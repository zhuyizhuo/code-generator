package com.github.zhuyizhuo.generator.mybatis;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.io.Resources;

/**
 * mybatis代码生成器
 * @author yizhuo 2018/7/27 20:55
 * @version 1.0
 */
public class BootStrap {

    public static void main(String[] args) throws Exception {
        Generator build = GeneratorBuilder.build(Resources.getResourceAsStream(ConfigConstants.PROPERTIES_FILE_PATH));
        build.generate();
    }

}
