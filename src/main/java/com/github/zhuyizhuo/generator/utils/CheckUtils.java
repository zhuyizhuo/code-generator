package com.github.zhuyizhuo.generator.utils;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;

/**
 * class: CheckUtils <br>
 * description: 校验工具 <br>
 * time: 2018/8/20 13:01
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class CheckUtils {

    public static String checkDBType() {
        String dbType = PropertiesUtils.getProperties(ConfigConstants.DB_TYPE);
        if (GeneratorStringUtils.isBlank(dbType)){
            String errorMsg = "未指定数据库类型:" + ConfigConstants.DB_TYPE + ",请在generate-config.properties中指定.DB_TYPE 值列表请参照 DbTypeEnums.java";
            LogUtils.printInfo(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return dbType;
    }

}
