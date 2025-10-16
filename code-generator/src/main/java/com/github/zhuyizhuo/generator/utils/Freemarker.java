package com.github.zhuyizhuo.generator.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * freemarker 工具类
 * 创建时间：2015年2月8日
 * @version 1.0
 */
public class Freemarker {
    /** 模板缓存 */
    private static final Map<String, Template> TEMPLATE_CACHE = new ConcurrentHashMap<>();
    /** 配置缓存 */
    private static final Map<String, Configuration> CONFIG_CACHE = new ConcurrentHashMap<>();

	/**
	 * 输出对象到文件
	 * @param ftlFullPath ftl模板路径
	 * @param outPutPath 输出文件全路径
	 * @param outPutObject 输出对象
     * @throws Exception 获取模板异常抛出
	 */
	public static void printFile(String ftlFullPath, String outPutPath, Object outPutObject) throws Exception{
		printFile(GeneratorStringUtils.getFrontPath(ftlFullPath),GeneratorStringUtils.getFileName(ftlFullPath),outPutPath,outPutObject);
	}

	/**
	 * 将root对象输出到文件
	 * @param ftlPath   ftl文件路径
	 * @param ftlName	 ftl文件名
	 * @param outPutPath	输出后的文件全部路径
	 * @param outPutObject 输出对象
     * @throws Exception 获取模板异常抛出
	 */
	public static void printFile(String ftlPath, String ftlName, String outPutPath, Object outPutObject) throws Exception {
		try {
			File file = new File(outPutPath);
			//判断有没有父路径，就是判断文件整个路径是否存在
			if(file != null && file.getParentFile() != null && !file.getParentFile().exists()){
				//不存在就全部创建
				file.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlPath, ftlName);
			//模版输出
			template.process(outPutObject, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			LogUtils.printException(e);
            throw new Exception("模板渲染异常: " + e.getMessage(), e);
		} catch (IOException e) {
			LogUtils.printException(e);
            throw new Exception("文件输出异常: " + e.getMessage(), e);
		}
	}
	
	/**
	 * 通过文件名加载模版
	 * @param ftlPath ftl文件路径
     * @param ftlName ftl文件名
	 * @throws Exception 读取模板失败抛出异常
     * @return Template 模板
	 */
	public static Template getTemplate(String ftlPath, String ftlName) throws Exception{
        // 构建缓存key
        String cacheKey = ftlPath + "/" + ftlName;
        
        // 检查缓存中是否已存在该模板
        if (TEMPLATE_CACHE.containsKey(cacheKey)) {
            return TEMPLATE_CACHE.get(cacheKey);
        }
        
        try {
            Configuration cfg = getConfiguration(ftlPath);
            
            // 加载模板
            Template template = cfg.getTemplate(ftlName);
            
            // 缓存模板
            TEMPLATE_CACHE.put(cacheKey, template);
            
            return template;
		} catch (IOException e) {
			LogUtils.printException(e);
            throw new Exception("加载模板失败: " + ftlPath + "/" + ftlName + ", 异常: " + e.getMessage(), e);
		}
	}
    
    /**
     * 获取Freemarker配置，使用缓存避免重复创建
     * @param ftlPath 模板路径
     * @return Configuration实例
     */
    private static Configuration getConfiguration(String ftlPath) {
        // 检查配置缓存
        if (CONFIG_CACHE.containsKey(ftlPath)) {
            return CONFIG_CACHE.get(ftlPath);
        }
        
        // 创建新的配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30); // 更新到较新的版本
        cfg.setEncoding(Locale.CHINA, "utf-8");
        cfg.setTemplateLoader(new ClassTemplateLoader(Freemarker.class.getClassLoader(), ftlPath));
        
        // 配置模板异常处理
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // 配置模板缓存
        cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
        cfg.setTemplateUpdateDelayMilliseconds(3600000); // 1小时的缓存时间
        
        // 缓存配置
        CONFIG_CACHE.put(ftlPath, cfg);
        
        return cfg;
    }
    
    /**
     * 清理模板缓存，用于开发调试
     */
    public static void clearCache() {
        TEMPLATE_CACHE.clear();
        CONFIG_CACHE.clear();
    }
}
