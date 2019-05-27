package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;

/**
 * class: ModuleInfo <br>
 * description: TODO <br>
 * time: 2019/5/27
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class ModuleInfo {
    /** 模块名 */
    private String moduleName;
    /** 文件包路径 */
    private String fileFullPackage;
    /** 文件输出路径模板 */
    private String outPutFullPathFormat;
    /** 文件名格式化 service */
    private FormatService fileNameFormatServie;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFileFullPackage() {
        return fileFullPackage;
    }

    public void setFileFullPackage(String fileFullPackage) {
        this.fileFullPackage = fileFullPackage;
    }

    public String getOutPutFullPathFormat() {
        return outPutFullPathFormat;
    }

    public void setOutPutFullPathFormat(String outPutFullPathFormat) {
        this.outPutFullPathFormat = outPutFullPathFormat;
    }

    public FormatService getFileNameFormatServie() {
        return fileNameFormatServie;
    }

    public void setFileNameFormatServie(FormatService fileNameFormatServie) {
        this.fileNameFormatServie = fileNameFormatServie;
    }
}
