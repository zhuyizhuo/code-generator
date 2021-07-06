package com.github.zhuyizhuo.generator;

/**
 * 注释信息
 * @author zhuo
 */
public class Comment {
    /** 起始版本号 */
    private String sinceVersion;
    /** 当前版本号 */
    private String version;
    /** 作者 */
    private String author;

    public String getSinceVersion() {
        return sinceVersion;
    }

    public void setSinceVersion(String sinceVersion) {
        this.sinceVersion = sinceVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
