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
 * @since 1.5.0
 */
public class MybatisPlusGenerateImpl extends FreemarkerGenerateService {

    public MybatisPlusGenerateImpl() {
        addTemplatePath(ModuleTypeEnums.XML, TemplateConstants.XML_MYBATIS_PLUS);

        addTemplatePath(ModuleTypeEnums.MAPPER, TemplateConstants.MAPPER_MYBATIS_PLUS);

        addTemplatePath(ModuleTypeEnums.MODEL, TemplateConstants.MODEL_MYBATIS_PLUS);

        addTemplatePath(ModuleTypeEnums.SERVICE, TemplateConstants.SERVICE_MYBATIS_PLUS);

        addTemplatePath(ModuleTypeEnums.SERVICE_IMPL, TemplateConstants.SERVICE_IMPL_MYBATIS_PLUS);
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYBATIS_PLUS;
    }
}
