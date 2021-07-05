package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.constants.TemplateConstants;
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
        addTemplatePath(ModuleTypeEnums.XML,true, TemplateConstants.XML_MYSQL_HAS_PRIMARY_KEY);
        addTemplatePath(ModuleTypeEnums.XML,false, TemplateConstants.XML_MYSQL_NO_PRIMARY_KEY);

        addTemplatePath(ModuleTypeEnums.MAPPER,true, TemplateConstants.MAPPER_MYSQL_HAS_PRIMARY_KEY);
        addTemplatePath(ModuleTypeEnums.MAPPER,false, TemplateConstants.MAPPER_MYSQL_NO_PRIMARY_KEY);

        addTemplatePath(ModuleTypeEnums.MODEL, TemplateConstants.MODEL);
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYSQL;
    }
}
