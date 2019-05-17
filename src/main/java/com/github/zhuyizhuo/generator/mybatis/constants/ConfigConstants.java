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

    public static final String DRIVER = "db.driver";
    public static final String URL = "db.url";
    public static final String TABLE_SCHEMA = "db.table-schema";
    public static final String USERNAME = "db.username";
    public static final String PASSWORD = "db.password";

    public static final String GENERATE_TABLES_NAME = "generate.table-names";
    public static final String LOG_ENABLED = "generate.log.enabled";

    public static final String BASE_PACKAGE = "generate.java.base-package";
    public static final String FILE_OUT_PUT_PATH = "generate.java.base-out-put-path";

    public static final String DAO_PACKAGE = "generate.java.mapper.package";
    public static final String DAO_NAME_FORMAT = "generate.java.mapper.name-format";
    public static final String DAO_OUT_PUT_PATH = "generate.java.mapper.path";

    public static final String POJO_PACKAGE = "generate.java.pojo.package";
    public static final String POJO_NAME_FORMAT = "generate.java.pojo.name-format";
    public static final String POJO_OUT_PUT_PATH = "generate.java.pojo.path";

    public static final String AUTHOR = "generate.java.comment.author";
    public static final String SINCE_VERSION = "generate.java.comment.since-version";
    public static final String VERSION = "generate.java.comment.current-version";

    public static final String XML_OUT_PUT_PATH = "generate.xml.out-put-path";
    public static final String XML_NAME_FORMAT = "generate.xml.name-format";

    public static final String PARAMETER_TYPE_USE_TYPE_ALIASES = "generate.xml.mybatis.parameter-type.aliases.enabled";

    /** 数据库表的分隔符 */
    public static String tableRegex = "_";
}
