package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.enums.ErrorTypeEnums;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>类型转换器</p>
 *
 * @author zhuo
 * @version 1.0
 * @date : 2018/7/29 18:36
 */
public class TypeConversion {
    /**
     * key 数据库字段类型
     * value java 数据类型
     * 注意:
     * 如果 java 数据类型非 java.lang 包下的类,
     * 则需要在 javaDataTypeFullPathMap 中须有对应的全路径配置
     */
    public static final Map<String,String> dbDataType2JavaType = new HashMap<>();
    /**
     * key java 数据类型
     * value 该类型全路径
     * 即 import 的 java 类全路径
     */
    public static final Map<String,String> javaDataTypeFullPathMap = new HashMap<>();
    /**
     * key 数据库字段类型  key 必须大写
     * value mybatis JDBC type
     */
    public static final Map<String,String> type2JdbcTypeMap = new HashMap<>();
    /**
     * key java 数据类型
     * value mybatis 内置对应别名
     */
    public static final Map<String,String> parameterTypeMap = new HashMap<String,String>();

    static{
        initDBDataType2JavaMap();

        initType2JdbcTypeMap();
        
        initParameterTypeMap();
    }

    private static void initParameterTypeMap() {
        /** 基本数据类型 */
        addParameterType("byte","_byte");
        addParameterType("long","_long");
        addParameterType("short","_short");
        addParameterType("int","_int");
        addParameterType("double","_double");
        addParameterType("float","_float");
        addParameterType("boolean","_boolean");
        /** 对象类型 */
        addParameterType("String","string");
        addParameterType("Byte","byte");
        addParameterType("Long","long");
        addParameterType("Short","short");
        addParameterType("Integer","int");
        addParameterType("Double","double");
        addParameterType("Float","float");
        addParameterType("Boolean","boolean");
        addParameterType("Date","date");
        addParameterType("BigDecimal","bigdecimal");
    }

    private static void initType2JdbcTypeMap() {
        addType2JdbcType("INT", JdbcType.INTEGER);
        addType2JdbcType("NUMBER", JdbcType.NUMERIC);
        addType2JdbcType("BIT", JdbcType.NUMERIC);
        addType2JdbcType("TIMESTAMP(6)", JdbcType.TIMESTAMP);
        addType2JdbcType("TIMESTAMP", JdbcType.TIMESTAMP);
        addType2JdbcType("DATETIME", JdbcType.TIMESTAMP);
        addType2JdbcType("VARCHAR", JdbcType.VARCHAR);
        addType2JdbcType("VARCHAR2", JdbcType.VARCHAR);
        addType2JdbcType("DATE", JdbcType.TIMESTAMP);
        addType2JdbcType("DECIMAL", JdbcType.DECIMAL);
        addType2JdbcType("DOUBLE", JdbcType.DOUBLE);
        addType2JdbcType("FLOAT", JdbcType.FLOAT);
        addType2JdbcType("BIGINT", JdbcType.BIGINT);
        addType2JdbcType("SMALLINT", JdbcType.SMALLINT);
        addType2JdbcType("TINYINT", JdbcType.TINYINT);
        addType2JdbcType("NUMERIC", JdbcType.NUMERIC);
        addType2JdbcType("TEXT", JdbcType.VARCHAR);
     }

    private static void initDBDataType2JavaMap() {
        setDBDataType2JavaClass("CHAR",String.class);
        setDBDataType2JavaClass("NUMBER",Integer.class);
        setDBDataType2JavaClass("LONG",Long.class);
        setDBDataType2JavaClass("VARCHAR2",String.class);
        setDBDataType2JavaClass("NVARCHAR2",String.class);
        setDBDataType2JavaClass("CLOB",String.class);
        setDBDataType2JavaClass("BLOB",String.class);
        setDBDataType2JavaClass("INT",Integer.class);
        setDBDataType2JavaClass("VARCHAR",String.class);
        setDBDataType2JavaClass("TEXT",String.class);
        setDBDataType2JavaClass("LONGTEXT",String.class);
        setDBDataType2JavaClass("LONGBLOB",String.class);
        setDBDataType2JavaClass("TINYBLOB",String.class);
        setDBDataType2JavaClass("TINYTEXT",String.class);
        setDBDataType2JavaClass("BIT",Integer.class);
        setDBDataType2JavaClass("TINYINT",Integer.class);
        setDBDataType2JavaClass("BIGINT",Long.class);
        setDBDataType2JavaClass("DECIMAL", BigDecimal.class);
        setDBDataType2JavaClass("FLOAT", BigDecimal.class);
        setDBDataType2JavaClass("DOUBLE", BigDecimal.class);
        setDBDataType2JavaClass("DATE",  LocalDateTime.class);
        setDBDataType2JavaClass("TIME", LocalDateTime.class);
        setDBDataType2JavaClass("DATETIME", LocalDateTime.class);
        setDBDataType2JavaClass("YEAR", LocalDateTime.class);
        setDBDataType2JavaClass("FLOAT", BigDecimal.class);
        setDBDataType2JavaClass("TIMESTAMP", LocalDateTime.class);
        setDBDataType2JavaClass("TIMESTAMP(6)", LocalDateTime.class);
    }

