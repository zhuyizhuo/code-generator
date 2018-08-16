package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;

import java.text.MessageFormat;

/**
 * 分层信息
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 16:19
 */
public class StratificationInfo {
    private static final String point = ".";

    /** 基础路径 */
    private String basePackage = "";

    /** 实体名称 */
    private String POJO_NAME_FORMAT = "{0}";
    /** service 层名称 */
    private String SERVICE_NAME_FORMAT = "{0}Service";
    /** service 实现类名称 */
    private String SERVICE_IMPL_NAME_FORMAT = "{0}ServiceImpl";
    /** dao 层名称 */
    private String DAO_NAME_FORMAT = "{0}Mapper";

    /** dao包路径 */
    private String daoPackage = "dao";
    /** service 接口层包路径 */
    private String servicePackage = "service";
    /** service 实现类包路径 */
    private String serviceImplPackage = "service.impl";
    /** 实体路径 */
    private String pojoPackage = "pojo";
    /** xml文件路径 */
    private String xmlPackage = "xml";

    /** 实体名称 */
    private String pojoName;
    /** service 层名称 */
    private String serviceName;
    /** service 实现类名称 */
    private String serviceImplName;
    /** dao 层名称 */
    private String daoName;
    /** xml名称 */
    private String xmlName;

    /** dao层包全路径 */
    private String daoFullPackage;
    /** service 接口层包全路径 */
    private String serviceFullPackage;
    /** service 实现类包全路径 */
    private String serviceImplFullPackage;
    /** 实体包全路径 */
    private String pojoFullPackage;
    /** xml包全路径*/
    private String xmlFullPackage;

    private FormatService formatService = new FormatService() {
        @Override
        public String formatTableName(String tableName) {
            return tableName.toLowerCase();
        }
    };

    public StratificationInfo() {

    }

    public StratificationInfo(String basePackage) {
        initEachFormat();

        initEachPackage();

        if(GeneratorStringUtils.isNotBlank(basePackage)) {
            this.basePackage = basePackage;
            basePackage = basePackage + point;
        } else {
            basePackage = "";
        }
        /** dao层包全路径 */
        this.daoFullPackage = basePackage + this.daoPackage;
        /** service 接口层包全路径 */
        this.serviceFullPackage = basePackage + this.servicePackage;
        /** service 实现类包全路径 */
        this.serviceImplFullPackage = basePackage + this.serviceImplPackage;
        /** 实体包全路径 */
        this.pojoFullPackage = basePackage + this.pojoPackage;
        /** xml包全路径*/
        this.xmlFullPackage = basePackage + this.xmlPackage;

    }

    private void initEachPackage() {
        String pojoPackage = PropertiesUtils.getProperties(ConfigConstants.POJO_PACKAGE);
        String daoPackage = PropertiesUtils.getProperties(ConfigConstants.DAO_PACKAGE);
        String xmlPackage = PropertiesUtils.getProperties(ConfigConstants.XML_PACKAGE);
        if(GeneratorStringUtils.isNotBlank(pojoPackage)){
            this.pojoPackage = pojoPackage;
        }
        if(GeneratorStringUtils.isNotBlank(daoPackage)){
            this.daoPackage = daoPackage;
        }
        if(GeneratorStringUtils.isNotBlank(xmlPackage)){
            this.xmlPackage = xmlPackage;
        }
    }

    private void initEachFormat() {
        String daoNameFormat = PropertiesUtils.getProperties(ConfigConstants.DAO_NAME_FORMAT);
        String serviceImplNameFormat = PropertiesUtils.getProperties(ConfigConstants.SERVICE_IMPL_NAME_FORMAT);
        String serviceNameFormat = PropertiesUtils.getProperties(ConfigConstants.SERVICE_NAME_FORMAT);
        String pojoNameFormat = PropertiesUtils.getProperties(ConfigConstants.POJO_NAME_FORMAT);
        if(GeneratorStringUtils.isNotBlank(daoNameFormat)){
            this.DAO_NAME_FORMAT = daoNameFormat;
        }
        if(GeneratorStringUtils.isNotBlank(serviceImplNameFormat)){
            this.SERVICE_IMPL_NAME_FORMAT = serviceImplNameFormat;
        }
        if(GeneratorStringUtils.isNotBlank(serviceNameFormat)){
            this.SERVICE_NAME_FORMAT = serviceNameFormat;
        }
        if(GeneratorStringUtils.isNotBlank(pojoNameFormat)){
            this.POJO_NAME_FORMAT = pojoNameFormat;
        }
    }

    public static String getPoint() {
        return point;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getPojoPackage() {
        return pojoPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }

    public String getXmlPackage() {
        return xmlPackage;
    }

    public void setXmlPackage(String xmlPackage) {
        this.xmlPackage = xmlPackage;
    }

    public String getPojoName() {
        return pojoName;
    }

    public void setPojoName(String pojoName) {
        this.pojoName = formatName(POJO_NAME_FORMAT,pojoName);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = formatName(SERVICE_NAME_FORMAT,serviceName);
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = formatName(SERVICE_IMPL_NAME_FORMAT,serviceImplName);
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = formatName(DAO_NAME_FORMAT,daoName);
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getDaoFullPackage() {
        return daoFullPackage;
    }

    public void setDaoFullPackage(String daoFullPackage) {
        this.daoFullPackage = daoFullPackage;
    }

    public String getServiceFullPackage() {
        return serviceFullPackage;
    }

    public void setServiceFullPackage(String serviceFullPackage) {
        this.serviceFullPackage = serviceFullPackage;
    }

    public String getServiceImplFullPackage() {
        return serviceImplFullPackage;
    }

    public void setServiceImplFullPackage(String serviceImplFullPackage) {
        this.serviceImplFullPackage = serviceImplFullPackage;
    }

    public String getPojoFullPackage() {
        return pojoFullPackage;
    }

    public void setPojoFullPackage(String pojoFullPackage) {
        this.pojoFullPackage = pojoFullPackage;
    }

    public String getXmlFullPackage() {
        return xmlFullPackage;
    }

    public void setXmlFullPackage(String xmlFullPackage) {
        this.xmlFullPackage = xmlFullPackage;
    }

    public String formatName(String daoNameFormat, String daoName) {
        return MessageFormat.format(daoNameFormat, daoName);
    }

    public void initFilesName(TableInfoFtl tableInfoFtl) {
        String javaTableName = tableInfoFtl.getJavaTableName();
        setPojoName(javaTableName);
        setDaoName(javaTableName);
        setServiceName(javaTableName);
        setServiceImplName(javaTableName);
        String xmlNameFormat = PropertiesUtils.getProperties(ConfigConstants.XML_NAME_FORMAT);
        if ("camel".equalsIgnoreCase(xmlNameFormat)){
            setXmlName(tableInfoFtl.getJavaTableName());
        } else {
            setXmlName(formatService.formatTableName(tableInfoFtl.getTableName()));
        }
    }

}