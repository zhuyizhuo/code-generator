package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.constants.MethodEnableConstants;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;

/**
 * 方法名信息
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 15:40
 */
public class MethodInfo {

    /** 新增方法名 */
    private static final String INSERT_METHOD_FORMAT = "insert{0}";
    /** 删除方法名 */
    private static final String DELETE_METHOD_FORMAT = "delete{0}ByWhere";
    /** 根据主键删除方法名 */
    private static final String DELETE_BY_PRIMARY_KEY_METHOD_FORMAT = "delete{0}ByPrimaryKey";
    /** 更新方法名 */
    private static final String UPDATE_METHOD_FORMAT = "update{0}ByWhere";
    /** 根据主键更新方法名 */
    private static final String UPDATE_BY_PRIMARY_KEY_METHOD_FORMAT = "update{0}ByPrimaryKey";
    /** 查询方法名 */
    private static final String QUERY_METHOD_FORMAT = "query{0}ListByWhere";
    /** 根据主键查询 */
    private static final String QUERY_BY_PRIMARY_KEY_METHOD_FORMAT = "query{0}ByPrimaryKey";
    /** 查询总数方法名 */
    private static final String COUNT_METHOD_FORMAT = "count{0}ByWhere";
    /** 批量新增方法名*/
    private static final String BATCH_INSERT_METHOD_FORMAT = "batchInsert{0}";
    /** 分页查询方法名 */
    private static final String PAGING_QUERY_METHOD_FORMAT = "pagingQuery{0}";

    /** 方法名 */
    private String insertMethodName;
    private String updateMethodName;
    private String updateByPrimaryKeyMethodName;
    private String deleteByPrimaryKeyMethodName;
    private String deleteMethodName;
    private String queryMethodName;
    private String queryByPrimaryKeyMethodName;
    private String countMethodName;

    /** 是否生成指定方法 */
    private boolean insertMethodEnabled = true;
    private boolean deleteMethodEnabled = true;
    private boolean deleteByPrimaryKeyMethodEnabled = true;
    private boolean updateMethodEnabled = true;
    private boolean updateByPrimaryKeyMethodEnabled = true;
    private boolean queryMethodEnabled = true;
    private boolean queryByPrimaryKeyEnabled = true;
    private boolean countMethodEnabled = true;

    /**
     * 格式化方法名
     * @param format 方法名格式化模板 例如 countTotal{0}
     * @param tableName 表名
     * @return 格式化后的方法名
     */
    public static String formatMethodName(String format,String tableName){
        return MessageFormat.format(format,tableName);
    }

    public String getInsertMethodName() {
        return insertMethodName;
    }

    public void setInsertMethodName(String insertMethodName) {
        this.insertMethodName = formatMethodName(INSERT_METHOD_FORMAT,insertMethodName);
    }

    public String getUpdateMethodName() {
        return updateMethodName;
    }

    public void setUpdateMethodName(String updateMethodName) {
        this.updateMethodName = formatMethodName(UPDATE_METHOD_FORMAT,updateMethodName);
    }

    public String getDeleteMethodName() {
        return deleteMethodName;
    }

    public void setDeleteMethodName(String deleteMethodName) {
        this.deleteMethodName = formatMethodName(DELETE_METHOD_FORMAT,deleteMethodName);
    }

    public String getQueryMethodName() {
        return queryMethodName;
    }

    public void setQueryMethodName(String queryMethodName) {
        this.queryMethodName = formatMethodName(QUERY_METHOD_FORMAT,queryMethodName);
    }

    public String getCountMethodName() {
        return countMethodName;
    }

    public void setCountMethodName(String countMethodName) {
        this.countMethodName = formatMethodName(COUNT_METHOD_FORMAT,countMethodName);;
    }

    public String getDeleteByPrimaryKeyMethodName() {
        return deleteByPrimaryKeyMethodName;
    }

    public void setDeleteByPrimaryKeyMethodName(String deleteByPrimaryKeyMethodName) {
        this.deleteByPrimaryKeyMethodName = formatMethodName(DELETE_BY_PRIMARY_KEY_METHOD_FORMAT,deleteByPrimaryKeyMethodName);
    }

    public String getUpdateByPrimaryKeyMethodName() {
        return updateByPrimaryKeyMethodName;
    }

