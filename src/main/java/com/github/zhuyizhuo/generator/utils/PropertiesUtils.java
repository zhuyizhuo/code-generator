package com.github.zhuyizhuo.generator.utils;

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

    public static void loadProperties(InputStream resourceAsStream) throws IOException,IllegalArgumentException {
        proInfo.load(resourceAsStream);
        CheckUtils.checkDBType();
        CheckUtils.checkNeedConfig();
    }

    public static String getProperties(String key){
        String property = proInfo.getProperty(key);
        return property == null ? null : property.trim();
    }

    /**
     * 判断是否包含对应配置
     * @param key 键
     * @return 存在返回true  不存在返回false
     */
    public static boolean containsKey(String key){
        return GeneratorStringUtils.isNotBlank(proInfo.getProperty(key));
    }

    /**
     * 获取配置信息
     * @param key 键
     * @return 不存在 返回FALSE
     */
    public static boolean getBooleanPropertiesDefaultFalse(String key){
        String properties = getProperties(key);
        if (GeneratorStringUtils.isBlank(properties)){
            return false;
        }
        try {
            return Boolean.parseBoolean(properties);
        } catch(Exception e) {
            LogUtils.printErrInfo("配置有误,key="+key+",值应为 true false");
        }
        return false;
    }

    /**
     * 获取配置信息
     * @param key 键
     * @return 不存在则 返回TRUE
     */
    public static boolean getBooleanPropertiesDefaultTrue(String key){
        String properties = getProperties(key);
        if (GeneratorStringUtils.isBlank(properties)){
            return true;
        }
        try {
            return Boolean.parseBoolean(properties);
        } catch(Exception e) {
            LogUtils.printErrInfo("配置有误,key="+key+",值应为 true false");
        }
        return true;
    }
}
