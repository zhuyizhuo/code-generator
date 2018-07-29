package com.zhuyizhuo.generator.mybatis.database.vo;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.zhuyizhuo.generator.mybatis.database.dto.JavaTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;

import java.util.List;
import java.util.Map;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 16:30
 */
public class FreeMarkerVO {
    /** 表名 和java表信息对应数据*/
    private Map<String,JavaTableInfo> javaTableInfos;
    /** 表名 和数据库表信息对应数据*/
    private Map<String,DbTableInfo> dbTableInfos;

    public Map<String, JavaTableInfo> getJavaTableInfos() {
        return javaTableInfos;
    }

    public void setJavaTableInfos(Map<String, JavaTableInfo> javaTableInfos) {
        this.javaTableInfos = javaTableInfos;
    }

    public Map<String, DbTableInfo> getDbTableInfos() {
        return dbTableInfos;
    }

    public void setDbTableInfos(Map<String, DbTableInfo> dbTableInfos) {
        this.dbTableInfos = dbTableInfos;
    }

    public void setDbTableInfos(List<DbTableInfo> dbTableInfos) {
        if (dbTableInfos == null){
            return;
        }
         this.dbTableInfos = Maps.uniqueIndex(dbTableInfos.iterator(), new Function<DbTableInfo,String>() {
            public String apply(DbTableInfo info) {
                return info.getTableName();
            }
        });
    }
}
