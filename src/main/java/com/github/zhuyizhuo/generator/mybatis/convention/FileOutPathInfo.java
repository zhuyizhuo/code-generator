package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Nullable;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.enums.FileTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: FileOutPathInfo <br>
 * description: 文件输出路径信息 <br>
 *
 * @author yizhuo <br>
 * @version 1.0
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
     *  ModuleTypeEnums -> 类名格式化 Service
     */
    private Map<ModuleTypeEnums, FormatService> classNameFormatServieMap = new HashMap<>();
    /***
     *  moduleTpye ->  JavaClassDefinition
     */
    private Map<ModuleTypeEnums,JavaClassDefinition> javaClassDefinition = new ConcurrentHashMap<>();
    /**
     *  输出路径 MAP
     *  moduleTpye -> 输出全路径
     */
    private Map<ModuleTypeEnums,String> outPutPathMap = new ConcurrentHashMap<>();


    public void init(Map<ModuleTypeEnums, FormatService> classNameFormatServieMap) {
        ModuleTypeEnums[] values = ModuleTypeEnums.values();
        String outPutPath = "";
        for (int i = 0; i < values.length; i++) {
            String fileFullPackage = PropertiesUtils.getConfig(values[i].getFilePackageKey());
            if (FileTypeEnums.JAVA.equals(values[i].getTypeEnums())){
                if (GeneratorStringUtils.isNotBlank(basePackage)) {
                    fileFullPackage = basePackage + "." + fileFullPackage;
                }
                javaClassDefinition.put(values[i], new JavaClassDefinition(fileFullPackage));

                if ("TRUE".equalsIgnoreCase(basePackageEnabled)) {
                    outPutPath = baseOutputPath + baseJavaOutputPath + "/" + fileFullPackage.replaceAll("\\.", "/") + "/";
                } else {
                    int index = fileFullPackage.lastIndexOf(".");
                    if(index != -1){
                        fileFullPackage = fileFullPackage.substring(index + 1);
                    }
                    outPutPath = baseOutputPath + "/" + fileFullPackage + "/";
                }
            } else {
                if ("TRUE".equalsIgnoreCase(basePackageEnabled)) {
                    outPutPath = baseOutputPath + baseResourcesOutputPath + this.xmlOutPutPath + "/";
                } else {
                    outPutPath = baseOutputPath + this.xmlOutPutPath + "/";
                }
            }
            outPutPathMap.put(values[i], outPutPath);
        }

        if (classNameFormatServieMap != null) {
            this.classNameFormatServieMap = classNameFormatServieMap;
        }
    }

    public Map<String,JavaClassDefinition> initFilesNameAndFormatPath(String tableName, String tableNameCamelCase) {
        ModuleTypeEnums[] values = ModuleTypeEnums.values();
        String fileName = "";
        Map<String,JavaClassDefinition> javaClassDefinitionResp = new ConcurrentHashMap<>();
        for (int i = 0; i < values.length; i++) {
            if (getFormatService(values[i]) != null){
                fileName = getFormatService(values[i]).format(tableName);
            } else {
                fileName = fileNameFormat(values[i], tableNameCamelCase);
            }
            if (FileTypeEnums.JAVA.equals(values[i].getTypeEnums())){
                outPutPathMap.put(values[i], outPutPathMap.get(values[i]) + fileName + ".java");
                JavaClassDefinition javaClassDefinition = this.javaClassDefinition.get(values[i]);
                if (javaClassDefinition != null){
                    javaClassDefinition.setClassName(fileName);
                }
                javaClassDefinitionResp.put(values[i].toString(),javaClassDefinition);
            } else {
                outPutPathMap.put(values[i], outPutPathMap.get(ModuleTypeEnums.XML) + fileName + ".xml");
            }
        }
        return javaClassDefinitionResp;
    }

    /**
     *  根据类型 获取输出的全路径
     * @param moduleType 模块类型
     * @return 输出文件全路径
     */
    public String getOutputFullPath(ModuleTypeEnums moduleType) {
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
    private String fileNameFormat(ModuleTypeEnums moduleType, String javaTableName) {
        String properties = PropertiesUtils.getConfig(moduleType.getFileNameFormatKey());
        return MessageFormat.format(properties, javaTableName);
    }

    private FormatService getFormatService(ModuleTypeEnums moduleType) {
        return classNameFormatServieMap.get(moduleType);
    }

}
