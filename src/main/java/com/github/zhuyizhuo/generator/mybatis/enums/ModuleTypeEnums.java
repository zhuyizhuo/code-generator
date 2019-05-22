package com.github.zhuyizhuo.generator.mybatis.enums;

/**
 * class: ModuleTypeEnums <br>
 * description: TODO <br>
 * time: 2019/5/22
 *
 * @author yizhuo <br>
 * @since #since#
 */
public enum ModuleTypeEnums {
    MAPPER("MAPPER","数据库接口"),
    POJO("POJO","数据对象"),
    XML("XML","mybatis xml 文件"),
    ;
    private String moduleType;
    private String fileNameFormat;
    private String moduleDescription;

    ModuleTypeEnums(String moduleType, String moduleDescription) {
        this.moduleType = moduleType;
        this.moduleDescription = moduleDescription;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }
}
