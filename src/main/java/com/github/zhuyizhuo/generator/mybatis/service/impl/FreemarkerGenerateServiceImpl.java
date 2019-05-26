package com.github.zhuyizhuo.generator.mybatis.service.impl;

import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.service.GenerateService;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateMetaData;
import com.github.zhuyizhuo.generator.mybatis.vo.TemplateGenerateInfo;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * freemarker 模板生成
 */
public class FreemarkerGenerateServiceImpl implements GenerateService {
    /**
     *
     *  moduleTpye_hasPrivateKey -> templatePath
     */
    private Map<String,String> templatePathMap = new ConcurrentHashMap<>();

    public FreemarkerGenerateServiceImpl() {
        templatePathMap.put(ModuleEnums.XML+"_true", "/freemarker/template/common/privateKey_mybatis_template.ftl");
        templatePathMap.put(ModuleEnums.XML+"_false", "/freemarker/template/common/nokey_mybatis_template.ftl");
        templatePathMap.put(ModuleEnums.MAPPER+"_true", "/freemarker/template/common/privateKey_Dao_Template.ftl");
        templatePathMap.put(ModuleEnums.MAPPER+"_false", "/freemarker/template/common/noKey_Dao_Template.ftl");
        templatePathMap.put(ModuleEnums.POJO+"_false", "/freemarker/template/common/javabean.ftl");
        templatePathMap.put(ModuleEnums.POJO+"_true", "/freemarker/template/common/javabean.ftl");
    }

    /**
     * 获取模板路径
     * @param moduleType 模块类型
     * @param hasPrivateKey 是否有主键
     * @return 模板路径
     */
    public String getTemplatePath(String moduleType, boolean hasPrivateKey) {
        return this.templatePathMap.get(moduleType+"_"+hasPrivateKey);
    }

    @Override
    public void generate(GenerateMetaData generateMetaData) {
        try {
            Map<String, List<TemplateGenerateInfo>> tableInfosMap = generateMetaData.getTableInfosMap();
            for (Map.Entry<String, List<TemplateGenerateInfo>> entry : tableInfosMap.entrySet()) {
                List<TemplateGenerateInfo> value = entry.getValue();
                boolean hasPrimaryKey = value.get(0).getGenerateInfo().getTableInfo().isHasPrimaryKey();
                for (int i = 0; i < value.size(); i++) {
                    TemplateGenerateInfo templateGenerateInfo = value.get(i);
                    Freemarker.printFile(getTemplatePath(templateGenerateInfo.getModuleType(),hasPrimaryKey),
                            templateGenerateInfo.getFileOutputPath(), templateGenerateInfo.getGenerateInfo());
                }
            }

        }catch (Exception e){
            LogUtils.printErrInfo("FreemarkerGenerateServiceImpl.generate error!");
            LogUtils.printException(e);
        }
    }

}
