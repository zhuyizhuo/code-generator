package com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker;

import com.github.zhuyizhuo.generator.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.TemplateGenerateService;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateMetaData;
import com.github.zhuyizhuo.generator.mybatis.vo.ModulePathInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * freemarker 模板生成
 */
public abstract class FreemarkerGenerateService implements TemplateGenerateService {
    /**
     *  TemplateType_moduleType_hasPrivateKey -> templatePath
     */
    private Map<String,String> templatePathMap = new ConcurrentHashMap<>();

    protected abstract TemplateTypeEnums getTemplateType();

    protected void addTemplatePath(ModuleTypeEnums moduleType, Boolean hasPrivateKey, String templatePath){
        if (hasPrivateKey == null){
            addTemplate(moduleType.name(), templatePath);
        } else {
            this.templatePathMap.put(getTemplateType() + "_" + moduleType+ "_" + hasPrivateKey, templatePath);
        }
    }

    protected void addTemplatePath(ModuleTypeEnums moduleType, String templatePath) {
        addTemplate(moduleType.name(), templatePath);
    }

    @Override
    public void addTemplate(String moduleType, String templatePath) {
        this.templatePathMap.put(getTemplateType() + "_" + moduleType + "_true", templatePath);
        this.templatePathMap.put(getTemplateType() + "_" + moduleType + "_false", templatePath);
    }

    /**
     * 获取模板路径
     * @param moduleType 模块类型
     * @param hasPrivateKey 是否有主键
     * @return 模板路径
     */
    protected String getTemplatePath(String moduleType, boolean hasPrivateKey) {
        return this.templatePathMap.get(getTemplateType() + "_" + moduleType + "_" + hasPrivateKey);
    }

    @Override
    public void generate(GenerateMetaData generateMetaData) {
        try {
            Map<String, List<ModulePathInfo>> tableInfosMap = generateMetaData.getModulePathInfoMap();
            for (Map.Entry<String, List<ModulePathInfo>> entry : tableInfosMap.entrySet()) {
                List<ModulePathInfo> value = entry.getValue();
                GenerateInfo generateInfo = generateMetaData.getGenerateInfoByTableName(entry.getKey());
                TableInfo tableInfo = generateInfo.getTableInfo();
                LogUtils.info(">>>>>>>>>>>>>>>>> generate [" + tableInfo.getTableName() + "] start <<<<<<<<<<<<<<<");
                LogUtils.info(tableInfo.getTableName() + " 表共" + tableInfo.getColumnLists().size() + "列");
                LogUtils.logGenerateInfo(generateInfo);
                boolean hasPrimaryKey = tableInfo.isHasPrimaryKey();
                for (int i = 0; i < value.size(); i++) {
                    ModulePathInfo templateGenerateInfo = value.get(i);
                    String templatePath = this.getTemplatePath(templateGenerateInfo.getModuleType(), hasPrimaryKey);
                    if (GeneratorStringUtils.isNotBlank(templatePath)){
                        Freemarker.printFile(templatePath,
                                templateGenerateInfo.getFileOutputPath(), generateInfo);
                        LogUtils.info("文件输出路径:"+templateGenerateInfo.getFileOutputPath());
                    }
                }
                LogUtils.info(">>>>>>>>>>>>>>>>> generate [" + tableInfo.getTableName() + "] end <<<<<<<<<<<<<<<<<");
            }
        }catch (Exception e){
            LogUtils.error("FreemarkerGenerateService.generate error!");
            LogUtils.printException(e);
        }
    }

}
