package com.github.zhuyizhuo.generator.mybatis.service.abst;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * class: AbstractDbService <br>
 * description: 关系型数据库抽象类 <br>
 * time: 2018/7/30 13:12
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public abstract class AbstractDbService implements DbService {

    protected DataBaseInfo getDataBaseInfo() {
        DataBaseInfo tableInfo = new DataBaseInfo();
        tableInfo.setTableSchema(getTableSchema());
        tableInfo.setTableNames(getTables());
        return tableInfo;
    }

    protected String getTableSchema() {
        String tableSchema = PropertiesUtils.getProperties(ConfigConstants.TABLE_SCHEMA);
        if (GeneratorStringUtils.isNotBlank(tableSchema)){
            tableSchema = tableSchema.toUpperCase();
        }
        return tableSchema;
    }

    protected List<String> getTables() {
        String includeTableName = PropertiesUtils.getProperties(ConfigConstants.INCLUDE_TABLE_NAME);
        if (GeneratorStringUtils.isNotBlank(includeTableName)){
            return Splitter.on(",").splitToList(includeTableName);
        }
        return null;
    }

    protected void setTableInfoFtl(DbTableInfo dbTableInfo, TableInfoFtl ftlTableInfo) {

        ftlTableInfo.setTableName(dbTableInfo.getTableName());
        ftlTableInfo.setTableSchema(dbTableInfo.getTableSchema());
        if (GeneratorStringUtils.isBlank(ftlTableInfo.getTableComment())){
            ftlTableInfo.setTableComment("TODO");
        } else {
            ftlTableInfo.setTableComment(dbTableInfo.getTableComment());
        }
        List<ColumnInfo> columnLists = dbTableInfo.getColumnLists();
        JavaColumnInfo javaColumnInfo;
        for (int i = 0; i < columnLists.size(); i++) {
            ColumnInfo columnInfo = columnLists.get(i);
            javaColumnInfo = new JavaColumnInfo();
            javaColumnInfo.setDataType(columnInfo.getDataType());
            javaColumnInfo.setColumnName(columnInfo.getColumnName());
            javaColumnInfo.setColumnComment(replaceEnter(columnInfo.getColumnComment()));
            javaColumnInfo.setJavaColumnName(GeneratorStringUtils.changeColmName2Java(columnInfo.getColumnName(),"_"));
            javaColumnInfo.setJavaDataType(getJavaDataType(columnInfo));
            javaColumnInfo.setJavaDataTypeFullPath(TypeConversion.javaDataTypeFullPathMap.get(javaColumnInfo.getJavaDataType()));
            ftlTableInfo.addJavaColumnInfo(javaColumnInfo);
            ftlTableInfo.addImportPackage(javaColumnInfo.getJavaDataTypeFullPath());
        }
    }

    protected abstract String getJavaDataType(ColumnInfo columnInfo);

    /**
     * 备注去除回车换行
     * @param columnComment 字段备注
     * @return 去除回车换行后返回
     */
    protected String replaceEnter(String columnComment) {
        if (GeneratorStringUtils.isBlank(columnComment)) {
            return "";
        }
        return columnComment.replaceAll("\r"," ").replaceAll("\n"," ").replaceAll("\r\n"," ");
    }

    protected String getJavaTableName(String tableName) {
        return GeneratorStringUtils.changeTableName2JavaFirstUpper(tableName,"_");
    }
}
