package com.github.zhuyizhuo.generator.mybatis.constants;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:15
 */
public class FtlPathInfo {

    private String mysqlXmlFtlPath = "/freemarker/template/common/mybatis_mapper_template_v1.ftl";
    private String pojoFtlPath = "/freemarker/template/common/javabean.ftl";
    private String daoFtlPath = "/freemarker/template/common/dao.ftl";
    private String bootStrapFtlPath = "/freemarker/template/common/bootStrap.ftl";

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

    @Override
    public String toString() {
        return "模板路径:\n{" +
                "mysqlXmlFtlPath='" + mysqlXmlFtlPath + '\'' +
                ",\n pojoFtlPath='" + pojoFtlPath + '\'' +
                ",\n daoFtlPath='" + daoFtlPath + '\'' +
                ",\n bootStrapFtlPath='" + bootStrapFtlPath + '\'' +
                '}';
    }
}
