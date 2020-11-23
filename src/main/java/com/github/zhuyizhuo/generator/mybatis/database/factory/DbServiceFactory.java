package com.github.zhuyizhuo.generator.mybatis.database.factory;

import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.database.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.database.service.impl.MysqlDbServiceImpl;
import com.github.zhuyizhuo.generator.mybatis.database.service.impl.OracleDbServiceImpl;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库service构建工厂 <br>
 * time: 2018/7/30 12:57
 *
 * @author zhuo <br>
 * @version 1.0
 */
public class DbServiceFactory {

    private static Map<String,DbService> serviceMap = new ConcurrentHashMap<String,DbService>();

    static{
        serviceMap.put(DbTypeEnums.MYSQL.toString(), new MysqlDbServiceImpl());
        serviceMap.put(DbTypeEnums.ORACLE.toString(), new OracleDbServiceImpl());
    }

    public static DbService getDbService() {
        String dbType = ContextHolder.getConfig(ConfigConstants.DB_TYPE).toUpperCase();
        LogUtils.info("数据库类型:" + dbType);
        DbService dbService = serviceMap.get(dbType);
        if (dbService == null){
            String errorMsg =  ConfigConstants.DB_TYPE + "配置类型不支持,所支持类型请参照 " + DbTypeEnums.class.getName();
            LogUtils.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return dbService;
    }

}
