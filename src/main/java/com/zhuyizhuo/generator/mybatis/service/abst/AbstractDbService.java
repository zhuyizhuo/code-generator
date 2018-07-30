package com.zhuyizhuo.generator.mybatis.service.abst;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.zhuyizhuo.generator.mybatis.service.DbService;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;

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

    protected static DataBaseInfo getDataBaseInfo() {
        DataBaseInfo tableInfo = new DataBaseInfo();
        tableInfo.setTableSchema(getTableSchema());
        tableInfo.setTableNames(getTables());
        return tableInfo;
    }

    protected static String getTableSchema() {
        return PropertiesUtils.getProperties(ConfigConstants.TABLE_SCHEMA);
    }

    protected static List<String> getTables() {
        String includeTableName = PropertiesUtils.getProperties(ConfigConstants.INCLUDE_TABLE_NAME);
        if (StringUtils.isNotBlank(includeTableName)){
            return Splitter.on(",").splitToList(includeTableName);
        }
        return null;
    }
}
