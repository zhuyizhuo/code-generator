package com.github.zhuyizhuo.generator.mybatis.generator.extension;

import com.github.zhuyizhuo.generator.mybatis.enums.FileTypeEnums;

/**
 * class: FileInfo <br>
 * description: TODO <br>
 * time: 2019/5/27
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class FileInfo {
    /** 类型 */
    private FileTypeEnums fileType;
    /** 模块 */
    private String moduleType;
    /** 模板路径 */
    private String inputPath;
    /** 模块包 **/
    private String modulePackage;
    /** 文件名格式化 service 例如 {0}Mapper */
    private String fileNameFormat;
    /*** 后缀 */
    private String suffix;

    public FileInfo(FileTypeEnums fileType, String moduleType, String inputPath, String modulePackage, String fileNameFormat, String suffix) {
        this.fileType = fileType;
        this.moduleType = moduleType;
        this.inputPath = inputPath;
        this.modulePackage = modulePackage;
        this.fileNameFormat = fileNameFormat;
        this.suffix = suffix;
    }

    public FileTypeEnums getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeEnums fileType) {
        this.fileType = fileType;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

    public String getFileNameFormat() {
        return fileNameFormat;
    }

    public void setFileNameFormat(String fileNameFormat) {
        this.fileNameFormat = fileNameFormat;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
