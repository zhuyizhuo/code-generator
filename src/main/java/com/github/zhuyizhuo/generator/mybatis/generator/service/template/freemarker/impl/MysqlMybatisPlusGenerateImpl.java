package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.FreemarkerGenerateService;

/**
 * class: MysqlMybatisPlusGenerateImpl <br>
 * description: MybatisPlus 模板生成 <br>
 * time: 2020-10-28 21:37:06
 *
 * @author zhuo <br>
 * @since 1.4.3
 */
public class MysqlMybatisPlusGenerateImpl extends FreemarkerGenerateService {

    public MysqlMybatisPlusGenerateImpl() {
        addTemplatePath(ModuleEnums.XML, "/freemarker/template/java/mybatisPlus/mybatis_plus_xml.ftl");

        addTemplatePath(ModuleEnums.MAPPER, "/freemarker/template/java/mybatisPlus/mybatis_plus_mapper.ftl");

        addTemplatePath(ModuleEnums.MODEL, "/freemarker/template/java/mybatisPlus/mybatis_plus_model.ftl");

        addTemplatePath(ModuleEnums.SERVICE, "/freemarker/template/java/mybatisPlus/mybatis_plus_service.ftl");

        addTemplatePath(ModuleEnums.SERVICE_IMPL, "/freemarker/template/java/mybatisPlus/mybatis_plus_service_impl.ftl");
    }

    @Override
    protected TemplateTypeEnums getTemplateType() {
        return TemplateTypeEnums.MYBATIS_PLUS;
    }
}
