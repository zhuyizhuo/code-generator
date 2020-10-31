package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.exception.GeneratorException;
import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.enums.DbTypeEnums;

import java.util.Properties;

/**
 * class: CheckUtils <br>
 * description: 校验工具 <br>
 * time: 2018/8/20 13:01
 *
 * @author zhuo <br>
 * @since 1.3.0
 */
public class CheckUtils {
    public static final String[] dbConfig = {ConfigConstants.DB_TYPE,ConfigConstants.URL,ConfigConstants.DRIVER,
            ConfigConstants.USERNAME,ConfigConstants.PASSWORD,ConfigConstants.TABLE_SCHEMA};

    public static Properties checkDatabaseConfig() {
        Properties properties = new Properties();
        StringBuffer errorMsg = new StringBuffer();
        for (int i = 0; i < dbConfig.length; i++) {
            String needProperties = PropertiesUtils.getProperties(dbConfig[i]);
            if (GeneratorStringUtils.isBlank(needProperties)){
                errorMsg.append("未配置 " + dbConfig[i] + "  \n");
            } else {
                properties.put(dbConfig[i], needProperties);
            }
        }
        String dbType = PropertiesUtils.getProperties(ConfigConstants.DB_TYPE).toUpperCase();
        try {
            DbTypeEnums.valueOf(dbType);
        } catch (Exception e){
            DbTypeEnums[] values = DbTypeEnums.values();
            errorMsg.append(ConfigConstants.DB_TYPE)
                    .append(" 配置有误, 支持配置类型:");
            for (int i = 0; i < values.length; i++) {
                errorMsg.append(values[i].name() + " ");
            }
        }
        if (errorMsg.length() > 0){
            throw new GeneratorException(errorMsg.toString());
        }
        return properties;
    }

    public static void assertNotNull(Object moduleType, String errorMsg) throws IllegalArgumentException {
        if (moduleType == null){
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void assertNotNull(String moduleType, String errorMsg) throws IllegalArgumentException {
        if(GeneratorStringUtils.isBlank(moduleType)){
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
