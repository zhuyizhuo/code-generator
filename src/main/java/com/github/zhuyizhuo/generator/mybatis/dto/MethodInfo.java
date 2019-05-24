package com.github.zhuyizhuo.generator.mybatis.dto;

import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.service.ContextHolder;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
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
//    private Map<String,MethodDescription> methodDescription = new ConcurrentHashMap<>();
    /**
     * method -> methodNameFormatService
     */
    private Map<MethodEnums,FormatService> formatMap = new ConcurrentHashMap<>();
    /**
     * 格式化全部方法名 service
     */
    private FormatService commonMethodFormatService;

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
                                this.commonMethodFormatService != null ? commonMethodFormatService.format(tableName)
                                :tableNameCamelCase)
                : formatService.format(tableNameCamelCase);
    }

    public Map<String,MethodDescription> initMethodName(String tableName, String tableNameCamelCase) {
        this.tableName = tableName;
        this.tableNameCamelCase = tableNameCamelCase;

        Map<String,MethodDescription> methodDescriptionMap = new ConcurrentHashMap<>();
        MethodEnums[] values = MethodEnums.values();
        MethodDescription methodDescription;
        for (int i = 0; i < values.length; i++) {
            String propertiesEnabledKey = values[i].getPropertiesEnabledKey();
            String methodCommentKey = values[i].getMethodCommentKey();
            boolean methodEnabled = PropertiesUtils.getBooleanPropertiesDefaultTrue(propertiesEnabledKey);
            String methodComment = GeneratorStringUtils.isBlank(PropertiesUtils.getProperties(methodCommentKey))
                    ? ContextHolder.getDefaultConfig(methodCommentKey)
                    : PropertiesUtils.getProperties(methodCommentKey);
            methodDescription = new MethodDescription();
            methodDescription.setEnabled(methodEnabled);
            methodDescription.setMethodName(formatMethodName(values[i]));
            methodDescription.setComment(methodComment);
            methodDescription.addParams(new ParamDescription("测试传入参数"));
            methodDescriptionMap.put(values[i].toString(),methodDescription);
        }
        return methodDescriptionMap;
    }

    private boolean getProperties(MethodEnums methodEnums) {
        return PropertiesUtils.getBooleanPropertiesDefaultTrue(methodEnums.getPropertiesEnabledKey());
    }

    public void addCommonMethodFormatService(FormatService formatService) {
        this.commonMethodFormatService = formatService;
    }

    public void setFormatMap(Map<MethodEnums, FormatService> formatMap) {
        this.formatMap = formatMap;
    }

    public Map<MethodEnums, FormatService> getFormatMap() {
        return formatMap;
    }

    public FormatService getCommonMethodFormatService() {
        return commonMethodFormatService;
    }

}
