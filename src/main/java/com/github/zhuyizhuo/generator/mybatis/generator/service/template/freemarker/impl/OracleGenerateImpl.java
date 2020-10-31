package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.FreemarkerGenerateService;

/**
 * oracle 模板生成 <br>
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
public class OracleGenerateImpl extends FreemarkerGenerateService {

    public OracleGenerateImpl() {
        addTemplatePath(ModuleTypeEnums.XML,true, "/freemarker/template/xml/primary_key_oracle_mybatis_template.ftl");
        addTemplatePath(ModuleTypeEnums.XML,false, "/freemarker/template/xml/no_primary_key_oracle_mybatis_template.ftl");

        addTemplatePath(ModuleTypeEnums.MAPPER,true, "/freemarker/template/java/primary_key_oracle_mapper_template.ftl");
        addTemplatePath(ModuleTypeEnums.MAPPER,false, "/freemarker/template/java/no_primary_key_oracle_mapper_template.ftl");

        addTemplatePath(ModuleTypeEnums.MODEL, "/freemarker/template/java/model.ftl");
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.ORACLE;
    }
}
