package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法相关参数
 * @author zhuo
 * @version 1.0
 * time: 2018/7/29 15:40
 */
public class MethodInfo {

    /**
     * method -> methodNameFormatService
     */
    private Map<MethodEnums, FormatService> methodNameFormatServiceMap;
    /**
     * 格式化全部方法名 service
     */
    private FormatService commonMethodNameFormatService;

    public MethodInfo() {}

    public MethodInfo(Map<MethodEnums, FormatService> methodNameFormatServiceMap, FormatService commonMethodNameFormatService) {
        this.methodNameFormatServiceMap = methodNameFormatServiceMap;
        this.commonMethodNameFormatService = commonMethodNameFormatService;
    }

    public Map<String,MethodDescription> initMethodName(String tableName) {
        Map<String,MethodDescription> methodDescriptionMap = new ConcurrentHashMap<>();
        MethodDescription methodDescription;
        MethodEnums[] methodEnums = MethodEnums.values();
        for (MethodEnums method : methodEnums) {
            if (MethodEnums.ALL_METHOD.equals(method)) {
                continue;
            }
            methodDescription = new MethodDescription();
            methodDescription.setEnabled(PropertiesUtils.getBooleanConfigDefaultTrue(method.getPropertiesEnabledKey()));
            methodDescription.setMethodName(formatMethodName(method, tableName));
            methodDescription.setComment(ContextHolder.getConfig(method.getMethodCommentKey()));
            methodDescription.addParams(methodDescription.new ParamDescription(tableName + " 参数对象"));
            methodDescriptionMap.put(method.toString(), methodDescription);
        }
        return methodDescriptionMap;
    }

    private String formatMethodName(MethodEnums method, String tableName){
        FormatService formatService = null;
        if (this.methodNameFormatServiceMap != null){
            formatService = methodNameFormatServiceMap.get(method);
        }
        if (formatService != null){
            return formatService.format(tableName);
        }
        String methodName = this.commonMethodNameFormatService != null ?
                commonMethodNameFormatService.format(tableName) :
                GeneratorStringUtils.changeTableName2CamelFirstUpper(tableName, ContextHolder.getConfig(ConfigConstants.TABLE_SEPARATOR));
        return MessageFormat.format(ContextHolder.getConfig(method.getMethodFormatKey()), methodName);
    }

}
