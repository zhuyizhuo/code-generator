package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.annotation.Value;
import com.github.zhuyizhuo.generator.enums.FileTypeEnums;
import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ModuleInfo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 包路径及文件输出路径信息 <br>
 *
 * @author zhuo <br>
 * @since  1.0
 */
@CoventionClass
public class FileOutPathInfo {

    @Value("#{generate.base.out-put-path}")
    private String baseOutputPath;
    /**  true 则生成完整目录 false 则仅生成目录最后一层 */
    @Value("#{generate.java.base.package.enabled}")
    private String basePackageEnabled;

    private String tableName;
    private String tableNameCamelCase;
    /**
     *  类名格式化 Service Map
     *  ModuleType -> 类名格式化 Service
     */
    private Map<String, FormatService> classNameFormatServiceMap;
    /**
     * 模块类型 -> 模块的格式化信息
     * moduleType ->  ModuleInfo
     */
    private Map<String, ModuleInfo> moduleInfoMap;
    /** 模块信息 */
    private List<ModuleInfo> modules;
    /**  moduleType -> java 类定义 Map  */
    private Map<String,JavaClassDefinition> javaClassDefinitionMap;

    /**
     * 初始化模块信息
     */
    public void init() {
        ModuleTypeEnums[] values = ModuleTypeEnums.values();
        String outPutFullPathFormat = "";
        ModuleInfo info;
        String filePackage;
        String outPutPath;
        for (int i = 0; i < values.length; i++) {
            ModuleTypeEnums module = values[i];
            filePackage = ContextHolder.getConfig(module.getFilePackageKey());
            outPutPath = ContextHolder.getConfig(module.getOutputPathKey());
            if (FileTypeEnums.JAVA.equals(module.getFileType())){
                if (filePackage.startsWith(".")){
                    filePackage = filePackage.substring(1);
                }
                outPutFullPathFormat = getJavaOutputFullPath(outPutPath);
            } else if (FileTypeEnums.XML.equals(module.getFileType())){
                outPutFullPathFormat = getResourcesOutputFullPath(outPutPath);
            }
            info = new ModuleInfo();
            info.setModuleType(module.name());
            info.setFileType(module.getFileType());
            info.setFileFullPackage(filePackage);
            info.setOutPutFullPathFormatPattern(outPutFullPathFormat);
            info.setFileNameFormatServie(getFormatService(module));
            info.setFileNameFormatPattern(ContextHolder.getConfig(module.getFileNameFormatKey()));
            addModuleInfo(module, info);
        }
    }

    public void setClassNameFormatServiceMap(Map<String, FormatService> classNameFormatServieMap) {
        this.classNameFormatServiceMap = classNameFormatServieMap;
    }

    /**
     * 根据路径获取资源文件输出全路径
     * @param resourcesOutPutPath 资源文件的输出路径
     * @return 资源文件输出全路径
     */
    public String getResourcesOutputFullPath(String resourcesOutPutPath) {
        String outPutFullPath = resourcesOutPutPath;
        if (!"TRUE".equalsIgnoreCase(basePackageEnabled)) {
            String tempPath = resourcesOutPutPath.replaceAll("\\\\","/");
            if (tempPath.lastIndexOf("/") != -1){
                tempPath = tempPath.substring(tempPath.lastIndexOf("/") + 1);
            }
            outPutFullPath = baseOutputPath + "/" + tempPath;
        }
        return outPutFullPath + "/{0}.xml";
    }

    /***
     *  将路径中的 . 替换为 / ,将路径处理为正常文件路径
     *
     * @param outPutPath java 文件输出路径 例如 /xxx/src/main/java/com.github.generator
     * @return java 文件输出全路径
     */
    public String getJavaOutputFullPath(String outPutPath){
        String outPutFullPath = outPutPath;
        int packageStart = outPutFullPath.lastIndexOf(">");
        if(packageStart != -1){
            String basePath = outPutFullPath.substring(0, packageStart);
            String fullPackage = outPutFullPath.substring(packageStart + 1);
            if (fullPackage.contains(".")){
                outPutFullPath = basePath + fullPackage.replaceAll("\\.", "/");
            }
        }

        if (!"TRUE".equalsIgnoreCase(basePackageEnabled)) {
            if(outPutFullPath.lastIndexOf("/") != -1){
                outPutFullPath = outPutFullPath.substring(outPutFullPath.lastIndexOf("/") + 1);
            }
            outPutFullPath = baseOutputPath + "/" + outPutFullPath;
        }
        return outPutFullPath + "/{0}.java";
    }

