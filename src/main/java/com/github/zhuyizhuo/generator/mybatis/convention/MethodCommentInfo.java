package com.github.zhuyizhuo.generator.mybatis.convention;

/**
 * class: MethodCommentInfo <br>
 * description: 方法描述 <br>
 * time: 2018/8/17 12:47
 *
 * @author yizhuo <br>
 * @version 1.3.0
 */
public class MethodCommentInfo {

    private String insertMethodDescription = "新增数据";
    private String deleteMethodDescription = "根据传入参数删除数据";
    private String deleteByPrimaryKeyMethodDescription = "根据主键删除数据";
    private String updateMethodDescription = "根据传入参数更新数据";
    private String updateByPrimaryKeyMethodDescription = "根据主键更新数据";
    private String queryMethodDescription = "根据传入参数查询数据列表";
    private String queryByPrimaryKeyDescription = "根据主键查询数据";
    private String countMethodDescription = "统计符合条件的数据数量";
    /** 参数对象描述 */
    private String paramsDescription = "传入参数";

    public String getInsertMethodDescription() {
        return insertMethodDescription;
    }

    public void setInsertMethodDescription(String insertMethodDescription) {
        this.insertMethodDescription = insertMethodDescription;
    }

    public String getUpdateMethodDescription() {
        return updateMethodDescription;
    }

    public void setUpdateMethodDescription(String updateMethodDescription) {
        this.updateMethodDescription = updateMethodDescription;
    }

    public String getUpdateByPrimaryKeyMethodDescription() {
        return updateByPrimaryKeyMethodDescription;
    }

    public void setUpdateByPrimaryKeyMethodDescription(String updateByPrimaryKeyMethodDescription) {
        this.updateByPrimaryKeyMethodDescription = updateByPrimaryKeyMethodDescription;
    }

    public String getDeleteByPrimaryKeyMethodDescription() {
        return deleteByPrimaryKeyMethodDescription;
    }

    public void setDeleteByPrimaryKeyMethodDescription(String deleteByPrimaryKeyMethodDescription) {
        this.deleteByPrimaryKeyMethodDescription = deleteByPrimaryKeyMethodDescription;
    }

    public String getDeleteMethodDescription() {
        return deleteMethodDescription;
    }

    public void setDeleteMethodDescription(String deleteMethodDescription) {
        this.deleteMethodDescription = deleteMethodDescription;
    }

    public String getQueryMethodDescription() {
        return queryMethodDescription;
    }

    public void setQueryMethodDescription(String queryMethodDescription) {
        this.queryMethodDescription = queryMethodDescription;
    }

    public String getCountMethodDescription() {
        return countMethodDescription;
    }

    public void setCountMethodDescription(String countMethodDescription) {
        this.countMethodDescription = countMethodDescription;
    }

    public String getQueryByPrimaryKeyDescription() {
        return queryByPrimaryKeyDescription;
    }

    public void setQueryByPrimaryKeyDescription(String queryByPrimaryKeyDescription) {
        this.queryByPrimaryKeyDescription = queryByPrimaryKeyDescription;
    }

    public String getParamsDescription() {
        return paramsDescription;
    }

    public void setParamsDescription(String paramsDescription) {
        this.paramsDescription = paramsDescription;
    }
}
