package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 方法注释
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 16:10
 */
public class CommentInfo {
    /** 文件创建时版本号 */
    private String sinceVersion = "";
    /** 当前版本号 */
    private String version = "1.0";
    /** 默认作者 */
    private String author = "TODO";
    /** 默认生成时间 */
    private String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public CommentInfo() {
        String author = PropertiesUtils.getProperties(ConfigConstants.AUTHOR);
        if (StringUtils.isNotBlank(author)){
            this.author = author;
        }
        String sinceVersion = PropertiesUtils.getProperties(ConfigConstants.SINCE_VERSION);
        if (StringUtils.isNotBlank(sinceVersion)){
            this.sinceVersion = sinceVersion;
        }
        String version = PropertiesUtils.getProperties(ConfigConstants.VERSION);
        if (StringUtils.isNotBlank(version)){
            this.version = version;
        }
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSinceVersion() {
        return sinceVersion;
    }

    public void setSinceVersion(String sinceVersion) {
        this.sinceVersion = sinceVersion;
    }
}
