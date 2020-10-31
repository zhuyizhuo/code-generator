package com.github.zhuyizhuo.generator.enums;

import com.github.zhuyizhuo.generator.constants.LinkConstants;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;

/**
 * 错误类型枚举
 *
 * @author zhuo
 * @since 1.4.3
 */
public enum ErrorTypeEnums {
    /** 初始化配置失败! */
    INIT_CONFIG_ERROR("00001","","初始化配置失败!\n" +
            "可点击以下链接查找解决方案:\n" +
            "常见问题: " + LinkConstants.FAQ_DOC_URL + "\n" +
            "如果没有解决方案, 可提 ISSUE 反馈: " + LinkConstants.ISSUE_URL),
    /** 请检查是否添加对应数据库驱动依赖! */
    CHECK_DEPENDENCE("00002","Error setting driver on UnpooledDataSource.","请检查是否添加对应数据库驱动依赖!\n" +
            "示例: \n" +
            "mysql 8.0.20 依赖:  \n" +
            "<dependency>\n" +
            "  <groupId>mysql</groupId>\n" +
            "  <artifactId>mysql-connector-java</artifactId>\n" +
            "  <version>8.0.20</version>\n" +
            "</dependency> \n" +
            "oracle ojdbc14 依赖:  \n" +
            "<dependency>\n" +
            "  <groupId>com.oracle</groupId>\n" +
            "  <artifactId>ojdbc14</artifactId>\n" +
            "  <version>10.2.0.4.0</version>\n" +
            "</dependency>\n" +
            "Maven 官方仓库地址: " + LinkConstants.MAVEN_REPOSITORY_URL + "\n" +
            "可点击以下链接查找解决方案:\n" +
            "常见问题: " + LinkConstants.FAQ_DOC_URL + "\n" +
            "如果没有解决方案, 可提 ISSUE 反馈: " + LinkConstants.ISSUE_URL),
    /** 检查数据库配置 */
    CHECK_DATABASE_CONFIG("00003","using password: YES","请检查数据库配置信息是否正确。\n" +
            "可点击以下链接查找解决方案:\n" +
            "常见问题: " + LinkConstants.FAQ_DOC_URL + "\n" +
            "如果没有解决方案, 可提 ISSUE 反馈: " + LinkConstants.ISSUE_URL),
    /** 请检查数据库配置信息是否正确 可以设置日志级别查看详细堆栈 */
    ERROR_DATABASE_CONFIG("00004","","请检查数据库配置信息是否正确。\n" +
            "可点击以下链接查找解决方案:\n" +
            "常见问题: " + LinkConstants.FAQ_DOC_URL + "\n" +
            "如果没有解决方案, 可提 ISSUE 反馈: " + LinkConstants.ISSUE_URL),


    /** 未内置数据库数据类型和 Java 类型的映射关系 */
    NOT_SUPPORT_DB_DATATYPE("10001","","该版本暂未内置数据库[#{dbDataType}]类型和 Java 类型的映射关系!\n" +
            "请使用本生成器提供的扩展 API,自行添加数据库[#{dbDataType}]类型和 Java 类型的映射关系。\n " +
            "例如 :将 [#{dbDataType}]类型映射为 String 类型如下 : \n" +
            "\t new GeneratorBuilder().fieldType2JavaType(\"#{dbDataType}\", String.class).build();\n" +
            "@see "+ GeneratorBuilder.class.getName() +".fieldType2JavaType ; \n" +
            "详细扩展参考文档: " + LinkConstants.EXTENSION_DOC_URL_A + "\n" +
            "如需内置此类型映射,可提 ISSUE : " + LinkConstants.ISSUE_URL),
    /** 未内置数据库数据类型和 MYBATIS JDBC_TYPE的映射关系 */
    NOT_SUPPORT_DATATYPE_JDBC_TYPE("10002","","该版本暂未内置数据库[#{dbColmType}]类型和 Mybatis XML 中 JdbcType 的映射关系!\n" +
            "请使用本生成器提供的扩展 API,自行添加数据库[#{dbColmType}]类型和和 Mybatis XML 中 JdbcType 的映射关系。\n " +
            "例如 :将 [#{dbColmType}]类型映射为 VARCHAR 类型如下 : \n" +
            "\t new GeneratorBuilder().fieldType2JdbcType(\"#{dbColmType}\", JdbcType.VARCHAR).build();\n " +
            "@see "+ GeneratorBuilder.class.getName() +".fieldType2JdbcType ;\n" +
            "详细扩展参考文档: " + LinkConstants.EXTENSION_DOC_URL_B + "\n" +
            "如需内置此类型映射,可提 ISSUE : " + LinkConstants.ISSUE_URL),
    ;

    private String errorCode;
    private String errorMsg;
    private String message;

    ErrorTypeEnums(String errorCode, String errorMsg, String message) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.message = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getMessage() {
        return "错误码:" + errorCode + ", 错误信息:" + message;
    }

    public String getMessage(String replace) {
        GenericTokenParser parser = new GenericTokenParser("#{", "}", new TokenHandler() {
            @Override
            public String handleToken(String content) {
                return replace;
            }
        });
        return "错误码:" + errorCode + ", 错误信息:" + parser.parse(message);
    }
}
