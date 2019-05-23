package com.github.zhuyizhuo.generator.mybatis.enums;

/**
 * 方法枚举
 * @since 1.4.0
 * @version 1.4.0
 */
public enum MethodEnums {
    /** 新增方法 */
    INSERT("generate.java.method.insert.enabled","insert{0}",true),
    /** 批量新增方法*/
    BATCH_INSERT("generate.java.method.batch-insert.enabled","batchInsert{0}",true),
    /** 删除方法 */
    DELETE_BY_WHERE("generate.java.method.delete-by-where.enabled","delete{0}ByWhere",true),
    /** 根据主键删除方法 */
    DELETE_BY_PRIMARY_KEY("generate.java.method.delete-by-primary-key.enabled","delete{0}ByPrimaryKey",true),
    /** 根据主键更新方法 */
    UPDATE_BY_PRIMARY_KEY("generate.java.method.update-by-primary-key.enabled","update{0}ByPrimaryKey",true),
    /** 查询方法 */
    QUERY_BY_WHERE("generate.java.method.query-by-where.enabled","query{0}ListByWhere",true),
    /** 根据主键查询 */
    QUERY_BY_PRIMARY_KEY("generate.java.method.query-by-primary-key.enabled","query{0}ByPrimaryKey",true),
    /** 查询总数方法 */
    COUNT_BY_WHERE("generate.java.method.count-by-where.enabled","count{0}ByWhere",true),

    ;

    /** 配置是否生成方法的 key */
    private String propertiesEnabledKey;
    /** 默认格式化 */
    private String methodFormat;
    /** 默认是否生成方法 */
    private boolean defaultMethodEnabled;

    MethodEnums(String propertiesKey, String methodFormat, boolean methodEnabled) {
        this.propertiesEnabledKey = propertiesKey;
        this.methodFormat = methodFormat;
        this.defaultMethodEnabled = methodEnabled;
    }

    public String getPropertiesEnabledKey() {
        return propertiesEnabledKey;
    }

    public String getMethodFormat() {
        return methodFormat;
    }

    public boolean isDefaultMethodEnabled() {
        return defaultMethodEnabled;
    }
}
