package com.github.zhuyizhuo.generator.mybatis;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;

/**
 * mybatis代码生成器
 * @author yizhuo 2018/7/27 20:55
 * @version 1.0
 */
public class BootStrap {

    public static void main(String[] args) throws IOException {
        //        GeneratorService build = GeneratorBuilder.build(Resources.getResourceAsStream(ConfigConstants.PROPERTIES_FILE_PATH));
        Generator build = new GeneratorBuilder().build(Resources.getResourceAsStream(""));
        build.generate();
    }

}
