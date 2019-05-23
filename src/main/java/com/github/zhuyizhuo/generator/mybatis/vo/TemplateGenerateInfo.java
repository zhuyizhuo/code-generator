package com.github.zhuyizhuo.generator.mybatis.vo;

/**
 * class: TemplateGenerateInfo <br>
 * description: TODO <br>
 * time: 2019/5/23
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class TemplateGenerateInfo {
    private String fileInputPath;
    private String fileOutputPath;
    private GenerateInfo generateInfo;

    public TemplateGenerateInfo(String fileInputPath, String fileOutputPath, GenerateInfo generateInfo) {
        this.fileInputPath = fileInputPath;
        this.fileOutputPath = fileOutputPath;
        this.generateInfo = generateInfo;
    }

    public String getFileInputPath() {
        return fileInputPath;
    }

    public void setFileInputPath(String fileInputPath) {
        this.fileInputPath = fileInputPath;
    }

    public String getFileOutputPath() {
        return fileOutputPath;
    }

    public void setFileOutputPath(String fileOutputPath) {
        this.fileOutputPath = fileOutputPath;
    }

    public GenerateInfo getGenerateInfo() {
        return generateInfo;
    }

    public void setGenerateInfo(GenerateInfo generateInfo) {
        this.generateInfo = generateInfo;
    }
}
