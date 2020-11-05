package com.github.zhuyizhuo.generator.constants;

/**
 * 模板常量类
 *
 * @author zhuo
 * @since 1.4.3
 */
public class TemplateConstants {
    /**  mybatis plus 相关模板*/
    public static final String MYBATIS_PLUS_XML = "/freemarker/template/java/mybatisPlus/mybatis_plus_xml.ftl";
    public static final String MYBATIS_PLUS_MAPPER = "/freemarker/template/java/mybatisPlus/mybatis_plus_mapper.ftl";
    public static final String MYBATIS_PLUS_MODEL = "/freemarker/template/java/mybatisPlus/mybatis_plus_model.ftl";
    public static final String MYBATIS_PLUS_SERVICE = "/freemarker/template/java/mybatisPlus/mybatis_plus_service.ftl";
    public static final String MYBATIS_PLUS_SERVICE_IMPL = "/freemarker/template/java/mybatisPlus/mybatis_plus_service_impl.ftl";

    /** mysql 相关模板 */
    public static final String MYSQL_XML_HAS_PRIMARY_KEY = "/freemarker/template/xml/primary_key_mysql_mybatis_template.ftl";
    public static final String MYSQL_XML_NO_PRIMARY_KEY = "/freemarker/template/xml/no_primary_key_mysql_mybatis_template.ftl";
    public static final String MYSQL_MAPPER_HAS_PRIMARY_KEY = "/freemarker/template/java/primary_key_mysql_mapper_template.ftl";
    public static final String MYSQL_MAPPER_NO_PRIMARY_KEY = "/freemarker/template/java/no_primary_key_mysql_mapper_template.ftl";

    /** oracle 相关模板 */
    public static final String ORACLE_XML_HAS_PRIMARY_KEY = "/freemarker/template/xml/primary_key_oracle_mybatis_template.ftl";
    public static final String ORACLE_XML_NO_PRIMARY_KEY = "/freemarker/template/xml/no_primary_key_oracle_mybatis_template.ftl";
    public static final String ORACLE_MAPPER_HAS_PRIMARY_KEY = "/freemarker/template/java/primary_key_oracle_mapper_template.ftl";
    public static final String ORACLE_MAPPER_NO_PRIMARY_KEY = "/freemarker/template/java/no_primary_key_oracle_mapper_template.ftl";

    /** 数据对象 model 模板 */
    public static final String MODEL = "/freemarker/template/java/model.ftl";
}