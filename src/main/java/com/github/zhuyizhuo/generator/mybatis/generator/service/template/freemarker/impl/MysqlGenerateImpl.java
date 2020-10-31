package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.FreemarkerGenerateService;

/**
 * mysql 模板生成 <br>
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
public class MysqlGenerateImpl extends FreemarkerGenerateService {

    public MysqlGenerateImpl() {
        addTemplatePath(ModuleTypeEnums.XML,true, "/freemarker/template/xml/primary_key_mysql_mybatis_template.ftl");
        addTemplatePath(ModuleTypeEnums.XML,false, "/freemarker/template/xml/no_primary_key_mysql_mybatis_template.ftl");

        addTemplatePath(ModuleTypeEnums.MAPPER,true, "/freemarker/template/java/primary_key_mysql_mapper_template.ftl");
        addTemplatePath(ModuleTypeEnums.MAPPER,false, "/freemarker/template/java/no_primary_key_mysql_mapper_template.ftl");

        addTemplatePath(ModuleTypeEnums.MODEL, "/freemarker/template/java/model.ftl");
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYSQL;
    }
}
