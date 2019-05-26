package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.enums.FileTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: FileOutPathInfo <br>
 * description: 包路径 及 文件输出路径信息 <br>
 *
 * @author yizhuo <br>
 * @since  1.0
 * @version 1.4.0
 */
@CoventionClass
public class FileOutPathInfo {

    @Value("#{generate.base.out-put-path}")
    private String baseOutputPath;
    @Value("#{generate.base.java.out-put-path}")
    private String baseJavaOutputPath;
    @Value("#{generate.base.resources.out-put-path}")
    private String baseResourcesOutputPath;

    /** mybatis xml文件输出路径 */
    @Value("#{generate.resources.xml.out-put-path}")
    private String xmlOutPutPath;

    @Value("#{generate.java.base.package}")
    private String basePackage;
    /**  true 则生成完整目录 false 则仅生成目录最后一层 */
    @Value("#{generate.java.base.package.enabled}")
    private String basePackageEnabled;

    /**
     *  类名格式化 Service Map
     *  ModuleEnums -> 类名格式化 Service
     */
    private Map<ModuleEnums, FormatService> classNameFormatServieMap = new HashMap<>();
    /***
     *  moduleTpye ->  JavaClassDefinition
     */
    private Map<ModuleEnums,JavaClassDefinition> javaClassDefinition = new ConcurrentHashMap<>();
    /**
     *  输出路径 MAP
     *  moduleTpye -> 输出全路径
     */
    private Map<ModuleEnums,String> outPutPathMap = new ConcurrentHashMap<>();
    /***
     *  输出路径格式 Map
     *  moduleTpye -> 输出路径格式模板
     */
    private Map<ModuleEnums,String> outPutPathTemplateMap = new ConcurrentHashMap<>();


    public void init(Map<ModuleEnums, FormatService> classNameFormatServieMap) {
        ModuleEnums[] values = ModuleEnums.values();
        String outPutFullPath = "";
        for (int i = 0; i < values.length; i++) {
            String fileFullPackage = PropertiesUtils.getConfig(values[i].getFilePackageKey());
            if (FileTypeEnums.JAVA.equals(values[i].getTypeEnums())){
                if (GeneratorStringUtils.isNotBlank(basePackage)) {
                    fileFullPackage = basePackage + "." + fileFullPackage;
                }
                this.javaClassDefinition.put(values[i], new JavaClassDefinition(fileFullPackage));

                if ("TRUE".equalsIgnoreCase(basePackageEnabled)) {
                    outPutFullPath = baseOutputPath + baseJavaOutputPath + "/" + fileFullPackage.replaceAll("\\.", "/");
                } else {
                    int index = fileFullPackage.lastIndexOf(".");
                    if(index != -1){
                        fileFullPackage = fileFullPackage.substring(index + 1);
                    }
                    outPutFullPath = baseOutputPath + "/" + fileFullPackage;
                }
                outPutFullPath += "/{0}.java";
            } else {
                if ("TRUE".equalsIgnoreCase(basePackageEnabled)) {
                    outPutFullPath = baseOutputPath + baseResourcesOutputPath + this.xmlOutPutPath;
                } else {
                    outPutFullPath = baseOutputPath + this.xmlOutPutPath;
                }
                outPutFullPath += "/{0}.xml";
            }
            this.outPutPathTemplateMap.put(values[i], outPutFullPath);
        }

        if (classNameFormatServieMap != null) {
            this.classNameFormatServieMap = classNameFormatServieMap;
        }
    }

    public Map<String,JavaClassDefinition> initFilesNameAndFormatPath(String tableName, String tableNameCamelCase) {
        ModuleEnums[] values = ModuleEnums.values();
        String fileName = "";
        Map<String,JavaClassDefinition> javaClassDefinitionResp = new ConcurrentHashMap<>();
        for (int i = 0; i < values.length; i++) {
            if (getFormatService(values[i]) != null){
                fileName = getFormatService(values[i]).format(tableName);
            } else {
                fileName = fileNameFormat(values[i], tableNameCamelCase);
            }
            if (FileTypeEnums.JAVA.equals(values[i].getTypeEnums())){
                outPutPathMap.put(values[i], MessageFormat.format(outPutPathTemplateMap.get(values[i]),fileName ));
                JavaClassDefinition javaClassDefinition = this.javaClassDefinition.get(values[i]);
                if (javaClassDefinition != null){
                    javaClassDefinition.setClassName(fileName);
                }
                javaClassDefinitionResp.put(values[i].toString(),new JavaClassDefinition(javaClassDefinition.getFullPackage(),fileName));
            } else {
                outPutPathMap.put(values[i], MessageFormat.format(outPutPathTemplateMap.get(values[i]), tableName.toLowerCase()));
            }
        }
        return javaClassDefinitionResp;
    }

    /**
     *  根据类型 获取输出的全路径
     * @param moduleType 模块类型
     * @return 输出文件全路径
     */
    public String getOutputFullPath(ModuleEnums moduleType) {
        return this.outPutPathMap.get(moduleType);
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setBasePackageEnabled(String basePackageEnabled) {
        this.basePackageEnabled = basePackageEnabled;
    }

    /**
     *  格式化类名
     */
    private String fileNameFormat(ModuleEnums moduleType, String javaTableName) {
        String properties = PropertiesUtils.getConfig(moduleType.getFileNameFormatKey());
        return MessageFormat.format(properties, javaTableName);
    }

    private FormatService getFormatService(ModuleEnums moduleType) {
        return classNameFormatServieMap.get(moduleType);
    }

}
