package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.exception.GeneratorException;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import org.apache.ibatis.io.Resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 资源文件处理工具类
 * @author zhuo
 * @version 1.0
 * time: 2018/7/29 18:39
 */
public class PropertiesUtils {
    /** 自定义配置 */
    public static final Properties customConfiguration = new Properties();

    /**
     * 加载配置文件
     * @param configPath 配置文件路径
     */
    public static Properties loadProperties(String configPath) throws GeneratorException {
        try {
            if (GeneratorStringUtils.isBlank(configPath)) {
                return customConfiguration;
            }
            return loadProperties(new BufferedReader(new InputStreamReader(Resources.getResourceAsStream(configPath), StandardCharsets.UTF_8)));
        } catch (Exception e){
            LogUtils.printException(e);
            throw new GeneratorException("加载配置文件失败,path:" + configPath+",Exception:" + e.getMessage());
        }
    }

    /**
     * 加载配置文件
     */
    public static Properties loadProperties(BufferedReader resourceAsStream) {
        try {
            customConfiguration.load(resourceAsStream);
            return customConfiguration;
        } catch (Exception e) {
            LogUtils.printException(e);
            throw new GeneratorException("加载配置文件失败!");
        }
    }

    public static String getProperties(String key){
        String property = customConfiguration.getProperty(key);
        return property == null ? null : property.trim();
    }

    /**
     * 获取配置信息
     * @param key 键
     * @return 不存在或配置有误 返回 FALSE
     */
    public static boolean getBooleanConfigDefaultFalse(String key){
        try {
            return Boolean.parseBoolean(ContextHolder.getConfig(key));
        } catch(Exception e) {
            LogUtils.info("key:"+key+" 配置有误，有效值为 true 或 false!");
            return false;
        }
    }

    /**
     * 获取配置信息
     * @param key 键
     * @return 不存在则 返回TRUE
     */
    public static boolean getBooleanConfigDefaultTrue(String key){
        try {
            return Boolean.parseBoolean(ContextHolder.getConfig(key));
        } catch(Exception e) {
            LogUtils.info("key:"+key+" 配置有误，有效值为 true 或 false!");
            return true;
        }
    }
}
