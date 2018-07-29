package com.zhuyizhuo.generator.utils;

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

    public static boolean getBooleanProperties(String key){
        return Boolean.parseBoolean(getProperties(key));
    }
}
