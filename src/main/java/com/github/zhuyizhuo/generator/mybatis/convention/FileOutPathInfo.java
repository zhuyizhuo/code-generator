package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.enums.FileTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ModuleInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /** mybatis xml文件输出路径 */
    @Value("#{generate.resources.xml.out-put-path}")
    private String xmlOutPutPath;

    /**  true 则生成完整目录 false 则仅生成目录最后一层 */
    @Value("#{generate.java.base.package.enabled}")
    private String basePackageEnabled;

    private String tableName;
    private String tableNameCamelCase;
    /**
     *  类名格式化 Service Map
     *  ModuleType -> 类名格式化 Service
     */
    private Map<String, FormatService> classNameFormatServieMap = new HashMap<>();
    /**
     * moduleTpye ->  ModuleInfo
     */
    private Map<String, ModuleInfo> moduleInfoMap = new ConcurrentHashMap<>();
    /**
     *  表模块路径 map
     *  tablename_moduleType -> fileName
     */
    private Map<String,String> tableFileNamaMap = new ConcurrentHashMap<>();

    public void init() {
        ModuleEnums[] values = ModuleEnums.values();
        String outPutFullPathFormat = "";
        ModuleInfo info;
        for (int i = 0; i < values.length; i++) {
            String filePackage = PropertiesUtils.getConfig(values[i].getFilePackageKey());
            String outPutPath = PropertiesUtils.getConfig(values[i].getOutputPathKey());
            info = new ModuleInfo();
            if (FileTypeEnums.JAVA.equals(values[i].getTypeEnums())){
                info.setFileFullPackage(filePackage);
                outPutFullPathFormat = getOutputFullPathByFullPackage(outPutPath, filePackage) + "{0}.java";
            } else {
                outPutFullPathFormat = getResourcesOutputFullPath(this.xmlOutPutPath) + "{0}.xml";
            }
            info.setOutPutFullPathFormat(outPutFullPathFormat);
            info.setModuleName(values[i].toString());
            addModuleInfo(values[i], info);
        }
    }

    private void addModuleInfo(ModuleEnums value, ModuleInfo info) {
        this.moduleInfoMap.put(value.toString(), info);
    }

    private ModuleInfo getModuleInfo(ModuleEnums value) {
        return this.moduleInfoMap.get(value.toString());
    }

    public void setClassNameFormatServieMap(Map<String, FormatService> classNameFormatServieMap) {
        if (classNameFormatServieMap != null) {
            this.classNameFormatServieMap = classNameFormatServieMap;
        }
    }

    /**
     * 根据路径获取资源文件输出全路径
     * @return 资源文件输出全路径
     */
    public String getResourcesOutputFullPath(String resourcesOutPutPath) {
        String outPutFullPath;
        if ("TRUE".equalsIgnoreCase(basePackageEnabled)) {
            outPutFullPath = baseOutputPath + resourcesOutPutPath;
        } else {
            String tempPath = resourcesOutPutPath.replaceAll("\\\\","/");
            if (tempPath.lastIndexOf("/") != -1){
                tempPath = tempPath.substring(tempPath.lastIndexOf("/") + 1);
            }
            outPutFullPath = baseOutputPath + tempPath;
        }
        return outPutFullPath + "/";
    }

    /***
     *  根据文件包名获取文件输出全路径
     *
     * @param outPutPath
     * @param fileFullPackage
     * @return java 文件输出全路径
     */
    public String getOutputFullPathByFullPackage(String outPutPath, String fileFullPackage) {
        String outPutFullPath;
        if ("TRUE".equalsIgnoreCase(basePackageEnabled)) {
            outPutFullPath = baseOutputPath + "/" + outPutPath + "/" + fileFullPackage.replaceAll("\\.", "/");
        } else {
            int index = fileFullPackage.lastIndexOf(".");
            if(index != -1){
                fileFullPackage = fileFullPackage.substring(index + 1);
            }
            outPutFullPath = baseOutputPath + "/" + fileFullPackage;
        }
        return outPutFullPath + "/";
    }

    /**
     *  返回 java 类定义
     * @param tableName 表名
     * @param tableNameCamelCase 表名转驼峰
     * @return map: moduleType -> java 类定义
     */
    public Map<String,JavaClassDefinition> initFileNames(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.tableNameCamelCase = tableNameCamelCase;
        ModuleEnums[] values = ModuleEnums.values();
        String fileName = "";
        /**
         *  moduleType -> java 类定义 Map
         */
        Map<String,JavaClassDefinition> javaClassDefinitionResp = new ConcurrentHashMap<>();
        for (int i = 0; i < values.length; i++) {
            fileName = getFileName(values[i], tableName);

            if (FileTypeEnums.JAVA.equals(values[i].getTypeEnums())){
                String fileFullPackage = getModuleInfo(values[i]).getFileFullPackage();
                javaClassDefinitionResp.put(values[i].toString(),
                        new JavaClassDefinition(fileFullPackage,fileName));
            }
        }
        // 扩展
        for (int i = 0; i < javaTemplates.size(); i++) {
            JavaModuleInfo fileInfo = javaTemplates.get(i);
            fileName = getFileName(fileInfo.getModuleType(), FileTypeEnums.JAVA, fileInfo.getClassNameFormat());

            javaClassDefinitionResp.put(fileInfo.getModuleType(),
                    new JavaClassDefinition(fileInfo.getClassPackage(), fileName));
        }
        return javaClassDefinitionResp;
    }

    private String getFileName(ModuleEnums moduleType, String tableName) {
        String key = tableName + "_" + moduleType;
        String fileName = this.tableFileNamaMap.get(key);
        if (GeneratorStringUtils.isNotBlank(fileName)){
            return fileName;
        }
        if (getFormatService(moduleType) != null){
            fileName = getFormatService(moduleType).format(tableName);
        } else {
            String formatConfig = PropertiesUtils.getConfig(moduleType.getFileNameFormatKey());
            fileName = fileNameFormat(moduleType.getTypeEnums(), formatConfig);
        }
        this.tableFileNamaMap.put(key, fileName);
        return fileName;
    }

    private String getFileName(String moduleType, FileTypeEnums typeEnums, String fileNameFormat) {
        String key = tableName + "_" + moduleType;
        String fileName = this.tableFileNamaMap.get(key);
        if (GeneratorStringUtils.isNotBlank(fileName)){
            return fileName;
        }
        if (getFormatService(moduleType) != null){
            fileName = getFormatService(moduleType).format(tableName);
        } else {
            fileName = fileNameFormat(typeEnums, fileNameFormat);
        }
        this.tableFileNamaMap.put(key, fileName);
        return fileName;
    }

    /**
     *  根据类型 获取输出的全路径
     * @param moduleType 模块类型
     * @return 输出文件全路径
     */
    public String getOutputFullPathByFullPackage(ModuleEnums moduleType, String tableName) {
        return MessageFormat.format(getModuleInfo(moduleType).getOutPutFullPathFormat(),
                getFileName(moduleType, tableName));
    }

    public void setBasePackageEnabled(String basePackageEnabled) {
        this.basePackageEnabled = basePackageEnabled;
    }

    /**
     *  格式化类名
     */
    private String fileNameFormat(FileTypeEnums typeEnums,String formatConfig) {
        if (FileTypeEnums.JAVA.equals(typeEnums)){
            return MessageFormat.format(formatConfig, tableNameCamelCase);
        } else {
            return MessageFormat.format(formatConfig, tableName.toLowerCase());
        }
    }

    private FormatService getFormatService(String moduleType) {
        return classNameFormatServieMap.get(moduleType);
    }

    private FormatService getFormatService(ModuleEnums moduleType) {
        return classNameFormatServieMap.get(moduleType.toString());
    }

    public String getJavaOutputFullPath(JavaModuleInfo fileInfo) {
        String outputFullPathByFullPackage = getOutputFullPathByFullPackage(fileInfo.getOutputPath(), fileInfo.getClassPackage());
        String fileName = getFileName(fileInfo.getModuleType(), FileTypeEnums.JAVA, fileInfo.getClassNameFormat()) + ".java";
        return outputFullPathByFullPackage + fileName;
    }

    public void addJavaTemplate(JavaModuleInfo fileInfo) {
        this.javaTemplates.add(fileInfo);
    }
    /** 扩展 */
    private List<JavaModuleInfo> javaTemplates = new ArrayList<>();
}
