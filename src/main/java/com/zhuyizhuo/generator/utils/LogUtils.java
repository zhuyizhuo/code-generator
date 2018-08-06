package com.zhuyizhuo.generator.utils;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;

/**
 * class: LogUtils <br>
 * description: 日志输出 <br>
 * time: 2018/8/6 11:04
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class LogUtils {

    private static final boolean booleanProperties = PropertiesUtils.getBooleanPropertiesDefaultFalse(ConfigConstants.LOG_ENABLED);

    public static void printInfo(String info){
        if (booleanProperties){
            System.out.println(info);
        }
    }
}
