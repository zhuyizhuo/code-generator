package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.TemplateTypeEnums;
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
        addTemplatePath(ModuleTypeEnums.XML, "/freemarker/template/java/mybatisPlus/mybatis_plus_xml.ftl");

        addTemplatePath(ModuleTypeEnums.MAPPER, "/freemarker/template/java/mybatisPlus/mybatis_plus_mapper.ftl");

        addTemplatePath(ModuleTypeEnums.MODEL, "/freemarker/template/java/mybatisPlus/mybatis_plus_model.ftl");

        addTemplatePath(ModuleTypeEnums.SERVICE, "/freemarker/template/java/mybatisPlus/mybatis_plus_service.ftl");

        addTemplatePath(ModuleTypeEnums.SERVICE_IMPL, "/freemarker/template/java/mybatisPlus/mybatis_plus_service_impl.ftl");
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYBATIS_PLUS;
    }
}
