package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;

/**
 * class: TemplateGenerateInfo <br>
 * description: TODO <br>
 * time: 2019/5/23
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class TemplateGenerateInfo {

    private ModuleTypeEnums moduleTypeEnums;
    private String fileOutputPath;
    private GenerateInfo generateInfo;

    public TemplateGenerateInfo(ModuleTypeEnums moduleTypeEnums, String fileOutputPath, GenerateInfo generateInfo) {
        this.moduleTypeEnums = moduleTypeEnums;
        this.fileOutputPath = fileOutputPath;
        this.generateInfo = generateInfo;
    }

    public ModuleTypeEnums getModuleTypeEnums() {
        return moduleTypeEnums;
    }

    public void setModuleTypeEnums(ModuleTypeEnums moduleTypeEnums) {
        this.moduleTypeEnums = moduleTypeEnums;
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
