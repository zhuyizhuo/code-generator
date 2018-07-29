package com.zhuyizhuo.generator.mybatis.database.convention;

/**
 * 分层信息
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 16:19
 */
public class StratificationInfo {
    /** 基础路径 */
    private String basePackage = "cn.zhuyizhuo";
    /** dao 层包路径*/
    private String daoPackage = "dao";
    /** service 接口层包路径 */
    private String servicePackage = "service";
    /** service 实现类包路径 */
    private String serviceImplPackage = "service.impl";
    /** xml文件路径 */
    private String xmlPackage = "xml";
    /** 实体路径 */
    private String pojoPackage = "pojo";

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getXmlPackage() {
        return xmlPackage;
    }

    public void setXmlPackage(String xmlPackage) {
        this.xmlPackage = xmlPackage;
    }

    public String getPojoPackage() {
        return pojoPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }
}
