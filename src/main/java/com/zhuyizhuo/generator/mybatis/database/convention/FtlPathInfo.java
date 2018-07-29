package com.zhuyizhuo.generator.mybatis.database.convention;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:15
 */
public class FtlPathInfo {

    private String resourcesPath = getResourcesPath();
    private String mysqlXmlFtlPath = resourcesPath + "freemarker/template/mysql/Test.ftl";

    private static String getResourcesPath() {
        return FtlPathInfo.class.getResource("/").getPath();
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    public String getMysqlXmlFtlPath() {
        return mysqlXmlFtlPath;
    }

    public void setMysqlXmlFtlPath(String mysqlXmlFtlPath) {
        this.mysqlXmlFtlPath = mysqlXmlFtlPath;
    }

}
