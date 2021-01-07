package com.github.zhuyizhuo.generator.constants;

/**
 * 模板常量类
 *
 * @author zhuo
 * @since 1.5.0
 */
public class TemplateConstants {
    /**  mybatis plus 相关模板*/
    public static final String XML_MYBATIS_PLUS = "/freemarker/template/java/mybatis_plus/xml_mybatis_plus.ftl";
    public static final String MAPPER_MYBATIS_PLUS = "/freemarker/template/java/mybatis_plus/mapper_mybatis_plus.ftl";
    public static final String MODEL_MYBATIS_PLUS = "/freemarker/template/java/mybatis_plus/model_mybatis_plus.ftl";
    public static final String MODEL_MYBATIS_PLUS_LOMBOK = "/freemarker/template/java/mybatis_plus/model_mybatis_plus_lombok.ftl";
    public static final String SERVICE_MYBATIS_PLUS = "/freemarker/template/java/mybatis_plus/service_mybatis_plus.ftl";
    public static final String SERVICE_IMPL_MYBATIS_PLUS = "/freemarker/template/java/mybatis_plus/service_impl_mybatis_plus.ftl";

    /** mysql 相关模板 */
    public static final String XML_MYSQL_HAS_PRIMARY_KEY = "/freemarker/template/xml/primary_key_mysql_mybatis_template.ftl";
    public static final String XML_MYSQL_NO_PRIMARY_KEY = "/freemarker/template/xml/no_primary_key_mysql_mybatis_template.ftl";
    public static final String MAPPER_MYSQL_HAS_PRIMARY_KEY = "/freemarker/template/java/primary_key_mysql_mapper_template.ftl";
    public static final String MAPPER_MYSQL_NO_PRIMARY_KEY = "/freemarker/template/java/no_primary_key_mysql_mapper_template.ftl";

    /** oracle 相关模板 */
    public static final String XML_ORACLE_HAS_PRIMARY_KEY = "/freemarker/template/xml/primary_key_oracle_mybatis_template.ftl";
    public static final String XML_ORACLE_NO_PRIMARY_KEY = "/freemarker/template/xml/no_primary_key_oracle_mybatis_template.ftl";
    public static final String MAPPER_ORACLE_HAS_PRIMARY_KEY = "/freemarker/template/java/primary_key_oracle_mapper_template.ftl";
    public static final String MAPPER_ORACLE_NO_PRIMARY_KEY = "/freemarker/template/java/no_primary_key_oracle_mapper_template.ftl";

    /** 数据对象 model 模板 */
    public static final String MODEL = "/freemarker/template/java/common/model.ftl";
    /** 数据对象 lombok model 模板 */
    public static final String MODEL_LOMBOK = "/freemarker/template/java/common/model_lombok.ftl";
    /** 数据对象 swagger + lombok VO 模板   */
    public static final String VO_SWAGGER_LOMBOK = "/freemarker/template/java/common/vo_swagger_lombok.ftl";
}