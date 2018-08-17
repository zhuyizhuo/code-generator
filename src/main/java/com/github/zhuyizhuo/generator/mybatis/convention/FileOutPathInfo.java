package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
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
    /** 基路径 */
    private String basePath;
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

    public String initBasePath(){
        this.basePath = PropertiesUtils.getProperties(ConfigConstants.FILE_OUT_PUT_PATH);
        if (GeneratorStringUtils.isBlank(basePath)){
            basePath = System.getProperty("user.dir") + "/src/main/java/";
        }
        this.basePath += "/";
        return this.basePath;
    }

    public void init(StratificationInfo stratificationInfo) {
        initBasePath();

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
            this.xmlOutPutPath = getXmlOutPutPath(changePackage2Path(stratificationInfo.getXmlFullPackage()));
        } else {
            this.xmlOutPutPath = getXmlOutPutPath(this.xmlOutPutPath);
        }
    }

    public void formatPath(StratificationInfo stratificationInfo){
        this.pojoOutPutFullPath = MessageFormat.format(pojoOutPutPath,stratificationInfo.getPojoName());
        this.daoOutPutFullPath = MessageFormat.format(daoOutPutPath,stratificationInfo.getDaoName());
        this.xmlOutPutFullPath = MessageFormat.format(xmlOutPutPath,stratificationInfo.getXmlName());
    }

    private String getJavaFileOutPutFullPath(String filePath) {
        return basePath + filePath + "/{0}.java";
    }

    private String getXmlOutPutPath(String xmlFilePath) {
        return basePath + xmlFilePath + "/{0}.xml";
    }

    public String changePackage2Path(String packagePath){
        return packagePath.replaceAll("\\.","/");
    }

    public String getPojoOutPutPath() {
        return pojoOutPutPath;
    }

    public void setPojoOutPutPath(String pojoOutPutPath) {
        this.pojoOutPutPath = pojoOutPutPath;
    }

    public String getDaoOutPutPath() {
        return daoOutPutPath;
    }

    public void setDaoOutPutPath(String daoOutPutPath) {
        this.daoOutPutPath = daoOutPutPath;
    }

    public String getXmlOutPutPath() {
        return xmlOutPutPath;
    }

    public void setXmlOutPutPath(String xmlOutPutPath) {
        this.xmlOutPutPath = xmlOutPutPath;
    }

    public String getPojoOutPutFullPath() {
        return pojoOutPutFullPath;
    }

    public void setPojoOutPutFullPath(String pojoOutPutFullPath) {
        this.pojoOutPutFullPath = pojoOutPutFullPath;
    }

    public String getDaoOutPutFullPath() {
        return daoOutPutFullPath;
    }

    public void setDaoOutPutFullPath(String daoOutPutFullPath) {
        this.daoOutPutFullPath = daoOutPutFullPath;
    }

    public String getXmlOutPutFullPath() {
        return xmlOutPutFullPath;
    }

    public void setXmlOutPutFullPath(String xmlOutPutFullPath) {
        this.xmlOutPutFullPath = xmlOutPutFullPath;
    }
}
