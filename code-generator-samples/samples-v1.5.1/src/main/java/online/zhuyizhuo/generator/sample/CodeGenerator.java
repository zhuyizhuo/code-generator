package online.zhuyizhuo.generator.sample;

import com.github.zhuyizhuo.generator.constants.TemplateConstants;
import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl.MybatisPlusGenerateImpl;
import org.apache.ibatis.type.JdbcType;

public class CodeGenerator {

    public static void main(String[] args) {
        /** 要生成的表名 多个可用英文逗号隔开; 缺省为当前表空间下所有表; */
        String tableName = "ACT_EVT_LOG";
        //TODO 使用时注意修改作者名称
        String author = "zhuo";
        MybatisPlusGenerateImpl mybatisPlusGenerate = new MybatisPlusGenerateImpl();
        Generator generator = new GeneratorBuilder()
                .addGenerateService(mybatisPlusGenerate)
                .properties("generate.table-names=" + tableName)
                .properties("generate.java.comment.author=" + author)
                .fieldType2JdbcType("longblob", JdbcType.VARCHAR)
                .replaceDefaultTemplate(ModuleTypeEnums.MODEL, TemplateConstants.MODEL_MYBATIS_PLUS_LOMBOK)
                .replaceDefaultTemplate(ModuleTypeEnums.VO, TemplateConstants.VO_SWAGGER_LOMBOK)
//                .build("simple_config_oracle.properties");
                .build("simple_config_mysql.properties");
        generator.generate();
    }
}