    public void setUpdateByPrimaryKeyMethodName(String updateByPrimaryKeyMethodName) {
        this.updateByPrimaryKeyMethodName = formatMethodName(UPDATE_BY_PRIMARY_KEY_METHOD_FORMAT,updateByPrimaryKeyMethodName);
    }

    public String getQueryByPrimaryKeyMethodName() {
        return queryByPrimaryKeyMethodName;
    }

    public void setQueryByPrimaryKeyMethodName(String queryByPrimaryKeyMethodName) {
        this.queryByPrimaryKeyMethodName = formatMethodName(QUERY_BY_PRIMARY_KEY_METHOD_FORMAT,queryByPrimaryKeyMethodName);
    }

    public boolean isInsertMethodEnabled() {
        return insertMethodEnabled;
    }

    public void setInsertMethodEnabled(boolean insertMethodEnabled) {
        this.insertMethodEnabled = insertMethodEnabled;
    }

    public boolean isDeleteMethodEnabled() {
        return deleteMethodEnabled;
    }

    public void setDeleteMethodEnabled(boolean deleteMethodEnabled) {
        this.deleteMethodEnabled = deleteMethodEnabled;
    }

    public boolean isDeleteByPrimaryKeyMethodEnabled() {
        return deleteByPrimaryKeyMethodEnabled;
    }

    public void setDeleteByPrimaryKeyMethodEnabled(boolean deleteByPrimaryKeyMethodEnabled) {
        this.deleteByPrimaryKeyMethodEnabled = deleteByPrimaryKeyMethodEnabled;
    }

    public boolean isUpdateMethodEnabled() {
        return updateMethodEnabled;
    }

    public void setUpdateMethodEnabled(boolean updateMethodEnabled) {
        this.updateMethodEnabled = updateMethodEnabled;
    }

    public boolean isUpdateByPrimaryKeyMethodEnabled() {
        return updateByPrimaryKeyMethodEnabled;
    }

    public void setUpdateByPrimaryKeyMethodEnabled(boolean updateByPrimaryKeyMethodEnabled) {
        this.updateByPrimaryKeyMethodEnabled = updateByPrimaryKeyMethodEnabled;
    }

    public boolean isQueryMethodEnabled() {
        return queryMethodEnabled;
    }

    public void setQueryMethodEnabled(boolean queryMethodEnabled) {
        this.queryMethodEnabled = queryMethodEnabled;
    }

    public boolean isQueryByPrimaryKeyEnabled() {
        return queryByPrimaryKeyEnabled;
    }

    public void setQueryByPrimaryKeyEnabled(boolean queryByPrimaryKeyEnabled) {
        this.queryByPrimaryKeyEnabled = queryByPrimaryKeyEnabled;
    }

    public boolean isCountMethodEnabled() {
        return countMethodEnabled;
    }

    public void setCountMethodEnabled(boolean countMethodEnabled) {
        this.countMethodEnabled = countMethodEnabled;
    }

    public void initMethodName(String javaTableName) {
        setInsertMethodName(javaTableName);
        setDeleteMethodName(javaTableName);
        setDeleteByPrimaryKeyMethodName(javaTableName);
        setUpdateMethodName(javaTableName);
        setUpdateByPrimaryKeyMethodName(javaTableName);
        setQueryMethodName(javaTableName);
        setQueryByPrimaryKeyMethodName(javaTableName);
        setCountMethodName(javaTableName);

        initEnabledMethod();
    }

    private void initEnabledMethod() {
        setInsertMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.INSERT_METHOD_ENABLED));
        setDeleteMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.DELETE_METHOD_ENABLED));
        setDeleteByPrimaryKeyMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.DELETE_BY_PRIMARY_KEY_METHOD_ENABLED));
        setUpdateMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.UPDATE_METHOD_ENABLED));
        setUpdateByPrimaryKeyMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.UPDATE_BY_PRIMARY_KEY_METHOD_ENABLED));
        setQueryMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.QUERY_METHOD_ENABLED));
        setQueryByPrimaryKeyEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.QUERY_BY_PRIMARY_KEY_ENABLED));
        setCountMethodEnabled(PropertiesUtils.getBooleanPropertiesDefaultTrue(MethodEnableConstants.COUNT_METHOD_ENABLED));
    }

}
