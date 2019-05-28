package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl;

import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.FreemarkerGenerateService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: OracleGenerateImpl <br>
 * description: oracle 模板生成 <br>
 * time: 2019/5/28
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public class OracleGenerateImpl extends FreemarkerGenerateService {

    /**
     *  moduleTpye_hasPrivateKey -> templatePath
     */
    private Map<String,String> templatePathMap = new ConcurrentHashMap<>();

    public OracleGenerateImpl() {
        addTemplatePath(ModuleEnums.XML,true,"/freemarker/template/common/privateKey_mybatis_template.ftl");
        addTemplatePath(ModuleEnums.MAPPER,true,"/freemarker/template/common/privateKey_Dao_Template.ftl");
        addTemplatePath(ModuleEnums.XML,false,"/freemarker/template/common/nokey_mybatis_template.ftl");
        addTemplatePath(ModuleEnums.MAPPER,false,"/freemarker/template/common/noKey_Dao_Template.ftl");
        addTemplatePath(ModuleEnums.POJO,null,"/freemarker/template/common/javabean.ftl");
    }

    @Override
    protected DbTypeEnums getDbType() {
        return DbTypeEnums.ORACLE;
    }
}
