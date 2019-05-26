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
    MAPPER("MAPPER",FileTypeEnums.JAVA,"{0}Mapper","generate.java.mapper.name-format","generate.java.mapper.package","数据库接口"),
    POJO("POJO",FileTypeEnums.JAVA,"{0}","generate.java.pojo.name-format","generate.java.pojo.package","数据对象"),

    XML("XML",FileTypeEnums.XML,"{0}","generate.resources.xml.name-format","generate.resources.xml.out-put-path","mybatis xml 文件"),
    ;
    private String moduleType;
    private FileTypeEnums typeEnums;
    /** 默认格式化方式 */
    private String fileNameFormat;
    private String fileNameFormatKey;
    private String filePackageKey;
    private String moduleDescription;

    ModuleTypeEnums(String moduleType, FileTypeEnums typeEnums, String fileNameFormat, String fileNameFormatKey, String filePackageKey, String moduleDescription) {
        this.moduleType = moduleType;
        this.typeEnums = typeEnums;
        this.fileNameFormat = fileNameFormat;
        this.fileNameFormatKey = fileNameFormatKey;
        this.filePackageKey = filePackageKey;
        this.moduleDescription = moduleDescription;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public FileTypeEnums getTypeEnums() {
        return typeEnums;
    }

    public void setTypeEnums(FileTypeEnums typeEnums) {
        this.typeEnums = typeEnums;
    }

    public String getFileNameFormat() {
        return fileNameFormat;
    }

    public void setFileNameFormat(String fileNameFormat) {
        this.fileNameFormat = fileNameFormat;
    }

    public String getFileNameFormatKey() {
        return fileNameFormatKey;
    }

    public void setFileNameFormatKey(String fileNameFormatKey) {
        this.fileNameFormatKey = fileNameFormatKey;
    }

    public String getFilePackageKey() {
        return filePackageKey;
    }

    public void setFilePackageKey(String filePackageKey) {
        this.filePackageKey = filePackageKey;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }
}
