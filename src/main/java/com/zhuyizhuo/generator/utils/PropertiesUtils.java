package com.zhuyizhuo.generator.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:39
 */
public class PropertiesUtils {
    private static final String PROPERTIES_FILE_PATH = "generate-config.properties";
    private static final Properties proInfo = new Properties();

    static{
        try(InputStream resourceAsStream = Resources.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            proInfo.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key){
        return proInfo.getProperty(key);
    }

    /**
     * 获取配置信息 不存在 返回FALSE
     * @param key
     * @return
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
     * 获取配置信息 不存在 返回TRUE
     * @param key
     * @return
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
