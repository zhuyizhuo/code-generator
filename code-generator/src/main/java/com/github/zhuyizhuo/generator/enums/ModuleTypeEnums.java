package com.github.zhuyizhuo.generator.enums;

/**
 * 模块类型枚举 <br>
 * create time: 2020-10-29 08:25:15 <br>
 *
 * @author zhuo <br>
 * @since 1.5.0
 */
public enum ModuleTypeEnums {

    /** 控制层 */
    CONTROLLER(FileTypeEnums.JAVA,"generate.java.module.controller","控制层"),
    /** 视图对象 */
    VO(FileTypeEnums.JAVA,"generate.java.module.vo","视图对象"),
    /** 服务接口 */
    SERVICE(FileTypeEnums.JAVA,"generate.java.module.service","服务接口"),
    /** 服务接口实现 */
    SERVICE_IMPL(FileTypeEnums.JAVA,"generate.java.module.service.impl","服务接口实现"),
    /** 数据库接口 */
    MAPPER(FileTypeEnums.JAVA,"generate.java.module.mapper","数据库接口"),
    /** 数据对象 */
    MODEL(FileTypeEnums.JAVA,"generate.java.module.model","数据对象"),
    /** mybatis xml 文件 */
    XML(FileTypeEnums.XML,"generate.resources.xml","mybatis xml 文件"),
    ;

    /** 文件类型 */
    private FileTypeEnums fileType;
    /** 配置前缀 */
    private String prefix;
    /** 模块描述 */
    private String description;

    ModuleTypeEnums(FileTypeEnums typeEnums, String prefix, String description) {
        this.fileType = typeEnums;
        this.prefix = prefix;
        this.description = description;
    }

    public FileTypeEnums getFileType() {
        return fileType;
    }

    public String getPrefix() {
        return prefix;
    }
    public String getDescription() {
        return description;
    }

    /** 生成文件的文件名格式化配置 */
    private String fileNameFormat = ".name-format";
    /** 生成文件的包路径配置 */
    private String filePackage = ".package";
    /** 生成文件的输出路径 */
    private String outputPath = ".out-put-path";

    public String getFileNameFormatKey() {
        return prefix + fileNameFormat;
    }

    public String getFilePackageKey() {
        return prefix + filePackage;
    }

    public String getOutputPathKey() {
        return prefix + outputPath;
    }

}
