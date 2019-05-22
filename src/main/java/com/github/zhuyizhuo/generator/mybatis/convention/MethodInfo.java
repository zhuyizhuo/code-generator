package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法相关参数
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 15:40
 */
public class MethodInfo {

    /** 方法名 */
    private String insertMethodName;
    private String updateByPrimaryKeyMethodName;
    private String deleteByPrimaryKeyMethodName;
    private String deleteMethodName;
    private String queryMethodName;
    private String queryByPrimaryKeyMethodName;
    private String countMethodName;
    private String batchInsertMethodName;

    /** 是否生成指定方法 */
    private boolean insertMethodEnabled = true;
    private boolean deleteMethodEnabled = true;
    private boolean deleteByPrimaryKeyMethodEnabled = true;
    private boolean updateByPrimaryKeyMethodEnabled = true;
    private boolean queryMethodEnabled = true;
    private boolean queryByPrimaryKeyEnabled = true;
    private boolean countMethodEnabled = true;
    private boolean batchInsertMethodEnabled = true;

    /** 表名 */
    private String tableName;
    /** 表名驼峰命名 */
    private String tableNameCamelCase;
    // methodName -> MethodDescription
    private Map<MethodEnums,MethodDescription> methodDescription = new ConcurrentHashMap<>();
    /**
     * method -> methodNameFormatService
     */
    private Map<MethodEnums,FormatService> formatMap = new ConcurrentHashMap<>();
    /** 格式化全部方法 */
    private FormatService methodFormatService;

    public MethodInfo() {
    }

    /**
     * 格式化方法名
     * @param format MethodEnums
     * @param tableName 表名
     * @return 格式化后的方法名
     */
    public static String formatMethodName(MethodEnums format,String tableName){
        return MessageFormat.format(format.getMethodFormat(),tableName);
    }

    private String formatMethodName(MethodEnums method){
        FormatService formatService = formatMap.get(method);
        return formatService == null
                ? MessageFormat.format(method.getMethodFormat(),
                                this.methodFormatService != null ? methodFormatService.format(tableName)
                                :tableNameCamelCase)
                : formatService.format(tableNameCamelCase);
    }

    public String getInsertMethodName() {
        return insertMethodName;
    }

    public String getDeleteMethodName() {
        return deleteMethodName;
    }

    public String getQueryMethodName() {
        return queryMethodName;
    }

    public String getCountMethodName() {
        return countMethodName;
    }

    public String getDeleteByPrimaryKeyMethodName() {
        return deleteByPrimaryKeyMethodName;
    }

    public String getUpdateByPrimaryKeyMethodName() {
        return updateByPrimaryKeyMethodName;
    }

    public String getQueryByPrimaryKeyMethodName() {
        return queryByPrimaryKeyMethodName;
    }

    public boolean isInsertMethodEnabled() {
        return insertMethodEnabled;
    }

    public boolean isDeleteMethodEnabled() {
        return deleteMethodEnabled;
    }

    public boolean isDeleteByPrimaryKeyMethodEnabled() {
        return deleteByPrimaryKeyMethodEnabled;
    }

    public boolean isUpdateByPrimaryKeyMethodEnabled() {
        return updateByPrimaryKeyMethodEnabled;
    }

    public boolean isQueryMethodEnabled() {
        return queryMethodEnabled;
    }

    public boolean isQueryByPrimaryKeyEnabled() {
        return queryByPrimaryKeyEnabled;
    }

    public String getBatchInsertMethodName() {
        return batchInsertMethodName;
    }

    public boolean isBatchInsertMethodEnabled() {
        return batchInsertMethodEnabled;
    }

    public boolean isCountMethodEnabled() {
        return countMethodEnabled;
    }

    public void initMethodName(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.tableNameCamelCase = tableNameCamelCase;
        initEnabledMethod();
        MethodEnums[] values = MethodEnums.values();
        for (int i = 0; i < values.length; i++) {
            String propertiesEnabledKey = values[i].getPropertiesEnabledKey();
            boolean booleanPropertiesDefaultTrue = PropertiesUtils.getBooleanPropertiesDefaultTrue(propertiesEnabledKey);
            MethodDescription methodDescription = this.methodDescription.get(values[i]);
            if (methodDescription != null){
                //TODO 可自定义添加方法处理  将此处逻辑去掉即可实现不覆盖自定义
                methodDescription.setEnabled(booleanPropertiesDefaultTrue);
                methodDescription.setMethodName(formatMethodName(values[i]));
            } else {
                //TODO 初始化应在何处
                methodDescription = new MethodDescription();
                methodDescription.setEnabled(booleanPropertiesDefaultTrue);
                methodDescription.setMethodName(formatMethodName(values[i]));
                this.methodDescription.put(values[i],methodDescription);
            }
        }

        this.insertMethodName = formatMethodName(MethodEnums.INSERT, tableNameCamelCase);
        this.deleteMethodName = formatMethodName(MethodEnums.DELETE_BY_WHERE, tableNameCamelCase);
        this.deleteByPrimaryKeyMethodName = formatMethodName(MethodEnums.DELETE_BY_PRIMARY_KEY, tableNameCamelCase);
        this.updateByPrimaryKeyMethodName = formatMethodName(MethodEnums.UPDATE_BY_PRIMARY_KEY, tableNameCamelCase);
        this.queryMethodName = formatMethodName(MethodEnums.QUERY_BY_WHERE, tableNameCamelCase);
        this.queryByPrimaryKeyMethodName = formatMethodName(MethodEnums.QUERY_BY_PRIMARY_KEY, tableNameCamelCase);
        this.countMethodName = formatMethodName(MethodEnums.COUNT_BY_WHERE, tableNameCamelCase);;
        this.batchInsertMethodName = formatMethodName(MethodEnums.BATCH_INSERT, tableNameCamelCase);;
        ;
    }

    public void initEnabledMethod() {
        this.insertMethodEnabled = getProperties(MethodEnums.INSERT);
        this.deleteMethodEnabled = getProperties(MethodEnums.DELETE_BY_WHERE);
        this.deleteByPrimaryKeyMethodEnabled = getProperties(MethodEnums.DELETE_BY_PRIMARY_KEY);
        this.updateByPrimaryKeyMethodEnabled = getProperties(MethodEnums.UPDATE_BY_PRIMARY_KEY);
        this.queryMethodEnabled = getProperties(MethodEnums.QUERY_BY_WHERE);
        this.queryByPrimaryKeyEnabled = getProperties(MethodEnums.QUERY_BY_PRIMARY_KEY);
        this.countMethodEnabled = getProperties(MethodEnums.COUNT_BY_WHERE);
        this.batchInsertMethodEnabled = getProperties(MethodEnums.BATCH_INSERT);
    }

    private boolean getProperties(MethodEnums methodEnums) {
        return PropertiesUtils.getBooleanPropertiesDefaultTrue(methodEnums.getPropertiesEnabledKey());
    }

    public void addAllFormat(FormatService formatService) {
        this.methodFormatService = formatService;
    }

    public void addMethodFormat(MethodEnums method, FormatService formatService) {
        this.formatMap.put(method,formatService);
    }

    // 以下 get  为  TEST
    public Map<MethodEnums, MethodDescription> getMethodDescription() {
        return methodDescription;
    }

    public Map<MethodEnums, FormatService> getFormatMap() {
        return formatMap;
    }

    public FormatService getMethodFormatService() {
        return methodFormatService;
    }

}
