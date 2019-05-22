package com.github.zhuyizhuo.generator.utils;

import com.alibaba.fastjson.JSON;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;

/**
 * class: LogUtils <br>
 * description: 日志输出 <br>
 * time: 2018/8/6 11:04
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class LogUtils {

    private static final boolean booleanProperties = PropertiesUtils.getBooleanPropertiesDefaultTrue(ConfigConstants.LOG_ENABLED);

    public static void printInfo(String info){
        if (booleanProperties){
            System.out.println(info);
        }
    }

    public static void printErrInfo(String info){
        if (booleanProperties){
            System.err.println(info);
        }
    }

    public static void printException(Exception e){
        if (booleanProperties){
            e.printStackTrace();
        }
    }

    public static void printJsonInfo(String message, Object obj){
        if (booleanProperties){
            System.out.println(message + JSON.toJSONString(obj));
        }
    }
}
