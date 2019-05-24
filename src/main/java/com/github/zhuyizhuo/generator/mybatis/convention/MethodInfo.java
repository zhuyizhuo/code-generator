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

    /** 表名 */
    private String tableName;
    /** 表名驼峰命名 */
    private String tableNameCamelCase;
    /**
     * 方法描述
     * methodName -> MethodDescription
     */
    private Map<String,MethodDescription> methodDescription = new ConcurrentHashMap<>();
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

    public void initMethodName(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.tableNameCamelCase = tableNameCamelCase;

        MethodEnums[] values = MethodEnums.values();
        for (int i = 0; i < values.length; i++) {
            String propertiesEnabledKey = values[i].getPropertiesEnabledKey();
            boolean methodEnabled = PropertiesUtils.getBooleanPropertiesDefaultTrue(propertiesEnabledKey);
            MethodDescription methodDescription = this.methodDescription.get(values[i].toString());
            if (methodDescription != null){
                //TODO 可自定义添加方法处理  将此处逻辑去掉即可实现不覆盖自定义
                methodDescription.setEnabled(methodEnabled);
                methodDescription.setMethodName(formatMethodName(values[i]));
            } else {
                //TODO 初始化应在何处
                methodDescription = new MethodDescription();
                methodDescription.setEnabled(methodEnabled);
                methodDescription.setMethodName(formatMethodName(values[i]));
                this.methodDescription.put(values[i].toString(),methodDescription);
            }
        }
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

    public Map<String, MethodDescription> getMethodDescription() {
        return methodDescription;
    }

    public Map<MethodEnums, FormatService> getFormatMap() {
        return formatMap;
    }

    public FormatService getMethodFormatService() {
        return methodFormatService;
    }

}
