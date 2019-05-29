package com.github.zhuyizhuo.generator.mybatis.vo;

/**
 * class: TemplateGenerateInfo <br>
 * description: 模板生成信息 <br>
 * time: 2019/5/23
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
public class TemplateGenerateInfo {
    /** 模块类型 */
    private String moduleType;
    /** 生成文件输出路径 */
    private String fileOutputPath;
    /** 生成文件所需信息 */
    private GenerateInfo generateInfo;

    public TemplateGenerateInfo(String moduleType, String fileOutputPath) {
        this.moduleType = moduleType;
        this.fileOutputPath = fileOutputPath;
    }

    public TemplateGenerateInfo(String moduleType, String fileOutputPath, GenerateInfo generateInfo) {
        this.moduleType = moduleType;
        this.fileOutputPath = fileOutputPath;
        this.generateInfo = generateInfo;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
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
