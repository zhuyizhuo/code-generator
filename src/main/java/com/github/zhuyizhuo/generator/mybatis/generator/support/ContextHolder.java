package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.annotation.Resource;
import com.github.zhuyizhuo.generator.annotation.Value;
import com.github.zhuyizhuo.generator.exception.GeneratorException;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置扫描 自动装配 <br>
 * <blockquote><pre>
 *     扫描在 generator.properties 中配置的装配类(全路径)，
 *     如果类上存在 CoventionClass 注解，则会对类中 @Value 标注的属性注入配置文件的对应配置。
 * </pre></blockquote>
 * time: 2019/5/23
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
@Resource("generate-config.properties")
public class ContextHolder {

    private final static String resourceFile = "generator.properties";
    /** 系统默认配置信息 */
    private static Properties contextConfig = new Properties();
    /** 生成器配置对象 map */
    private static Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
    /** 自定义配置信息 */
    private Properties customerProperties;

    private List<String> classNames = new ArrayList<String>();

    private ContextHolder() {}

    private ContextHolder(Properties customerProperties) {
        this.customerProperties = customerProperties;
    }

    /**
     * 获取上下文实例
     * @param properties
     */
    public static ContextHolder newInstance(Properties properties){
        ContextHolder contextHolder = new ContextHolder(properties);
        contextHolder.init();
        return contextHolder;
    }

    private void init() {
        try {
            Class<? extends ContextHolder> aClass = this.getClass();
            //定位
            doLoadConfig(aClass.getAnnotation(Resource.class).value());
            //注册
            doRegister();
            //注入
            doAutowired();
        } catch (Exception e){
            LogUtils.printException(e);
            throw new GeneratorException("生成器初始化失败!");
        }
    }

    private void doAutowired() {
        if (beanMap.isEmpty()){return;}

        GenericTokenParser parser = new GenericTokenParser("#{", "}", new TokenHandler() {
            @Override
            public String handleToken(String content) {
                return handleConfig(content);
            }
        });

        initProperties(parser);

        for (Map.Entry<String,Object> entry : beanMap.entrySet()){
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                if (!field.isAnnotationPresent(Value.class)){ continue;}

                String configValue = field.getAnnotation(Value.class).value().trim();
                if ("".equals(configValue)){
                    configValue = field.getType().getName();
                }

                field.setAccessible(true);
                try {
                    while (configValue.contains("#")){
                        configValue = parser.parse(configValue);
                    }
                    field.set(entry.getValue(), configValue);
                } catch (IllegalAccessException e) {
                    LogUtils.printException(e);
                }
            }
        }

    }

    private void initProperties(GenericTokenParser parser) {
        loopProperties(contextConfig, parser);
        if (customerProperties != null){
            loopProperties(customerProperties, parser);
            //用户配置将覆盖系统默认同名配置
            contextConfig.putAll(customerProperties);
        }
        LogUtils.debug("配置信息:" , contextConfig);
    }

    /**
     * 处理带有 #{} 的属性
     */
    private void loopProperties(Properties proInfo, GenericTokenParser parser) {
        Enumeration<?> enumeration = proInfo.propertyNames();
        while (enumeration.hasMoreElements()){
            String key = (String)enumeration.nextElement();
            String property = proInfo.getProperty(key);
            if (property != null && property.length() > 1){
                while (property.contains("#")){
                    property = parser.parse(property);
                }
                proInfo.setProperty(key, property);
            }
        }
    }

    /**
     * <p>
     * 按顺序依次获取配置,如果都未获取到 则返回空字符串
     *
     * <blockquote><pre>
     * 优先级：
     * 1. 先获取用户的 java 配置
     * 2. 获取配置文件配置
     * 3. 获取环境变量
     * 4. 获取系统变量
     * </pre></blockquote><p>
     * @param key 配置键
     * @return 按顺序依次获取配置,如果都未获取到 则返回空字符串
     */
    private String handleConfig(String key) {
        String property;
        if (customerProperties != null) {
            property = customerProperties.getProperty(key);
            if (GeneratorStringUtils.isNotBlank(property)){
                return property.trim();
            }
        }
        property = contextConfig.getProperty(key);
        if (GeneratorStringUtils.isNotBlank(property)){
            return property.trim();
        }
        property = System.getenv(key);
        if (GeneratorStringUtils.isNotBlank(property)) {
            return property.trim();
        }
        property = System.getProperty(key);
        return GeneratorStringUtils.isNotBlank(property) ? property.trim() : "";
    }

    private void doRegister() {
        if (classNames.isEmpty()) {return;}
        try {
            for (int i = 0; i < classNames.size(); i++) {
                String className = classNames.get(i);

                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(CoventionClass.class)){
                    String beanName = GeneratorStringUtils.firstLower(clazz.getSimpleName());
                    beanMap.put(beanName, clazz.newInstance());
                }
            }
        } catch (Exception e) {
            LogUtils.printException(e);
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8)));

            resourceAsStream = classLoader.getResourceAsStream(resourceFile);
            Properties contextConfig = new Properties();
            contextConfig.load(resourceAsStream);
            String property = contextConfig.getProperty("generate.convention.sourceType");
            String[] split = property.split(",");
            this.classNames = Arrays.asList(split);
        } catch (IOException e) {
            LogUtils.printException(e);
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    LogUtils.printException(e);
                }
            }
        }
    }

    public static <T> T getBean(String beanName){
        return (T) beanMap.get(GeneratorStringUtils.firstLower(beanName));
    }

    public static String getConfig(String key){
        return contextConfig.getProperty(key);
    }
}
