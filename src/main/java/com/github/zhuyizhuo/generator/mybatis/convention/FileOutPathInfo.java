package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.FilePathInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.XmlNameFormatEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
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
    private final String XML_FILE_PATH = "/src/main/resources/mappers";

    private FormatService formatService = (tableName) -> tableName.toLowerCase();

    @Value("#{generate.java.base-out-put-path}")
    private String baseOutputPath;
    /** java 基础路径 */
    private String baseJavaPath;
    /** 资源文件基础路径 */
    private String baseResourcesPath;
    /** 实体类输出路径 */
    @Value("#{generate.java.pojo.path}")
    private String pojoOutPutPath;
    /** dao输出路径 */
    @Value("#{generate.java.mapper.path}")
    private String daoOutPutPath;
    /** mybatis xml文件输出路径 */
    @Value("#{generate.xml.out-put-path}")
    private String xmlOutPutPath;

    @Value("#{generate.java.base-package.enabled}")
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

    public String getBasePath(){
        if (GeneratorStringUtils.isBlank(baseOutputPath)){
            baseOutputPath = System.getProperty("user.dir");
        }
        baseOutputPath += "/";
        return this.baseOutputPath;
    }

    public void init(Map<String, JavaClassDefinition> javaClassDefinitionMap) {
        String basePath = getBasePath();
        this.baseJavaPath = basePath + "/src/main/java/";
        this.baseResourcesPath = basePath;

        /*ModuleTypeEnums[] values = ModuleTypeEnums.values();
        for (int i = 0; i < values.length; i++) {
            String fullPackage = javaClassDefinitionMap.get(values[i]).getFullPackage();
            String s = changePackage2Path(fullPackage);
        }*/
        if ("TRUE".equalsIgnoreCase(basePackageEnabled)){
            this.pojoOutPutPath = getJavaFileOutPutFullPath(changePackage2Path(javaClassDefinitionMap.get(ModuleTypeEnums.POJO.getModuleType()).getFullPackage()));
            this.daoOutPutPath = getJavaFileOutPutFullPath(changePackage2Path(javaClassDefinitionMap.get(ModuleTypeEnums.MAPPER.getModuleType()).getFullPackage()));
            this.xmlOutPutPath = baseResourcesPath + XML_FILE_PATH + "/{0}.xml";
        } else {
            this.pojoOutPutPath = getJavaFileOutPutFullPath(this.pojoOutPutPath);
            this.daoOutPutPath = getJavaFileOutPutFullPath(this.daoOutPutPath);
            this.xmlOutPutPath = baseResourcesPath + this.xmlOutPutPath + "/{0}.xml";
        }
    }

    public List<FilePathInfo> formatPath(Map<String, JavaClassDefinition> stratificationInfo, String tableName){
        String pojoName = stratificationInfo.get(ModuleTypeEnums.POJO.getModuleType()).getClassName();
        String mapperName = stratificationInfo.get(ModuleTypeEnums.MAPPER.getModuleType()).getClassName();
        this.pojoOutPutFullPath = MessageFormat.format(pojoOutPutPath,pojoName);
        this.daoOutPutFullPath = MessageFormat.format(daoOutPutPath,mapperName);
        this.xmlOutPutFullPath = MessageFormat.format(xmlOutPutPath,initXmlName(tableName));

        List<FilePathInfo> filePathInfos = new ArrayList<>();
        filePathInfos.add(new FilePathInfo(FtlPathInfo.pojoFtlPath, pojoOutPutFullPath));
        filePathInfos.add(new FilePathInfo(FtlPathInfo.NOKEY_MAPPER_TEMPLATE_PATH, daoOutPutFullPath));
        filePathInfos.add(new FilePathInfo(FtlPathInfo.NOKEY_MYBATIS_TEMPLATE_PATH, xmlOutPutFullPath));
        return filePathInfos;
    }

    private String getJavaFileOutPutFullPath(String filePath) {
        return baseJavaPath + filePath + "/{0}.java";
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
}
