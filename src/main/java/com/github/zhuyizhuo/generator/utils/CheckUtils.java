package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.exception.GeneratorException;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;

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

    public static String checkDBType() {
        checkNeedConfig();
        String dbType = PropertiesUtils.getProperties(ConfigConstants.DB_TYPE).toUpperCase().trim();
        try {
            DbTypeEnums.valueOf(dbType);
        } catch (Exception e){
            DbTypeEnums[] values = DbTypeEnums.values();
            StringBuilder errorMsg = new StringBuilder(ConfigConstants.DB_TYPE)
                                    .append(" 配置有误, 支持配置类型:");
            for (int i = 0; i < values.length; i++) {
                errorMsg.append(values[i].name() + " ");
            }
            throw new GeneratorException(errorMsg.toString());
        }
        return dbType;
    }

    public static void checkNeedConfig() {
        StringBuffer errorMsg = new StringBuffer();
        for (int i = 0; i < dbConfig.length; i++) {
            if (isBlank(PropertiesUtils.getProperties(dbConfig[i]))){
                errorMsg.append("未配置 " + dbConfig[i] + "  \n");
            }
        }
        if (errorMsg.length() > 0){
            throw new GeneratorException(errorMsg.toString());
        }
    }

    private static boolean isBlank(String properties) {
        if (GeneratorStringUtils.isBlank(properties)) {
            return true;
        }
        return false;
    }

    public static void assertNotNull(Object moduleType, String errorMsg) throws IllegalArgumentException {
        if (moduleType == null){
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void assertNotNull(String moduleType, String errorMsg) throws IllegalArgumentException {
        if(GeneratorStringUtils.isBlank(moduleType.toString())){
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
