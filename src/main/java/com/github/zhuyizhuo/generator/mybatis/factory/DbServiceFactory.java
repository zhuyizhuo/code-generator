package com.github.zhuyizhuo.generator.mybatis.factory;

import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.service.impl.MysqlDbServiceImpl;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.service.impl.MysqlDbServiceImpl;
import com.github.zhuyizhuo.generator.mybatis.service.impl.OracleDbServiceImpl;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: DbServiceFactory <br>
 * description: 数据库service构建工厂 <br>
 * time: 2018/7/30 12:57
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class DbServiceFactory {

    private static Map<String,DbService> serviceMap = new ConcurrentHashMap<>();

    static{
        serviceMap.put(DbTypeEnums.MYSQL.toString(), new MysqlDbServiceImpl());
        serviceMap.put(DbTypeEnums.ORACLE.toString(), new OracleDbServiceImpl());
    }

    public static DbService getDbService() {
        String dbType = PropertiesUtils.getProperties(ConfigConstants.DB_TYPE);
        if (GeneratorStringUtils.isBlank(dbType)){
            String errorMsg = "未指定数据库类型:" + ConfigConstants.DB_TYPE + ",请在generate-config.properties中指定.DB_TYPE 值列表请参照 DbTypeEnums.java";
            LogUtils.printInfo(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        LogUtils.printInfo("数据库类型:" + dbType);
        DbService dbService = serviceMap.get(dbType);
        if (dbService == null){
            String errorMsg =  ConfigConstants.DB_TYPE + "配置类型不支持,所支持类型请参照 DbTypeEnums.java";
            LogUtils.printInfo(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return dbService;
    }
}