    /**
     * 生成实体时 需要 import 的引用类型在此设置
     * @param dbDataType 数据库字段类型
     * @param clazz java 类型
     */
    private static void setDBDataType2JavaClass(String dbDataType, Class clazz) {
        addDBDataType2JavaType(dbDataType, clazz.getSimpleName());
        addJavaDataTypeFullPath(clazz);
    }

    /**
     * 根据 dbDataType 转大写后去 map 取值, 如果值不存在返回 dbDataType
     * @param dbDataType 根据 dbDataType 转大写后去 map 取值
     * @return 根据 dbDataType 转大写后去 map 取值, 如果值不存在返回 dbDataType
     */
    public static String getJavaTypeByDBDataType(String dbDataType) {
        if (GeneratorStringUtils.isBlank(dbDataType)){
            return "";
        }
        String javaDataType = dbDataType2JavaType.get(dbDataType.toUpperCase());
        if (GeneratorStringUtils.isNotBlank(javaDataType)){
            return javaDataType;
        }
        throw new UnsupportedOperationException(ErrorTypeEnums.NOT_SUPPORT_DB_DATATYPE.getMessage(dbDataType));
    }

    public static String type2JdbcType(String dbColmType) {
        if (GeneratorStringUtils.isBlank(dbColmType)){
            return "";
        }
        String jdbcType = type2JdbcTypeMap.get(dbColmType.toUpperCase());
        if (GeneratorStringUtils.isNotBlank(jdbcType)){
            return jdbcType;
        }
        throw new UnsupportedOperationException(ErrorTypeEnums.NOT_SUPPORT_DATATYPE_JDBC_TYPE.getMessage(dbColmType));
    }

    /**
     * 根据 java 数据类型 获取 mybatis parameter 类型
     * @param javaDataType java 数据类型
     * @return mybatis parameter 类型
     */
    public static String getMybatisParameterTypeByJavaDataType(String javaDataType) {
        if (GeneratorStringUtils.isBlank(javaDataType)){
            return "";
        }
        String mybatisParameterType = parameterTypeMap.get(javaDataType);
        if (GeneratorStringUtils.isNotBlank(mybatisParameterType)){
            return mybatisParameterType;
        }
        return javaDataType;
    }

    public static void addJavaDataTypeFullPath(Class<?> value){
        String simpleName = value.getSimpleName();
        if (javaDataTypeFullPathMap.containsKey(simpleName)){
            return;
        }
        String name = value.getName();
        if (name.startsWith("java.lang.") && name.split("\\.").length == 3){
            return;
        }
        javaDataTypeFullPathMap.put(simpleName, name);
    }

    //to check
    public static void addParameterType(Class<?> clazz) {
        parameterTypeMap.put(clazz.getSimpleName(), clazz.getName());
    }

    public static void addParameterType(String simpleName, String parameterType) {
        parameterTypeMap.put(simpleName, parameterType);
    }

    public static void addDBDataType2JavaType(String dbDataType, String javaType) {
        dbDataType2JavaType.put(dbDataType.toUpperCase(), javaType);
    }

    public static void addType2JdbcType(String dataBaseType, JdbcType jdbcType) {
        if (Objects.nonNull(dataBaseType)){
            type2JdbcTypeMap.put(dataBaseType.toUpperCase(), jdbcType.toString());
        }
    }

    /**
     * 初始化
     * @param typeMapper
     */
    public static void init(Map<String,Class<?>> typeMapper) {
        if (typeMapper != null && !typeMapper.isEmpty()) {
            for (Map.Entry<String, Class<?>> entry : typeMapper.entrySet()) {
                Class<?> clazz = entry.getValue();
                //todo
                addParameterType(clazz);
                setDBDataType2JavaClass(entry.getKey(), clazz);
            }
        }
    }

}
