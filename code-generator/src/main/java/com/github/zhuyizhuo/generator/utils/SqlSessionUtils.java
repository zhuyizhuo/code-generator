package com.github.zhuyizhuo.generator.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获取 SqlSession <br>
 * time: 2018/7/30 12:34
 *
 * @author zhuo <br>
 * @version 1.0
 */
public class SqlSessionUtils {
    
    /** SqlSessionFactory缓存 */
    private static final Map<String, SqlSessionFactory> SQL_SESSION_FACTORY_CACHE = new ConcurrentHashMap<>();
    /** 上次配置内容的标记，用于检测配置变化 */
    private static String lastConfigHash = null;
    /** 锁对象，用于同步SqlSessionFactory的创建 */
    private static final Object LOCK = new Object();

    /**
     * 获取SqlSession实例，使用缓存的SqlSessionFactory
     * @return SqlSession instance
     */
    public static SqlSession getSqlSession() throws RuntimeException {
        try {
            String configHash = generateConfigHash(PropertiesUtils.customConfiguration);
            
            // 如果配置发生变化或者缓存不存在，重新创建SqlSessionFactory
            if (!configHash.equals(lastConfigHash) || SQL_SESSION_FACTORY_CACHE.isEmpty()) {
                synchronized (LOCK) {
                    if (!configHash.equals(lastConfigHash) || SQL_SESSION_FACTORY_CACHE.isEmpty()) {
                        createAndCacheSqlSessionFactory(configHash);
                    }
                }
            }
            
            // 从缓存中获取SqlSessionFactory并打开会话
            SqlSessionFactory factory = SQL_SESSION_FACTORY_CACHE.get(configHash);
            return factory.openSession();
        } catch (Exception e){
            throw new RuntimeException("SqlSessionUtils.getSqlSession Exception", e);
        }
    }
    
    /**
     * 创建并缓存SqlSessionFactory
     */
    private static void createAndCacheSqlSessionFactory(String configHash) throws Exception {
        //配置文件
        String resource = "mybatis/mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, PropertiesUtils.customConfiguration);
            SQL_SESSION_FACTORY_CACHE.put(configHash, sqlSessionFactory);
            lastConfigHash = configHash;
        }
    }
    
    /**
     * 生成配置的哈希值，用于检测配置变化
     */
    private static String generateConfigHash(Properties properties) {
        if (properties == null || properties.isEmpty()) {
            return "empty_config";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String key : properties.stringPropertyNames()) {
            sb.append(key).append("=").append(properties.getProperty(key)).append(",");
        }
        return Integer.toHexString(sb.toString().hashCode());
    }
    
    /**
     * 清理SqlSessionFactory缓存，用于配置变更时
     */
    public static void clearCache() {
        synchronized (LOCK) {
            SQL_SESSION_FACTORY_CACHE.clear();
            lastConfigHash = null;
        }
    }
    
    /**
     * 关闭SqlSession的辅助方法，确保资源正确释放
     */
    public static void closeSqlSession(SqlSession session) {
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                LogUtils.error("关闭SqlSession异常: " + e.getMessage());
            }
        }
    }
}
