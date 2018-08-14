package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:36
 */
public class TypeConversion {

    public static final Map<String,String> mySqlDbType2JavaMap = new HashMap<String,String>();
    public static final Map<String,String> oracleDbType2JavaMap = new HashMap<String,String>();
    public static final Map<String,String> javaDataTypeFullPathMap = new HashMap<String,String>();

    static{
        initMysqlType2JavaMap();

        initOracleType2JavaMap();

        initJavaDataTypeFullPathMap();
    }

    private static void initJavaDataTypeFullPathMap() {
        javaDataTypeFullPathMap.put("Date","java.util.Date");
        javaDataTypeFullPathMap.put("BigDecimal","java.math.BigDecimal");
    }

    private static void initOracleType2JavaMap() {
        oracleDbType2JavaMap.put("CHAR","String");
        oracleDbType2JavaMap.put("NUMBER","Integer");
        oracleDbType2JavaMap.put("LONG","Long");
        oracleDbType2JavaMap.put("FLOAT","BigDecimal");
        oracleDbType2JavaMap.put("VARCHAR2","String");
        oracleDbType2JavaMap.put("NVARCHAR2","String");
        oracleDbType2JavaMap.put("CLOB","String");
        oracleDbType2JavaMap.put("BLOB","String");
        oracleDbType2JavaMap.put("TIMESTAMP","Date");
        oracleDbType2JavaMap.put("DATE","Date");
    }

    private static void initMysqlType2JavaMap() {
        mySqlDbType2JavaMap.put("INT","Integer");
        mySqlDbType2JavaMap.put("VARCHAR","String");
        mySqlDbType2JavaMap.put("TEXT","String");
        mySqlDbType2JavaMap.put("CHAR","String");
        mySqlDbType2JavaMap.put("BLOB","String");
        mySqlDbType2JavaMap.put("LONGTEXT","String");
        mySqlDbType2JavaMap.put("LONGBLOB","String");
        mySqlDbType2JavaMap.put("TINYBLOB","String");
        mySqlDbType2JavaMap.put("TINYTEXT","String");
        mySqlDbType2JavaMap.put("DECIMAL","BigDecimal");
        mySqlDbType2JavaMap.put("TINYINT","Integer");
        mySqlDbType2JavaMap.put("BIGINT","Long");
        mySqlDbType2JavaMap.put("FLOAT","BigDecimal");
        mySqlDbType2JavaMap.put("DOUBLE","BigDecimal");
        mySqlDbType2JavaMap.put("DATE","Date");
        mySqlDbType2JavaMap.put("TIME","Date");
        mySqlDbType2JavaMap.put("DATETIME","Date");
        mySqlDbType2JavaMap.put("TIMESTAMP","Date");
        mySqlDbType2JavaMap.put("YEAR","Integer");
    }

    public static String dbType2Java(Map<String,String> dbTypeMap, String type) {
        String javaDataType = dbTypeMap.get(type);
        if (GeneratorStringUtils.isNotBlank(javaDataType)){
            return javaDataType;
        }
        return type;
    }

}
