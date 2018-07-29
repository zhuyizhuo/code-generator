package com.zhuyizhuo.generator.utils;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:36
 */
public class TypeConversion {

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
