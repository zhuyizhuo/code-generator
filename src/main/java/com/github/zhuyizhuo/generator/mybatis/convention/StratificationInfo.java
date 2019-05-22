package com.github.zhuyizhuo.generator.mybatis.convention;

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
public class StratificationInfo {
    private static final String point = ".";
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

    public void init() {
        this.init(PropertiesUtils.getProperties(ConfigConstants.BASE_PACKAGE));
    }

    /**
     *  初始化分层信息
     * @since 1.3.0
     * @param basePackage 基础路径
     */
    public void init(String basePackage) {
        /** dao包路径 */
        String mapperPackage = ModuleTypeEnums.MAPPER.getModulePackage();
        /** 实体路径 */
        String pojoPackage = ModuleTypeEnums.POJO.getModulePackage();

        String configPOJO = PropertiesUtils.getProperties(ConfigConstants.POJO_PACKAGE);
        String configMapper = PropertiesUtils.getProperties(ConfigConstants.DAO_PACKAGE);
        if(GeneratorStringUtils.isNotBlank(configPOJO)){
            pojoPackage = configPOJO;
        }
        if(GeneratorStringUtils.isNotBlank(configMapper)){
            mapperPackage = configMapper;
        }

        if(GeneratorStringUtils.isNotBlank(basePackage)) {
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
}