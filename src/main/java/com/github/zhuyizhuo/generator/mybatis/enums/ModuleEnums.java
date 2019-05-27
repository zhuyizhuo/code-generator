package com.github.zhuyizhuo.generator.mybatis.enums;

/**
 * class: ModuleEnums <br>
 * description: 模块枚举 <br>
 * time: 2019/5/22
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public enum ModuleEnums {

    MAPPER(FileTypeEnums.JAVA,"generate.java.module.mapper.name-format","generate.java.module.mapper.package","数据库接口"),

    POJO(FileTypeEnums.JAVA,"generate.java.module.pojo.name-format","generate.java.module.pojo.package","数据对象"),

    XML(FileTypeEnums.XML,"generate.resources.xml.name-format","generate.resources.xml.out-put-path","mybatis xml 文件"),
    ;
    /** 文件类型 */
    private FileTypeEnums typeEnums;
    /** 生成文件的文件名格式化配置 */
    private String fileNameFormatKey;
    /** 生成文件的包路径配置 */
    private String filePackageKey;
    /** 模块描述 */
    private String moduleDescription;

    ModuleEnums(FileTypeEnums typeEnums, String fileNameFormatKey, String filePackageKey, String moduleDescription) {
        this.typeEnums = typeEnums;
        this.fileNameFormatKey = fileNameFormatKey;
        this.filePackageKey = filePackageKey;
        this.moduleDescription = moduleDescription;
    }

    public FileTypeEnums getTypeEnums() {
        return typeEnums;
    }

    public void setTypeEnums(FileTypeEnums typeEnums) {
        this.typeEnums = typeEnums;
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
