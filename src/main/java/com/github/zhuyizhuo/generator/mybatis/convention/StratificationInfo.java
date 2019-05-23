package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
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
@CoventionClass
public class StratificationInfo {
    private static final String point = ".";
    @Value("#{generate.java.base-package}")
    private String basePackage;

    @Value("#{generate.java.mapper.package}")
    private String mapperPackage;
    @Value("#{generate.java.pojo.package}")
    private String pojoPackage;

    /** 表名 */
    private String tableName;
    /** java 类名 */
    private String javaClassName;

    /** 实体名称 */
    private String pojoName;
    /** dao 层名称 */
    private String daoName;

    /** dao层包全路径 */
    private String daoFullPackage;
    /** 实体包全路径 */
    private String pojoFullPackage;

    private Map<ModuleTypeEnums, FormatService> nameFormatMap = new HashMap<>();

    public StratificationInfo() {

    }

    /**
     *  初始化分层信息
     * @since 1.3.0
     * @param nameFormatMap
     */
    public void init(Map<ModuleTypeEnums, FormatService> nameFormatMap) {
        if (GeneratorStringUtils.isNotBlank(basePackage)) {
            /** dao层包全路径 */
            this.daoFullPackage = basePackage + point + mapperPackage;
            /** 实体包全路径 */
            this.pojoFullPackage = basePackage + point + pojoPackage;
        } else {
            /** dao层包全路径 */
            this.daoFullPackage = mapperPackage;
            /** 实体包全路径 */
            this.pojoFullPackage = pojoPackage;
        }
        if (nameFormatMap != null) {
            this.nameFormatMap = nameFormatMap;
        }
    }

    /**
     *  格式化类名
     */
    public String classNameFormat(ModuleTypeEnums moduleType, String javaTableName) {
        String properties = PropertiesUtils.getProperties(moduleType.getFileNameFormatKey());
        return MessageFormat.format(GeneratorStringUtils.isNotBlank(properties)
                                        ? properties
                                        : moduleType.getFileNameFormat()
                                    , javaTableName);
    }

    public void initFilesName(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.javaClassName = tableNameCamelCase;

        if (getFormatService(ModuleTypeEnums.POJO) != null){
            this.pojoName = getFormatService(ModuleTypeEnums.POJO).format(tableName);
        } else {
            this.pojoName = classNameFormat(ModuleTypeEnums.POJO, javaClassName);
        }
        if (getFormatService(ModuleTypeEnums.MAPPER) != null){
            this.daoName = getFormatService(ModuleTypeEnums.MAPPER).format(tableName);
        } else {
            this.daoName = classNameFormat(ModuleTypeEnums.MAPPER, javaClassName);
        }
    }

    private FormatService getFormatService(ModuleTypeEnums moduleType) {
        return nameFormatMap.get(moduleType);
    }

    public void addFormatService(ModuleTypeEnums moduleType, FormatService formatService) {
        this.nameFormatMap.put(moduleType, formatService);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getPojoPackage() {
        return pojoPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }

    public String getPojoName() {
        return pojoName;
    }

    public String getDaoName() {
        return daoName;
    }

    public String getDaoFullPackage() {
        return daoFullPackage;
    }

    public String getPojoFullPackage() {
        return pojoFullPackage;
    }
}