    /**
     *  初始化类名 及 输出全路径
     * @param tableName 表名
     * @param tableNameCamelCase 表名驼峰命名
     */
    public void initFileNamesAndOutPutFullPath(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.tableNameCamelCase = tableNameCamelCase;

        String fileName = "";
        this.modules = new ArrayList<>();
        this.javaClassDefinitionMap = new ConcurrentHashMap<>();
        for (Map.Entry<String, ModuleInfo> entry : moduleInfoMap.entrySet()) {
            String moduleType = entry.getKey();
            ModuleInfo moduleInfo = entry.getValue();
            FormatService fileNameFormatServie = moduleInfo.getFileNameFormatServie();
            if (fileNameFormatServie != null) {
                fileName = fileNameFormatServie.format(tableName);
            } else {
                fileName = fileNameFormat(moduleInfo.getFileType(), moduleInfo.getFileNameFormatPattern());
            }

            if (FileTypeEnums.JAVA.equals(moduleInfo.getFileType())){
                String fileFullPackage = moduleInfo.getFileFullPackage();
                this.javaClassDefinitionMap.put(moduleType, new JavaClassDefinition(fileFullPackage, fileName));
            }
            // 设置文件输出全路径
            moduleInfo.setOutPutFullPathByFileName(fileName);
            this.modules.add(moduleInfo);
        }
    }

    /**
     *  新增 java 模板
     * @param fileInfo java 模块信息
     */
    public void addJavaTemplate(JavaModuleInfo fileInfo) {
        ModuleInfo moduleInfo = getModuleInfo(fileInfo.getModuleType());
        if (moduleInfo == null){
            moduleInfo = new ModuleInfo();
            addModuleInfo(fileInfo.getModuleType(), moduleInfo);
        }
        String outPutFullPathFormat = getJavaOutputFullPath(fileInfo.getOutputPath());
        moduleInfo.setModuleType(fileInfo.getModuleType());
        moduleInfo.setOutPutFullPathFormatPattern(outPutFullPathFormat);
        // 仅 java 模块需设置包路径
        moduleInfo.setFileFullPackage(fileInfo.getClassPackage());
        moduleInfo.setFileNameFormatServie(getFormatService(fileInfo.getModuleType()));
        moduleInfo.setFileType(FileTypeEnums.JAVA);
        moduleInfo.setFileNameFormatPattern(fileInfo.getClassNameFormat());
    }

    /**
     * 新增自定义模块模板
     * @param fileInfo 自定义模块信息
     */
    public void addCustomizeModule(CustomizeModuleInfo fileInfo) {
        ModuleInfo moduleInfo = getModuleInfo(fileInfo.getModuleType());
        if (moduleInfo == null){
            moduleInfo = new ModuleInfo();
            addModuleInfo(fileInfo.getModuleType(), moduleInfo);
        }

        moduleInfo.setModuleType(fileInfo.getModuleType());
        moduleInfo.setFileNameFormatPattern(fileInfo.getFileNameFormatPattern());
        moduleInfo.setFileNameFormatServie(getFormatService(fileInfo.getModuleType()));
        moduleInfo.setOutPutFullPathFormatPattern(fileInfo.getOutPutFullPathFormatPattern());
    }

    /**
     *  获取所有模块信息
     * @return 所有模块信息
     */
    public List<ModuleInfo> getAllModule(){
        return this.modules;
    }

    public Map<String,JavaClassDefinition> getJavaClassDefinitionMap(){
        return this.javaClassDefinitionMap;
    }

    public void setBasePackageEnabled(String basePackageEnabled) {
        this.basePackageEnabled = basePackageEnabled;
    }

    public void setBaseOutputPath(String baseOutputPath) {
        this.baseOutputPath = baseOutputPath;
    }

    private void addModuleInfo(ModuleTypeEnums value, ModuleInfo info) {
        addModuleInfo(value.toString(), info);
    }

    private void addModuleInfo(String moduleType, ModuleInfo info) {
        if (this.moduleInfoMap == null){
            this.moduleInfoMap = new ConcurrentHashMap<>();
        }
        this.moduleInfoMap.put(moduleType, info);
    }

    private ModuleInfo getModuleInfo(String moduleType) {
        return this.moduleInfoMap.get(moduleType);
    }

    /**
     *  格式化类名
     */
    private String fileNameFormat(FileTypeEnums typeEnums,String formatConfig) {
        if (FileTypeEnums.XML.equals(typeEnums)){
            return MessageFormat.format(formatConfig, tableName.toLowerCase());
        }
        return MessageFormat.format(formatConfig, tableNameCamelCase);
    }

    private FormatService getFormatService(ModuleTypeEnums moduleType) {
        return getFormatService(moduleType.toString());
    }

    private FormatService getFormatService(String moduleType) {
        return classNameFormatServiceMap == null ? null : classNameFormatServiceMap.get(moduleType);
    }
}