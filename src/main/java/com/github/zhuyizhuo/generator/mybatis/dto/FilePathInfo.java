package com.github.zhuyizhuo.generator.mybatis.dto;

import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.RealGenerateInfo;

/**
 * class: FilePathInfo <br>
 * description: TODO <br>
 * time: 2019/5/23
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class FilePathInfo {
    /** 模块类型 */
    private String moduleType;
    private String templatePath;
    private String fileOutPutPath;
    private RealGenerateInfo generateInfo;

    public FilePathInfo() {
    }

    public FilePathInfo(String fileOutPutPath) {
        this.fileOutPutPath = fileOutPutPath;
    }

    public FilePathInfo(String templatePath, String fileOutPutPath) {
        this.templatePath = templatePath;
        this.fileOutPutPath = fileOutPutPath;
    }

    public FilePathInfo(String moduleType, String templatePath, String fileOutPutPath) {
        this.moduleType = moduleType;
        this.templatePath = templatePath;
        this.fileOutPutPath = fileOutPutPath;
    }

    public RealGenerateInfo getGenerateInfo() {
        return generateInfo;
    }

    public void setGenerateInfo(RealGenerateInfo generateInfo) {
        this.generateInfo = generateInfo;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getFileOutPutPath() {
        return fileOutPutPath;
    }

    public void setFileOutPutPath(String fileOutPutPath) {
        this.fileOutPutPath = fileOutPutPath;
    }
}
