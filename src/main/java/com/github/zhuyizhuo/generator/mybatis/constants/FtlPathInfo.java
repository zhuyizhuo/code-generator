package com.github.zhuyizhuo.generator.mybatis.constants;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:15
 */
public class FtlPathInfo {

    private String mybatisXmlFtlPath = "/freemarker/template/common/mybatis_mapper_template_v1.ftl";
    private String pojoFtlPath = "/freemarker/template/common/javabean.ftl";
    private String daoFtlPath = "/freemarker/template/common/dao.ftl";

    public String getMybatisXmlFtlPath() {
        return mybatisXmlFtlPath;
    }

    public void setMybatisXmlFtlPath(String mybatisXmlFtlPath) {
        this.mybatisXmlFtlPath = mybatisXmlFtlPath;
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

    @Override
    public String toString() {
        return "模板路径:{" +
                "mybatisXmlFtlPath='" + mybatisXmlFtlPath + '\'' +
                ", pojoFtlPath='" + pojoFtlPath + '\'' +
                ", daoFtlPath='" + daoFtlPath + '\'' +
                '}';
    }
}
