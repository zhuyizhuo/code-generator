package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private Map<ModuleTypeEnums, FormatService> nameFormatMap = new HashMap<>();
    /***
     *  moduleTpye ->  JavaClassDefinition
     */
    private Map<String,JavaClassDefinition> javaClassDefinition = new ConcurrentHashMap<>();

    public StratificationInfo() {

    }

    /**
     *  初始化分层信息
     * @since 1.3.0
     * @param nameFormatMap
     */
    public void init(Map<ModuleTypeEnums, FormatService> nameFormatMap) {
        String daoFullPackage = mapperPackage;
        String pojoFullPackage = pojoPackage;
        if (GeneratorStringUtils.isNotBlank(basePackage)) {
            /** dao层包全路径 */
            daoFullPackage = basePackage + point + mapperPackage;
            /** 实体包全路径 */
            pojoFullPackage = basePackage + point + pojoPackage;
        }
        javaClassDefinition.put(ModuleTypeEnums.MAPPER.getModuleType(), new JavaClassDefinition(daoFullPackage));
        javaClassDefinition.put(ModuleTypeEnums.POJO.getModuleType(), new JavaClassDefinition(pojoFullPackage));
        if (nameFormatMap != null) {
            this.nameFormatMap = nameFormatMap;
        }
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

    public Map<String,JavaClassDefinition> initFilesName(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.javaClassName = tableNameCamelCase;

        String pojoName = classNameFormat(ModuleTypeEnums.POJO, javaClassName);
        String daoName = classNameFormat(ModuleTypeEnums.MAPPER, javaClassName);
        if (getFormatService(ModuleTypeEnums.POJO) != null){
            pojoName = getFormatService(ModuleTypeEnums.POJO).format(tableName);
        }
        if (getFormatService(ModuleTypeEnums.MAPPER) != null){
            daoName = getFormatService(ModuleTypeEnums.MAPPER).format(tableName);
        }
        javaClassDefinition.get(ModuleTypeEnums.MAPPER.getModuleType()).setClassName(daoName);
        javaClassDefinition.get(ModuleTypeEnums.POJO.getModuleType()).setClassName(pojoName);
        return javaClassDefinition;
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

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }

    public Map<String, JavaClassDefinition> getJavaClassDefinition() {
        return javaClassDefinition;
    }

}