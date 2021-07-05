package com.github.zhuyizhuo.generator.enums;

/**
 * 方法枚举
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
public enum MethodEnums {
    /** 新增方法 */
    INSERT("generate.java.method.insert."),
    /** 批量新增方法*/
    BATCH_INSERT("generate.java.method.batch-insert."),
    /** 删除方法 */
    DELETE_BY_WHERE("generate.java.method.delete-by-where."),
    /** 根据主键删除方法 */
    DELETE_BY_PRIMARY_KEY("generate.java.method.delete-by-primary-key."),
    /** 根据主键更新方法 */
    UPDATE_BY_PRIMARY_KEY("generate.java.method.update-by-primary-key."),
    /** 查询方法 */
    QUERY_BY_WHERE("generate.java.method.query-by-where."),
    /** 根据主键查询 */
    QUERY_BY_PRIMARY_KEY("generate.java.method.select-one-by-primary-key."),
    /** 查询总数方法 */
    COUNT_BY_WHERE("generate.java.method.count-by-where."),
    /** 所有方法 */
    ALL_METHOD
    ;

    private String propertiesBaseKey;

    /** 配置是否生成方法 */
    private String propertiesEnabledKey = "enabled";
    /** 方法注释配置 */
    private String methodCommentKey = "comment";
    /** 默认格式化方式 */
    private String methodFormatKey = "name-format";

    MethodEnums() {
    }

    MethodEnums(String propertiesBaseKey) {
        this.propertiesBaseKey = propertiesBaseKey;
    }

    public String getPropertiesEnabledKey() {
        return propertiesBaseKey + propertiesEnabledKey;
    }

    public String getMethodCommentKey() {
        return propertiesBaseKey + methodCommentKey;
    }

    public String getMethodFormatKey() {
        return propertiesBaseKey + methodFormatKey;
    }
}