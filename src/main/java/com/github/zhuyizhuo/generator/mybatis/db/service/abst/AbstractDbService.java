package com.github.zhuyizhuo.generator.mybatis.db.service.abst;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.db.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
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
        String includeTableName = PropertiesUtils.getProperties(ConfigConstants.GENERATE_TABLES_NAME);
        if (GeneratorStringUtils.isNotBlank(includeTableName)){
            return Splitter.on(",").splitToList(includeTableName);
        }
        return null;
    }

    protected void setTableInfo(DbTableInfo dbTableInfo, TableInfo tableInfo) {

        tableInfo.setTableName(dbTableInfo.getTableName());
        tableInfo.setTableSchema(dbTableInfo.getTableSchema());
        if (GeneratorStringUtils.isBlank(tableInfo.getTableComment())){
            tableInfo.setTableComment("TODO");
        } else {
            tableInfo.setTableComment(dbTableInfo.getTableComment());
        }
        List<ColumnInfo> columnLists = dbTableInfo.getColumnLists();
        JavaColumnInfo javaColumnInfo;
        for (int i = 0; i < columnLists.size(); i++) {
            ColumnInfo columnInfo = columnLists.get(i);
            javaColumnInfo = new JavaColumnInfo();
            javaColumnInfo.setDataType(getDataType(columnInfo.getDataType()));
            javaColumnInfo.setColumnName(columnInfo.getColumnName());
            javaColumnInfo.setColumnComment(replaceEnter(columnInfo.getColumnComment()));
            javaColumnInfo.setJavaColumnName(GeneratorStringUtils.changeColmName2CamelFirstLower(columnInfo.getColumnName(),ConfigConstants.tableRegex));
            javaColumnInfo.setJavaDataType(getJavaDataType(columnInfo));
            /** 设置类全路径 java.lang包下的类不需要import */
            javaColumnInfo.setJavaDataTypeFullPath(TypeConversion.javaDataTypeFullPathMap.get(javaColumnInfo.getJavaDataType()));
            tableInfo.addJavaColumnInfo(javaColumnInfo);
            tableInfo.addImportPackage(javaColumnInfo.getJavaDataTypeFullPath());
        }
    }

    protected String getDataType(String dataType) {
        if (GeneratorStringUtils.isNotBlank(dataType) && dataType.contains("TIMESTAMP")){
            return "TIMESTAMP";
        }
        return dataType;
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

    protected String changeTableNameCamelCase(String tableName) {
        return GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName,ConfigConstants.tableRegex);
    }
}
