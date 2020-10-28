package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.FreemarkerGenerateService;

/**
 * class: MysqlGenerateImpl <br>
 * description: mysql 模板生成 <br>
 * time: 2019/5/28
 * modify time: 2020-10-28 21:36:07
 *
 * @author zhuo <br>
 * @since 1.4.0
 * @version 1.4.3
 */
public class MysqlGenerateImpl extends FreemarkerGenerateService {

    public MysqlGenerateImpl() {
        addTemplatePath(ModuleEnums.XML,true, "/freemarker/template/xml/primary_key_mysql_mybatis_template.ftl");
        addTemplatePath(ModuleEnums.XML,false, "/freemarker/template/xml/no_primary_key_mysql_mybatis_template.ftl");

        addTemplatePath(ModuleEnums.MAPPER,true, "/freemarker/template/java/primary_key_mysql_mapper_template.ftl");
        addTemplatePath(ModuleEnums.MAPPER,false, "/freemarker/template/java/no_primary_key_mysql_mapper_template.ftl");

        addTemplatePath(ModuleEnums.MODEL, "/freemarker/template/java/model.ftl");
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYSQL;
    }
}
