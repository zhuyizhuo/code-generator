package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.dto.FilePathInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.XmlNameFormatEnums;
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
    private static final String point = ".";

    @Value("#{generate.base.out-put-path}")
    private String baseOutputPath;
    @Value("#{generate.base.java.out-put-path}")
    private String baseJavaOutputPath;
    @Value("#{generate.base.resources.out-put-path}")
    private String baseResourcesOutputPath;

    @Value("#{generate.java.base.package}")
    private String basePackage;
    @Value("#{generate.java.mapper.package}")
    private String mapperPackage;
    @Value("#{generate.java.pojo.package}")
    private String pojoPackage;

    private Map<ModuleTypeEnums, FormatService> nameFormatMap = new HashMap<>();
    /***
     *  moduleTpye ->  JavaClassDefinition
     */
    private Map<String,JavaClassDefinition> javaClassDefinition = new ConcurrentHashMap<>();

    private FormatService formatService = (tableName) -> tableName.toLowerCase();

    /** 实体类输出路径 */
    @Value("#{generate.java.pojo.path}")
    private String pojoOutPutPath;
    /** dao输出路径 */
    @Value("#{generate.java.mapper.path}")
    private String daoOutPutPath;
    /** mybatis xml文件输出路径 */
    @Value("#{generate.resources.xml.out-put-path}")
    private String xmlOutPutPath;

    @Value("#{generate.java.base.package.enabled}")
    private String basePackageEnabled;

    /** 实体类输出全路径 */
    private String pojoOutPutFullPath;
    /** dao输出全路径 */
    private String daoOutPutFullPath;
    /** mybatis xml文件输出全路径 */
    private String xmlOutPutFullPath;

    private Map<ModuleTypeEnums,FilePathInfo> pathInfoMap = new ConcurrentHashMap<>();

    public FileOutPathInfo() {

    }

    public void init(Map<ModuleTypeEnums, FormatService> nameFormatMap) {
        String daoFullPackage = mapperPackage;
        String pojoFullPackage = pojoPackage;
        if (GeneratorStringUtils.isNotBlank(basePackage)) {
            /** dao层包全路径 */
            daoFullPackage = basePackage + point + mapperPackage;
            /** 实体包全路径 */
            pojoFullPackage = basePackage + point + pojoPackage;
        }

        ModuleTypeEnums[] values = ModuleTypeEnums.values();
        for (int i = 0; i < values.length; i++) {
            String fileNameFormatKey = values[i].getFileNameFormatKey();
            String config = PropertiesUtils.getConfig(fileNameFormatKey);

        }

        javaClassDefinition.put(ModuleTypeEnums.MAPPER.getModuleType(), new JavaClassDefinition(daoFullPackage));
        javaClassDefinition.put(ModuleTypeEnums.POJO.getModuleType(), new JavaClassDefinition(pojoFullPackage));
        if (nameFormatMap != null) {
            this.nameFormatMap = nameFormatMap;
        }

        /*ModuleTypeEnums[] values = ModuleTypeEnums.values();
        for (int i = 0; i < values.length; i++) {
            String fullPackage = javaClassDefinitionMap.get(values[i]).getFullPackage();
            String s = changePackage2Path(fullPackage);
        }*/
        if ("TRUE".equalsIgnoreCase(basePackageEnabled)){
            this.pojoOutPutPath = getJavaFileOutPutFullPath(changePackage2Path(pojoFullPackage));
            this.daoOutPutPath = getJavaFileOutPutFullPath(changePackage2Path(daoFullPackage));
            this.xmlOutPutPath = baseOutputPath + baseResourcesOutputPath + this.xmlOutPutPath + "/";
        } else {
            this.pojoOutPutPath = getJavaFileOutPutFullPath(this.pojoOutPutPath);
            this.daoOutPutPath = getJavaFileOutPutFullPath(this.daoOutPutPath);
            this.xmlOutPutPath = baseOutputPath + baseResourcesOutputPath + this.xmlOutPutPath + "/";
        }
    }

    private String getJavaFileOutPutFullPath(String filePath) {
        return baseOutputPath + baseJavaOutputPath + "/" + filePath + "/";
    }

    public String changePackage2Path(String packagePath){
        return packagePath.replaceAll("\\.","/");
    }

    public String getPojoOutPutFullPath() {
        return pojoOutPutFullPath;
    }

    public String getDaoOutPutFullPath() {
        return daoOutPutFullPath;
    }

    public String getXmlOutPutFullPath() {
        return xmlOutPutFullPath;
    }

    private String initXmlName(String tableName) {
        String xmlNameFormat = PropertiesUtils.getProperties(ConfigConstants.XML_NAME_FORMAT);
        return XmlNameFormatEnums.CAMEL.toString().equalsIgnoreCase(xmlNameFormat)
                ? GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName, ConfigConstants.tableRegex)
                : this.formatService.format(tableName);
    }

    public void addXmlNameFormat(FormatService formatService) {
        this.formatService = formatService;
    }

    public String getBasePackageEnabled() {
        return basePackageEnabled;
    }

    public void setBasePackageEnabled(String basePackageEnabled) {
        this.basePackageEnabled = basePackageEnabled;
    }



    /**
     *  格式化类名
     */
    private String classNameFormat(ModuleTypeEnums moduleType, String javaTableName) {
        String properties = PropertiesUtils.getProperties(moduleType.getFileNameFormatKey());
        return MessageFormat.format(GeneratorStringUtils.isNotBlank(properties)
                        ? properties
                        : moduleType.getFileNameFormat()
                , javaTableName);
    }

    public Map<String,JavaClassDefinition> initFilesNameAndFormatPath(String tableName, String tableNameCamelCase) {

        ModuleTypeEnums[] values = ModuleTypeEnums.values();
        String className = "";
        for (int i = 0; i < values.length; i++) {
            if (getFormatService(values[i]) != null){
                className = getFormatService(values[i]).format(tableName);
            } else {
                className = classNameFormat(values[i], tableNameCamelCase);
            }
            JavaClassDefinition javaClassDefinition = this.javaClassDefinition.get(values[i].getModuleType());
            if (javaClassDefinition != null){
                javaClassDefinition.setClassName(className);
            }
        }

        this.pojoOutPutFullPath = pojoOutPutPath + javaClassDefinition.get(ModuleTypeEnums.POJO.getModuleType()).getClassName() + ".java";
        this.daoOutPutFullPath = daoOutPutPath + javaClassDefinition.get(ModuleTypeEnums.MAPPER.getModuleType()).getClassName() +".java";
        this.xmlOutPutFullPath = xmlOutPutPath + initXmlName(tableName) + ".xml";

        return javaClassDefinition;
    }

    private FormatService getFormatService(ModuleTypeEnums moduleType) {
        return nameFormatMap.get(moduleType);
    }

    public void addFormatService(ModuleTypeEnums moduleType, FormatService formatService) {
        this.nameFormatMap.put(moduleType, formatService);
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }

}
