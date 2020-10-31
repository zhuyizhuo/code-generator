package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.enums.LogLevelEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.LogService;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;

import java.util.Properties;

/**
 * class: LogUtils <br>
 * description: 日志输出 <br>
 * time: 2018/8/6 11:04
 *
 * @author zhuo <br>
 * @version 1.0
 */
public class LogUtils {

    private static LogLevelEnums logLevel = LogLevelEnums.INFO;
    private static LogService logService;

    /**
     * 设置日志级别
     * @param logService 日志级别
     */
    public static void setLogService(LogService logService) {
        LogUtils.logService = logService;
    }

    /**
     * 设置日志级别
     * @param level 日志级别
     * @since 1.4.3
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
        if (LogLevelEnums.DEBUG.getLevel() >= getLevel()){
            System.out.println(info);
        }
    }

    /**
     * 打印 info 日志
     * @param info 日志内容
     */
    public static void info(String info){
        if (LogLevelEnums.INFO.getLevel() >= getLevel()){
            System.out.println(info);
        }
    }

    /**
     * 打印错误日志
     * @param errorMsg 错误信息
     */
    public static void error(String errorMsg){
        if (LogLevelEnums.INFO.getLevel() >= getLevel()){
            System.err.println(errorMsg);
        }
    }

    /**
     * 打印堆栈信息 需要日志级别在 DEBUG 以下
     * @param e 异常类
     */
    public static void printException(Exception e){
        if (LogLevelEnums.DEBUG.getLevel() >= getLevel()){
            e.printStackTrace();
        }
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
        if (LogLevelEnums.INFO.getLevel() >= getLevel()){
            System.out.println(message);
            for (String key : properties.stringPropertyNames()) {
                System.out.println(key + "=" + properties.getProperty(key));
            }
        }
    }

    public static void debug(String message, Properties properties) {
        if (LogLevelEnums.DEBUG.getLevel() >= getLevel()){
            System.out.println(message);
            for (String key : properties.stringPropertyNames()) {
                System.out.println(key + "=" + properties.getProperty(key));
            }
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
