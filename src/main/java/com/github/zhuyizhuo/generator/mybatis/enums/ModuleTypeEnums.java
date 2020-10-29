package com.github.zhuyizhuo.generator.mybatis.enums;

/**
 * class: ModuleTypeEnums <br>
 * description: 模块枚举 <br>
 * time: 2019/5/22
 * modify time: 2020-10-29 08:25:15
 * 
 * @author zhuo <br>
 * @since 1.4.0
 * @version 1.4.3
 */
public enum ModuleTypeEnums {

    MAPPER(FileTypeEnums.JAVA,"generate.java.module.mapper.","name-format","package","out-put-path","数据库接口"),

    MODEL(FileTypeEnums.JAVA,"generate.java.module.model.","name-format","package","out-put-path","数据对象"),

    SERVICE(FileTypeEnums.JAVA,"generate.java.module.service.","name-format","package","out-put-path","服务接口"),

    SERVICE_IMPL(FileTypeEnums.JAVA,"generate.java.module.service.impl.","name-format","package","out-put-path","服务实现接口"),

    XML(FileTypeEnums.XML,"generate.resources.xml.","name-format","package","out-put-path","mybatis xml 文件"),
    ;
    /** 文件类型 */
    private FileTypeEnums typeEnums;
    /** 配置前缀 */
    private String keyPrefix;
    /** 生成文件的文件名格式化配置 */
    private String fileNameFormatKey;
    /** 生成文件的包路径配置 */
    private String filePackageKey;
    private String outputPathKey;
    /** 模块描述 */
    private String moduleDescription;

    ModuleTypeEnums(FileTypeEnums typeEnums, String keyPrefix, String fileNameFormatKey, String filePackageKey, String outputPathKey, String moduleDescription) {
        this.typeEnums = typeEnums;
        this.keyPrefix = keyPrefix;
        this.fileNameFormatKey = fileNameFormatKey;
        this.filePackageKey = filePackageKey;
        this.outputPathKey = outputPathKey;
        this.moduleDescription = moduleDescription;
    }

    public FileTypeEnums getTypeEnums() {
        return typeEnums;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public String getFileNameFormatKey() {
        return keyPrefix + fileNameFormatKey;
    }

    public String getFilePackageKey() {
        return keyPrefix + filePackageKey;
    }

    public String getOutputPathKey() {
        return keyPrefix + outputPathKey;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }
}
