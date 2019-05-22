package com.github.zhuyizhuo.generator.mybatis.enums;

/**
 * class: ModuleTypeEnums <br>
 * description: 模块类型枚举 <br>
 * time: 2019/5/22
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public enum ModuleTypeEnums {
    MAPPER("MAPPER","{0}Mapper","generate.java.mapper.name-format","mapper","数据库接口"),
    POJO("POJO","{0}","generate.java.pojo.name-format","pojo","数据对象"),

    XML("XML","mybatis xml 文件"),
    ;
    private String moduleType;
    /** 默认格式化方式 */
    private String fileNameFormat;
    private String fileNameFormatKey;
    /** 模块所在的包 */
    private String modulePackage;
    private String moduleDescription;

    ModuleTypeEnums(String moduleType, String moduleDescription) {
        this.moduleType = moduleType;
        this.moduleDescription = moduleDescription;
    }

    ModuleTypeEnums(String moduleType, String fileNameFormat, String fileNameFormatKey, String modulePackage, String moduleDescription) {
        this.moduleType = moduleType;
        this.fileNameFormat = fileNameFormat;
        this.fileNameFormatKey = fileNameFormatKey;
        this.modulePackage = modulePackage;
        this.moduleDescription = moduleDescription;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public String getFileNameFormat() {
        return fileNameFormat;
    }

    public void setFileNameFormat(String fileNameFormat) {
        this.fileNameFormat = fileNameFormat;
    }

    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

    public String getFileNameFormatKey() {
        return fileNameFormatKey;
    }

    public void setFileNameFormatKey(String fileNameFormatKey) {
        this.fileNameFormatKey = fileNameFormatKey;
    }
}
