package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.vo.Ftl;
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

    private static CommentInfo commentInfo = new CommentInfo();
    private static MethodInfo methodInfo = new MethodInfo();
    private static StratificationInfo stratificationInfo = new StratificationInfo();
    private static FileOutPathInfo fileOutPathInfo = new FileOutPathInfo();

    public static Generator build(InputStream inputStream){
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printInfo("加载配置文件失败.");
        }

        DbService service =  DbServiceFactory.getDbService();
        Ftl ftl = new Ftl();
        ftl.setCommentInfo(commentInfo);
        ftl.setMethodInfo(methodInfo);
        ftl.setStratificationInfo(stratificationInfo);
        fileOutPathInfo.init(stratificationInfo);
        return new Generator(service,ftl,fileOutPathInfo);
    }

}