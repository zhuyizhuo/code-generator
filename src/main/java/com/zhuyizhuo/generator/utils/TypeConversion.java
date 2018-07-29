package com.zhuyizhuo.generator.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:36
 */
public class TypeConversion {

    private static final Map<String,String> mySqlDbType2JavaMap = new HashMap<>();
    public static final Map<String,String> javaDataTypeFullPathMap = new HashMap<>();

    static{
        mySqlDbType2JavaMap.put("INT","Integer");
        mySqlDbType2JavaMap.put("VARCHAR","String");

        javaDataTypeFullPathMap.put("Integer","java.lang.Integer");
        javaDataTypeFullPathMap.put("String","java.lang.String");
    }

    public static String mySqlDbType2Java(String type) {
        String javaDataType = mySqlDbType2JavaMap.get(type);
        if (StringUtils.isNotBlank(javaDataType)){
            return javaDataType;
        }
        return type;
    }

    public static String typeDbToJava(String type) {
        if ("".equals(type)) {
            return "String";
        } else if (type.equalsIgnoreCase("TIMESTAMP")) {
            return "Date";
        } else if (type.equalsIgnoreCase("oracle.sql.TIMESTAMP")
                || type.equalsIgnoreCase("java.sql.Timestamp")
                || type.equalsIgnoreCase("java.sql.Date")) {
            return "java.util.Date";
        } else if (type.equalsIgnoreCase("double")) {
            return "BigDecimal";
        } else if(type.equalsIgnoreCase("java.lang.Double")){
            return "java.math.BigDecimal";
        }
        else {
            return type;
        }
    }

    public static String type2JdbcType(String dbColmType) {
        if (dbColmType.equals("INT")) {
            return "INTEGER";
        } else if (dbColmType.equals("NUMBER")) {
            return "NUMERIC";
        } else if (dbColmType.equals("DATETIME")) {
            return "TIMESTAMP";
        } else if (dbColmType.equals("VARCHAR2")) {
            return "VARCHAR";
        } else if (dbColmType.equals("NUMBER")) {
            return "NUMERIC";
        } else if (dbColmType.equals("DATE")) {
            return "TIMESTAMP";
        }
        return dbColmType;
    }
}
