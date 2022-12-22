package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.enums.LogLevelEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.LogService;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 日志输出工具类 <br>
 * time: 2018/8/6 11:04
 *
 * @author zhuo <br>
 * @version 1.0
 */
public class LogUtils {

    private static LogLevelEnums logLevel = LogLevelEnums.INFO;
    private static LogService logService;
    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    /**
     * 设置打印日志的 service
     * @param logService 日志级别
     */
    public static void setLogService(LogService logService) {
        LogUtils.logService = logService;
    }

    /**
     * 设置日志级别
     * @param level 日志级别
     * @since 1.5.1
     */
    public static void setLevel(LogLevelEnums level) {
        LogUtils.logLevel = level;
    }

    /**
     * 设置日志级别
     * @param level 日志级别
     * @since 1.5.0
     */
    public static void setLevel(String level) {
        try{
            LogUtils.logLevel = LogLevelEnums.valueOf(level);
        } catch (Exception e){
            LogUtils.logLevel = LogLevelEnums.INFO;
        }
    }

    /**
     * 打印 debug 日志
     * @param info 日志内容
     */
    public static void debug(String info){
        logger.debug(info);
    }

    /**
     * 打印 info 日志
     * @param info 日志内容
     */
    public static void info(String info){
        logger.info(info);
    }

    /**
     * 打印错误日志
     * @param errorMsg 错误信息
     */
    public static void error(String errorMsg){
        logger.error(errorMsg);
    }

    /**
     * 打印堆栈信息 需要日志级别在 DEBUG 以下
     * @param e 异常类
     */
    public static void printException(Exception e){
        logger.error("异常:", e);
    }

    /**
     * 打印生成器对象
     * @param generateInfo 输出到模板的元数据对象
     */
    public static void logGenerateInfo(GenerateInfo generateInfo){
        if (LogLevelEnums.INFO.getLevel() >= getLevel() && logService != null){
            logService.logGenerateInfo(generateInfo);
        }
    }

    public static void logProperties(String message, Properties properties) {
        logger.info(message);
        for (String key : properties.stringPropertyNames()) {
            logger.info(key + "=" + properties.getProperty(key));
        }
    }

    public static void debug(String message, Properties properties) {
        logger.debug(message);
        for (String key : properties.stringPropertyNames()) {
            logger.debug(key + "=" + properties.getProperty(key));
        }
    }

    /**
     * 获取日志级别
     * @return 当前日志级别
     */
    private static int getLevel(){
        return logLevel.getLevel();
    }


}
