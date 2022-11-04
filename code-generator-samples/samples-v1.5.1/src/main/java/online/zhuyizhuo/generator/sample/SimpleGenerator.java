package online.zhuyizhuo.generator.sample;


import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl.MybatisPlusGenerateImpl;

public class SimpleGenerator {

    public static void main(String[] args) {
        /** [建议]要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; mysql 数据库表名大小写敏感 */
        String tableName = "VEHICLE";
        MybatisPlusGenerateImpl mybatisPlusGenerate = new MybatisPlusGenerateImpl();
        Generator generator = new GeneratorBuilder()
                .addGenerateService(mybatisPlusGenerate)
                .properties("generate.table-names=" + tableName)
                .properties("generate.java.comment.author=zhuo")
//                .fieldType2JavaType("NCLOB", String.class)
//                .fieldType2JdbcType("NVARCHAR2", JdbcType.NVARCHAR)
//                .fieldType2JdbcType("NCLOB", JdbcType.NCLOB)
//                .fieldType2JdbcType("CLOB", JdbcType.CLOB)
//                .properties("generate.log.level=DEBUG")
//                .build("simple_config.properties");
                .build("simple_config_oracle.properties");
//        LogUtils.setLevel(LogLevelEnums.DEBUG.name());
        generator.generate();
    }

}
