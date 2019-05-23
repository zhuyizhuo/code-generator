package com.github.zhuyizhuo.generator.mybatis.service;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;
import com.github.zhuyizhuo.generator.mybatis.annotation.Resource;
import com.github.zhuyizhuo.generator.mybatis.annotation.Value;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: ContextHolder <br>
 * description: 配置扫描 自动装配 <br>
 * time: 2019/5/23
 *
 * @author yizhuo <br>
 * @since 1.4.0
 */
@Resource("generate-config.properties")
public class ContextHolder {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> classNames = new ArrayList<String>();

    public void init() {
        Class<? extends ContextHolder> aClass = this.getClass();
        //定位
        doLoadConfig(aClass.getAnnotation(Resource.class).value());
        //注册
        doRegister();
        //注入
        doAutowired();
    }

    private void doAutowired() {
        if (beanMap.isEmpty()){return;}

        GenericTokenParser parser = new GenericTokenParser("#{", "}", new TokenHandler() {
            @Override
            public String handleToken(String content) {
                return content;
            }
        });

        for (Map.Entry<String,Object> entry : beanMap.entrySet()){
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                if (!field.isAnnotationPresent(Value.class)){ continue;}

                String beanName = field.getAnnotation(Value.class).value().trim();
                if ("".equals(beanName)){
                    beanName = field.getType().getName();
                }

                field.setAccessible(true);
                try {
                    String key = parser.parse(beanName);
                    String properties = PropertiesUtils.getProperties(key);
                    if (GeneratorStringUtils.isNotBlank(properties)){
                        field.set(entry.getValue(), properties);
                    } else {
                        field.set(entry.getValue(), contextConfig.get(key));
                    }
                } catch (IllegalAccessException e) {
                    LogUtils.printException(e);
                }

            }
        }

    }

    public static void main(String[] args) {
        ContextHolder configScanner = new ContextHolder();
        configScanner.init();
    }

    private void doRegister() {
        if (classNames.isEmpty()) return;
        try {
            for (int i = 0; i < classNames.size(); i++) {
                String className = classNames.get(i);

                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(CoventionClass.class)){
                    String beanName = GeneratorStringUtils.firstLower(clazz.getSimpleName());
                    beanMap.put(beanName,clazz.newInstance());
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
            this.contextConfig.load(resourceAsStream);

            resourceAsStream = classLoader.getResourceAsStream("application.properties");
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

    public <T> T getBean(String beanName){
        return (T) beanMap.get(GeneratorStringUtils.firstLower(beanName));
    }
}
