package online.zhuyizhuo.generator.sample;

import com.alibaba.fastjson.JSON;
import com.github.zhuyizhuo.generator.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;

/**
 * jdk version : 1.8 +
 * 生成器扩展 <br>
 *     替换系统模板
 *     自定义模块模板
 *     无配置文件生成
 *     打印生成对象信息
 *
 * 本项目使用 mysql 数据库示例
 * 如使用 oracle 需添加 oracle 数据库驱动依赖
 * @author zhuo <br>
 */
public class CustomizeGenerator {

    public static void main(String[] args) {
        customizeGenerate();
    }

    private static void customizeGenerate() {
        // [可选]打印生成对象信息 可根据日志编写模板 模板使用 freemarker 编写, 使用 freemarker 语法 取值、循环、判断即可
        LogUtils.setLogService(object ->
            System.out.println("生成元信息:\n" + JSON.toJSONString(object))
        );

        /** [必选]数据库配置 可选值参考 DbTypeEnums */
        String dbType= DbTypeEnums.MYSQL.name();
        /** [必选]驱动包路径 */
        String dbDriver= "com.mysql.cj.jdbc.Driver";
        /** [必选]数据库链接 需改为你的数据库链接 */
        String dbUrl= "jdbc:mysql://localhost:3306/management?useUnicode=true&serverTimezone=Asia/Shanghai";
        /** [必选]表空间 如 sql 为 select * from management.demo; 则 management 为表空间 */
        String tableSchema= "你的数据库表空间";
        /** [必选]数据库用户名  */
        String dbUserName= "你的数据库用户名";
        /** [必选]数据库密码  */
        String dbPassword= "你的数据库密码";

        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableNames = "";
        /** [建议]生成类所在的基础包路径 */
        String basePackage = "com.generator.mybatis.plus";
        /** [建议]生成的代码注释 作者 缺省值为 TODO */
        String author = "作者";
        /**
         * [建议] java 类输出路径
         * 1.4.2 版本缺省配置为 / ,即生成至 generate.base.out-put-path 配置的基础路径下,
         * 此处更改配置为 /src/main/java/ 则生成路径变为 #{generate.base.out-put-path}/src/main/java/
         * 1.5.0 版本 java 类输出路径缺省配置更改为 /src/main/java/
         */
        String outputPath = "/src/main/java/";
        /** [可选]可设置基础生成路径，默认生成至系统变量 user.dir 路径下，可修改为指定路径 */
        String baseOutPath = System.getProperty("user.dir") + "/samples-v1.5.1";

        // [可选][自定义扩展] 自定义模块类型
        String customizeModuleType = "page";
        new GeneratorBuilder()
                .properties("db.type=" + dbType,
                        "db.driver=" + dbDriver,
                        "db.url=" + dbUrl,
                        "db.table-schema="+ tableSchema,
                        "db.username=" + dbUserName,
                        "db.password=" + dbPassword)
                .properties("generate.table-names=" + tableNames,
                        // 自定义属性  使用 #{属性名} 可动态获取
                        "basePackage=" + basePackage,
                        "generate.base.out-put-path=" + baseOutPath,
                        "generate.java.module.mapper.package=#{basePackage}.mapper",
                        "generate.java.module.mapper.out-put-path=" + outputPath,
                        "generate.java.module.model.package=#{basePackage}.model",
                        "generate.java.module.model.out-put-path=" + outputPath,
                        "generate.resources.xml.out-put-path=#{base-out-put-path}/"+
                                basePackage.replaceAll("\\.","/")+"/xml/")
                // 自定义模板生成 新增 service 模块
                .addJavaTemplate(new JavaModuleInfo("service",
                        "/template/service.ftl",
                        basePackage + ".service",
                        // java 类实际生成全路径为 {generate.base.out-put-path}/{outputPath}/{classPackage}/{className}.java
                        baseOutPath + "/" + outputPath + "/" + basePackage + ".service",
                        "{0}Service"))
                // 使用自定义模板
                .addJavaTemplate(new JavaModuleInfo("MODEL",
                        "/template/model.ftl",
                        basePackage + ".model",
                        // java 类实际生成全路径为 {generate.base.out-put-path}/{outputPath}/{classPackage}/{className}.java
                        baseOutPath + "/" + outputPath + "/" + basePackage + ".model",
                        "{0}"))
                .addJavaTemplate(new JavaModuleInfo("vo",
                        "/template/LombokSwaggerVO.ftl",
                        basePackage + ".vo",
                        baseOutPath + "/" + outputPath + "/" + basePackage + ".vo",
                        "{0}VO"))
                // 自定义生成页面
                .addCustomizeModuleTemplate(new CustomizeModuleInfo(customizeModuleType,
                "template/html.ftl",
                System.getProperty("user.dir")+"/src/main/resources/customize/page/{0}.html",
                "{0}"))
                // 自定义的模块 也可以自定义模块名格式化  moduleType 需一致
                .addModuleNameFormat(customizeModuleType, name -> GeneratorStringUtils.changeTableName2CamelFirstUpper(name,"_") + "Page")
                .build()
                .generate();
    }

}
