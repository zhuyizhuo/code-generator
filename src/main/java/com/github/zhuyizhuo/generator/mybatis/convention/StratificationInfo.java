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
    /** 表名 */
    private String tableName;
    /** java 类名 */
    private String javaClassName;
    /** 实体名称 */
    private String POJO_NAME_FORMAT = "{0}";
    /** dao 层名称 */
    private String DAO_NAME_FORMAT = "{0}Mapper";

    /** dao包路径 */
    private String daoPackage = "mapper";
    /** 实体路径 */
    private String pojoPackage = "pojo";

    /** 实体名称 */
    private String pojoName;
    /** dao 层名称 */
    private String daoName;

    /** dao层包全路径 */
    private String daoFullPackage;
    /** 实体包全路径 */
    private String pojoFullPackage;

    private Map<String,FormatService> nameFormatMap = new HashMap<>();

    public StratificationInfo() {

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
        String daoNameFormat = PropertiesUtils.getProperties(ConfigConstants.DAO_NAME_FORMAT);
        String pojoNameFormat = PropertiesUtils.getProperties(ConfigConstants.POJO_NAME_FORMAT);
        if(GeneratorStringUtils.isNotBlank(daoNameFormat)){
            this.DAO_NAME_FORMAT = daoNameFormat;
        }
        if(GeneratorStringUtils.isNotBlank(pojoNameFormat)){
            this.POJO_NAME_FORMAT = pojoNameFormat;
        }

        String pojoPackage1 = PropertiesUtils.getProperties(ConfigConstants.POJO_PACKAGE);
        String daoPackage1 = PropertiesUtils.getProperties(ConfigConstants.DAO_PACKAGE);
        if(GeneratorStringUtils.isNotBlank(pojoPackage1)){
            this.pojoPackage = pojoPackage1;
        }
        if(GeneratorStringUtils.isNotBlank(daoPackage1)){
            this.daoPackage = daoPackage1;
        }

        if(GeneratorStringUtils.isNotBlank(basePackage)) {
            /** dao层包全路径 */
            this.daoFullPackage = basePackage + point + this.daoPackage;
            /** 实体包全路径 */
            this.pojoFullPackage = basePackage + point + this.pojoPackage;
        } else {
            /** dao层包全路径 */
            this.daoFullPackage = this.daoPackage;
            /** 实体包全路径 */
            this.pojoFullPackage = this.pojoPackage;
        }

    }

    public String getPojoName() {
        return pojoName;
    }

    public void setPojoName(String tableName) {
        if (nameFormatMap.get(ConfigConstants.POJO_NAME_FORMAT) != null){
            this.pojoName = nameFormatMap.get(ConfigConstants.POJO_NAME_FORMAT).format(tableName);
        } else {
            this.pojoName = formatName(POJO_NAME_FORMAT,javaClassName);
        }
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String tableName) {
        if (nameFormatMap.get(ConfigConstants.DAO_NAME_FORMAT) != null){
            this.daoName = nameFormatMap.get(ConfigConstants.DAO_NAME_FORMAT).format(tableName);
        } else {
            this.daoName = formatName(DAO_NAME_FORMAT,javaClassName);
        }
    }

    public String getDaoFullPackage() {
        return daoFullPackage;
    }

    public String getPojoFullPackage() {
        return pojoFullPackage;
    }

    /**
     *  格式化生成
     */
    public String formatName(String format, String javaTableName) {
        return MessageFormat.format(format, javaTableName);
    }

    public void initFilesName(String tableName) {
        this.tableName = tableName;
        this.javaClassName = GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName,ConfigConstants.tableRegex);
        setPojoName(tableName);
        setDaoName(tableName);
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}