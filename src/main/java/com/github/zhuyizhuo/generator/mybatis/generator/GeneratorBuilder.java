package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.io.InputStream;

/**
 * class: GeneratorBuilder <br>
 * description: Builds {@link Generator} instances. <br>
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class GeneratorBuilder {

    public static Generator build(InputStream inputStream){
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printInfo("加载配置文件失败.");
        }
        return new Generator();
    }

}