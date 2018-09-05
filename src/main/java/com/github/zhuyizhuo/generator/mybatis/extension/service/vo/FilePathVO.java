package com.github.zhuyizhuo.generator.mybatis.extension.service.vo;

/**
 * class: FilePathVO <br>
 * description: 文件模板及输出路径对象 <br>
 * time: 2018/9/5 18:54
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class FilePathVO {
    /** 模板路径 */
    private String templatePath;
    /** 文件输出路径 */
    private String fileOutPath;

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getFileOutPath() {
        return fileOutPath;
    }

    public void setFileOutPath(String fileOutPath) {
        this.fileOutPath = fileOutPath;
    }
}
