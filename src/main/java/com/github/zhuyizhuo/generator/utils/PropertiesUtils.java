package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:39
 */
public class PropertiesUtils {

    public static final Properties proInfo = new Properties();

    private static final String[] needProperties = {ConfigConstants.URL,ConfigConstants.DB_TYPE,ConfigConstants.DRIVER,ConfigConstants.USERNAME,ConfigConstants.PASSWORD,ConfigConstants.TABLE_SCHEMA};

    public static void loadProperties(InputStream resourceAsStream) throws IOException,IllegalArgumentException {
        proInfo.load(resourceAsStream);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < needProperties.length; i++) {
            if (isBlank(getProperties(needProperties[i]))){
                sb.append(needProperties[i] + "未配置 \n");
            }
        }
        if (sb.length() > 0){
            LogUtils.printInfo(sb.toString());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private static boolean isBlank(String properties) {
        if (StringUtils.isBlank(properties)) {
            return true;
        }
        return false;
    }

    public static String getProperties(String key){
        return proInfo.getProperty(key);
    }

    /**
     * 获取配置信息
     * @param key
     * @return 不存在 返回FALSE
     */
    public static boolean getBooleanPropertiesDefaultFalse(String key){
        String properties = getProperties(key);
        if (StringUtils.isBlank(properties)){
            return false;
        }
        try {
            return Boolean.parseBoolean(properties);
        } catch(Exception e) {
            LogUtils.printInfo("配置有误,key="+key+",值应为 true false");
        }
        return false;
    }

    /**
     * 获取配置信息
     * @param key
     * @return 不存在则 返回TRUE
     */
    public static boolean getBooleanPropertiesDefaultTrue(String key){
        String properties = getProperties(key);
        if (StringUtils.isBlank(properties)){
            return true;
        }
        try {
            return Boolean.parseBoolean(properties);
        } catch(Exception e) {
            LogUtils.printInfo("配置有误,key="+key+",值应为 true false");
        }
        return true;
    }
}
