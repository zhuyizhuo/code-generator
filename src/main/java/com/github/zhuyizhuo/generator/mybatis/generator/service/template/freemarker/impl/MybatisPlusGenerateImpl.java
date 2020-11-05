package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.constants.TemplateConstants;
import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.FreemarkerGenerateService;

/**
 * MybatisPlus 模板生成 <br>
 * create time: 2020-10-28 21:37:06
 *
 * @author zhuo <br>
 * @since 1.4.3
 */
public class MybatisPlusGenerateImpl extends FreemarkerGenerateService {

    public MybatisPlusGenerateImpl() {
        addTemplatePath(ModuleTypeEnums.XML, TemplateConstants.MYBATIS_PLUS_XML);

        addTemplatePath(ModuleTypeEnums.MAPPER, TemplateConstants.MYBATIS_PLUS_MAPPER);

        addTemplatePath(ModuleTypeEnums.MODEL, TemplateConstants.MYBATIS_PLUS_MODEL);

        addTemplatePath(ModuleTypeEnums.SERVICE, TemplateConstants.MYBATIS_PLUS_SERVICE);

        addTemplatePath(ModuleTypeEnums.SERVICE_IMPL, TemplateConstants.MYBATIS_PLUS_SERVICE_IMPL);
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYBATIS_PLUS;
    }
}
