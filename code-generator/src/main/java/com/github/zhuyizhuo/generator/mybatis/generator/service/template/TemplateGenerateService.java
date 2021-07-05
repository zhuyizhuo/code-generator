package com.github.zhuyizhuo.generator.mybatis.generator.service.template;

import com.github.zhuyizhuo.generator.mybatis.generator.service.GenerateService;

/**
 * 模板生成 service <br>
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
public interface TemplateGenerateService extends GenerateService {

    /**
     * 增加模板
     * @param moduleType 模块类型
     * @param templatePath 模板路径
     */
    void addTemplate(String moduleType,String templatePath);

}
