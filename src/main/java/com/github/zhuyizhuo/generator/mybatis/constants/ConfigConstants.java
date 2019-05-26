package com.github.zhuyizhuo.generator.mybatis.constants;

/**
 * class: ConfigConstants <br>
 * description: 常量类 <br>
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class ConfigConstants {
    public static final String DB_TYPE = "db.type";

    public static final String URL = "db.url";
    public static final String DRIVER = "db.driver";
    public static final String TABLE_SCHEMA = "db.table-schema";
    public static final String USERNAME = "db.username";
    public static final String PASSWORD = "db.password";

    public static final String GENERATE_TABLES_NAME = "generate.table-names";
    public static final String LOG_ENABLED = "generate.log.enabled";


    public static final String PARAMETER_TYPE_USE_TYPE_ALIASES = "generate.resources.xml.mybatis.parameter-type.aliases.enabled";

    public static final String TABLE_SEPARATOR = "generate.table.separator";

    /** 数据库表的分隔符 */
    public static String tableRegex = "";
}
