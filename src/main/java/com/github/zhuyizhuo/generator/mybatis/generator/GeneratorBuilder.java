package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * class: GeneratorBuilder <br>
 * description: TODO <br>
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class GeneratorBuilder {

    public static Generator build(InputStream resourceAsStream){
        try {
            PropertiesUtils.loadProperties(resourceAsStream);
        } catch (IOException e) {
            System.out.println("加载配置文件失败.");
            e.printStackTrace();
        }
        return new Generator();
    }

}