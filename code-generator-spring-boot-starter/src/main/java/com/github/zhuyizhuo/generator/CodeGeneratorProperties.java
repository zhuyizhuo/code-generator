package com.github.zhuyizhuo.generator;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhuo
 */
@ConfigurationProperties(prefix = "code-generator")
public class CodeGeneratorProperties {

    /** 数据库配置 */
    private Datasource datasource;
    /** 注释信息 */
    private Comment comment;

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}