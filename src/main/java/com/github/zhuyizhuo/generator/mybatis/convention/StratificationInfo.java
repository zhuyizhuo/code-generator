package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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
    /** dao 层名称 */
    private String DAO_NAME_FORMAT = "{0}Mapper";

    /** dao包路径 */
    private String daoPackage = "mapper";
    /** 实体路径 */
    private String pojoPackage = "pojo";
    /** xml文件路径 */
    private String xmlPackage = "xml";

    /** 实体名称 */
    private String pojoName;
    /** dao 层名称 */
    private String daoName;
    /** xml名称 */
    private String xmlName;

    /** dao层包全路径 */
    private String daoFullPackage;
    /** 实体包全路径 */
    private String pojoFullPackage;
    /** xml包全路径*/
    private String xmlFullPackage;

    private Map<String,FormatService> nameFormatMap = new HashMap<>();

    private FormatService formatService = (tableName) -> tableName.toLowerCase();

    public StratificationInfo() {

    }

    public StratificationInfo(String basePackage) {
        init(basePackage);
    }

    public void init() {
        this.init(PropertiesUtils.getProperties(ConfigConstants.BASE_PACKAGE));
    }

    /**
     *  初始化分层信息
     * @since 1.3.0
     * @param basePackage 基础路径
     */
    public void init(String basePackage) {
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
        /** 实体包全路径 */
        this.pojoFullPackage = basePackage + this.pojoPackage;
        /** xml包全路径*/
        this.xmlFullPackage = basePackage + this.xmlPackage;
    }

    private void initEachPackage() {
        String pojoPackage = PropertiesUtils.getProperties(ConfigConstants.POJO_PACKAGE);
        String daoPackage = PropertiesUtils.getProperties(ConfigConstants.DAO_PACKAGE);
        String xmlPackage = PropertiesUtils.getProperties(ConfigConstants.XML_OUT_PUT_PATH);
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
        String pojoNameFormat = PropertiesUtils.getProperties(ConfigConstants.POJO_NAME_FORMAT);
        if(GeneratorStringUtils.isNotBlank(daoNameFormat)){
            this.DAO_NAME_FORMAT = daoNameFormat;
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

    public void setPojoName(String tableName) {
        if (nameFormatMap.get(ConfigConstants.POJO_NAME_FORMAT) != null){
            this.pojoName = nameFormatMap.get(ConfigConstants.POJO_NAME_FORMAT).formatTableName(tableName);
        } else {
            this.pojoName = formatName(POJO_NAME_FORMAT,GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName,ConfigConstants.tableRegex));
        }
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String tableName) {
        if (nameFormatMap.get(ConfigConstants.DAO_NAME_FORMAT) != null){
            this.daoName = nameFormatMap.get(ConfigConstants.DAO_NAME_FORMAT).formatTableName(tableName);
        } else {
            this.daoName = formatName(DAO_NAME_FORMAT,GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName,ConfigConstants.tableRegex));
        }
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

    private void initXmlName(String tableName) {
        String xmlNameFormat = PropertiesUtils.getProperties(ConfigConstants.XML_NAME_FORMAT);
        if ("camel".equalsIgnoreCase(xmlNameFormat)){
            setXmlName(GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName,ConfigConstants.tableRegex));
        } else {
            setXmlName(formatService.formatTableName(tableName));
        }
    }

    /**
     *  格式化生成
     */
    public String formatName(String format, String javaTableName) {
        return MessageFormat.format(format, javaTableName);
    }

    public void addXmlNameFormat(FormatService formatService) {
        this.formatService = formatService;
    }

    public void initFilesName(String tableName) {
        setPojoName(tableName);
        setDaoName(tableName);
        initXmlName(tableName);
    }

    private void addFormatService(FormatService formatService, String pojoNameFormat) {
        this.nameFormatMap.put(pojoNameFormat, formatService);
    }

    public void addBeanNameFormat(FormatService formatService) {
        addFormatService(formatService, ConfigConstants.POJO_NAME_FORMAT);
    }

    public void addDaoNameFormat(FormatService formatService) {
        addFormatService(formatService, ConfigConstants.DAO_NAME_FORMAT);
    }
}