package com.github.zhuyizhuo.generator.utils;

import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:36
 */
public class TypeConversion {
    /**
     * key 数据库字段类型
     * value java 数据类型
     * java 类型 import 全路径配置 javaDataTypeFullPathMap
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
     * value mybatis内置对应别名
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
     }

    private static void initDBDataType2JavaMap() {
        addDBDataType2JavaType("CHAR","String");
        addDBDataType2JavaType("NUMBER","Integer");
        addDBDataType2JavaType("LONG","Long");
        addDBDataType2JavaType("VARCHAR2","String");
        addDBDataType2JavaType("NVARCHAR2","String");
        addDBDataType2JavaType("CLOB","String");
        addDBDataType2JavaType("BLOB","String");
        addDBDataType2JavaType("INT","Integer");
        addDBDataType2JavaType("VARCHAR","String");
        addDBDataType2JavaType("TEXT","String");
        addDBDataType2JavaType("LONGTEXT","String");
        addDBDataType2JavaType("LONGBLOB","String");
        addDBDataType2JavaType("TINYBLOB","String");
        addDBDataType2JavaType("TINYTEXT","String");
        addDBDataType2JavaType("TINYINT","Integer");
        addDBDataType2JavaType("BIGINT","Long");
        /** 生成实体时 需要 import 的引用类型在此设置 */
        setDBDataType2JavaObjectType("DECIMAL", BigDecimal.class);
        setDBDataType2JavaObjectType("FLOAT", BigDecimal.class);
        setDBDataType2JavaObjectType("DOUBLE", BigDecimal.class);
        setDBDataType2JavaObjectType("DATE",  LocalDateTime.class);
        setDBDataType2JavaObjectType("TIME", LocalDateTime.class);
        setDBDataType2JavaObjectType("DATETIME", LocalDateTime.class);
        setDBDataType2JavaObjectType("YEAR", LocalDateTime.class);
        setDBDataType2JavaObjectType("FLOAT", BigDecimal.class);
        setDBDataType2JavaObjectType("TIMESTAMP", LocalDateTime.class);
        setDBDataType2JavaObjectType("TIMESTAMP(6)", LocalDateTime.class);
    }

    /**
     * 生成实体时 需要 import 的引用类型在此设置
     * @param dbDataType 数据库字段类型
     * @param clazz java 类型
     */
    private static void setDBDataType2JavaObjectType(String dbDataType, Class clazz) {
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
        String errorMessage = "该版本暂未内置数据库["+dbDataType+"]类型和 Java 类型的映射关系!\n" +
                "请使用本生成器提供的扩展 API,自行添加数据库字段类型和 Java 类型的映射关系。\n " +
                "@see com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder.fieldType2JavaType ;";
        throw new UnsupportedOperationException(errorMessage);
    }

    public static String type2JdbcType(String dbColmType) {
        if (GeneratorStringUtils.isBlank(dbColmType)){
            return "";
        }
        String jdbcType = type2JdbcTypeMap.get(dbColmType.toUpperCase());
        if (GeneratorStringUtils.isNotBlank(jdbcType)){
            return jdbcType;
        }
        String errorMessage = "该版本暂未内置数据库["+dbColmType+"]类型和 Mybatis XML 中 JdbcType 的映射关系!\n" +
                "请使用本生成器提供的扩展 API,自行添加数据库字段类型和 Mybatis XML 中 JdbcType 的映射关系。\n " +
                "@see com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder.fieldType2JdbcType ;";
        throw new UnsupportedOperationException(errorMessage);
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
            String dbType = CheckUtils.checkDBType();
            for (Map.Entry<String, Class<?>> entry : typeMapper.entrySet()) {
                Class<?> clazz = entry.getValue();
                // 设置需要导入的类路径
                addParameterType(clazz);
                setDBDataType2JavaObjectType(entry.getKey(), clazz);
            }
        }
    }

}
