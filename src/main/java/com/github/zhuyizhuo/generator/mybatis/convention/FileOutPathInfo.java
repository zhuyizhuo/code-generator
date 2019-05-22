package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;

/**
 * class: FileOutPathInfo <br>
 * description: 文件输出路径信息 <br>
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class FileOutPathInfo {
    private final String XML_FILE_PATH = "mappers";

    private FormatService formatService = (tableName) -> tableName.toLowerCase();

    /** java 基础路径 */
    private String baseJavaPath;
    /** 资源文件基础路径 */
    private String baseResourcesPath;
    /** 实体类输出路径 */
    private String pojoOutPutPath;
    /** dao输出路径 */
    private String daoOutPutPath;
    /** mybatis xml文件输出路径 */
    private String xmlOutPutPath;

    /** 实体类输出全路径 */
    private String pojoOutPutFullPath;
    /** dao输出全路径 */
    private String daoOutPutFullPath;
    /** mybatis xml文件输出全路径 */
    private String xmlOutPutFullPath;

    public FileOutPathInfo() {

    }

    public String getBasePath(){
        String basePath = "";
        basePath = PropertiesUtils.getProperties(ConfigConstants.FILE_OUT_PUT_PATH);
        if (GeneratorStringUtils.isBlank(basePath)){
            basePath = System.getProperty("user.dir");
        }
        basePath += "/";
        return basePath;
    }

    public void init(StratificationInfo stratificationInfo) {
        String basePath = getBasePath();
        this.baseJavaPath = basePath + "/src/main/java/";
        this.baseResourcesPath = basePath + "/src/main/resources/";

        if (PropertiesUtils.containsKey(ConfigConstants.XML_OUT_PUT_PATH)){
            setXmlOutPutPath(PropertiesUtils.getProperties(ConfigConstants.XML_OUT_PUT_PATH));
        };
        if (PropertiesUtils.containsKey(ConfigConstants.DAO_OUT_PUT_PATH)){
            setDaoOutPutPath(PropertiesUtils.getProperties(ConfigConstants.DAO_OUT_PUT_PATH));
        };
        if (PropertiesUtils.containsKey(ConfigConstants.POJO_OUT_PUT_PATH)){
            setPojoOutPutPath(PropertiesUtils.getProperties(ConfigConstants.POJO_OUT_PUT_PATH));
        };

        if (GeneratorStringUtils.isBlank(pojoOutPutPath)){
            this.pojoOutPutPath = getJavaFileOutPutFullPath(changePackage2Path(stratificationInfo.getPojoFullPackage()));
        } else {
            this.pojoOutPutPath = getJavaFileOutPutFullPath(this.pojoOutPutPath);
        }
        if (GeneratorStringUtils.isBlank(daoOutPutPath)){
            this.daoOutPutPath = getJavaFileOutPutFullPath(changePackage2Path(stratificationInfo.getDaoFullPackage()));
        } else {
            this.daoOutPutPath = getJavaFileOutPutFullPath(this.daoOutPutPath);
        }
        if (GeneratorStringUtils.isBlank(xmlOutPutPath)){
            this.xmlOutPutPath = getXmlOutPutPath(XML_FILE_PATH);
        } else {
            this.xmlOutPutPath = getXmlOutPutPath(this.xmlOutPutPath);
        }
    }

    public void formatPath(StratificationInfo stratificationInfo){
        this.pojoOutPutFullPath = MessageFormat.format(pojoOutPutPath,stratificationInfo.getPojoName());
        this.daoOutPutFullPath = MessageFormat.format(daoOutPutPath,stratificationInfo.getDaoName());
        this.xmlOutPutFullPath = MessageFormat.format(xmlOutPutPath,initXmlName(stratificationInfo.getTableName()));
    }

    private String getJavaFileOutPutFullPath(String filePath) {
        return baseJavaPath + filePath + "/{0}.java";
    }

    private String getXmlOutPutPath(String xmlFilePath) {
        return baseResourcesPath + xmlFilePath + "/{0}.xml";
    }

    public String changePackage2Path(String packagePath){
        return packagePath.replaceAll("\\.","/");
    }

    public void setPojoOutPutPath(String pojoOutPutPath) {
        this.pojoOutPutPath = pojoOutPutPath;
    }

    public void setDaoOutPutPath(String daoOutPutPath) {
        this.daoOutPutPath = daoOutPutPath;
    }

    public void setXmlOutPutPath(String xmlOutPutPath) {
        this.xmlOutPutPath = xmlOutPutPath;
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
        return "camel".equalsIgnoreCase(xmlNameFormat)
                ? GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName, ConfigConstants.tableRegex)
                : this.formatService.formatTableName(tableName);
    }

    public void addXmlNameFormat(FormatService formatService) {
        this.formatService = formatService;
    }

}
