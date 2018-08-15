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
    public static final Map<String,String> type2JdbcTypeMap = new HashMap<String,String>();
    public static final Map<String,String> parameterTypeMap = new HashMap<String,String>();

    static{
        initMysqlType2JavaMap();

        initOracleType2JavaMap();

        initJavaDataTypeFullPathMap();

        initType2JdbcTypeMap();
        
        initParameterTypeMap();
    }

    private static void initParameterTypeMap() {
        parameterTypeMap.put("byte","_byte");
        parameterTypeMap.put("long","_long");
        parameterTypeMap.put("short","_short");
        parameterTypeMap.put("int","_int");
        parameterTypeMap.put("double","_double");
        parameterTypeMap.put("float","_float");
        parameterTypeMap.put("boolean","_boolean");
        parameterTypeMap.put("String","string");
        parameterTypeMap.put("Byte","byte");
        parameterTypeMap.put("Long","long");
        parameterTypeMap.put("Short","short");
        parameterTypeMap.put("Integer","int");
        parameterTypeMap.put("Double","double");
        parameterTypeMap.put("Float","float");
        parameterTypeMap.put("Boolean","boolean");
        parameterTypeMap.put("Date","date");
        parameterTypeMap.put("BigDecimal","bigdecimal");
    }

    private static void initType2JdbcTypeMap() {
        type2JdbcTypeMap.put("INT","INTEGER");
        type2JdbcTypeMap.put("NUMBER","NUMERIC");
        type2JdbcTypeMap.put("TIMESTAMP(6)","TIMESTAMP");
        type2JdbcTypeMap.put("DATETIME","TIMESTAMP");
        type2JdbcTypeMap.put("VARCHAR2","VARCHAR");
        type2JdbcTypeMap.put("NUMBER","NUMERIC");
        type2JdbcTypeMap.put("DATE","TIMESTAMP");
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
        oracleDbType2JavaMap.put("TIMESTAMP(6)","Date");
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

    public static String getTypeByMap(Map<String,String> dbTypeMap, String type) {
        String javaDataType = dbTypeMap.get(type);
        if (GeneratorStringUtils.isNotBlank(javaDataType)){
            return javaDataType;
        }
        return type;
    }

    public static String type2JdbcType(String dbColmType) {
        String jdbcType = type2JdbcTypeMap.get(dbColmType);
        if (GeneratorStringUtils.isNotBlank(jdbcType)){
            return jdbcType;
        }
        return dbColmType;
    }

}
