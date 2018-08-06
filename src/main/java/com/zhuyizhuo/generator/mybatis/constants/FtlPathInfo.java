package com.zhuyizhuo.generator.mybatis.constants;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:15
 */
public class FtlPathInfo {

    private String resourcesPath = getResourcesPath();
    private String mysqlXmlFtlPath = resourcesPath + "freemarker/template/common/mysql_mapper_template_v1.ftl";
    private String pojoFtlPath = resourcesPath + "freemarker/template/common/javabean.ftl";
    private String daoFtlPath = resourcesPath + "freemarker/template/common/dao.ftl";
    private String bootStrapFtlPath = resourcesPath + "freemarker/template/common/bootStrap.ftl";

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

    public String getPojoFtlPath() {
        return pojoFtlPath;
    }

    public void setPojoFtlPath(String pojoFtlPath) {
        this.pojoFtlPath = pojoFtlPath;
    }

    public String getDaoFtlPath() {
        return daoFtlPath;
    }

    public void setDaoFtlPath(String daoFtlPath) {
        this.daoFtlPath = daoFtlPath;
    }

    public String getBootStrapFtlPath() {
        return bootStrapFtlPath;
    }

    public void setBootStrapFtlPath(String bootStrapFtlPath) {
        this.bootStrapFtlPath = bootStrapFtlPath;
    }
}
