package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
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

    private static CommentInfo commentInfo;
    private static MethodInfo methodInfo;
    private static StratificationInfo stratificationInfo;
    private static FileOutPathInfo fileOutPathInfo;

    public static Generator build(InputStream inputStream){
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printInfo("加载配置文件失败.");
        }

        DbService service =  DbServiceFactory.getDbService();

        stratificationInfo = new StratificationInfo(PropertiesUtils.getProperties(ConfigConstants.BASE_PACKAGE));
        commentInfo = new CommentInfo();
        methodInfo = new MethodInfo();
        fileOutPathInfo = new FileOutPathInfo();

        fileOutPathInfo.initBasePath();
        Ftl ftl = new Ftl();
        ftl.setCommentInfo(commentInfo);
        ftl.setMethodInfo(methodInfo);
        ftl.setStratificationInfo(stratificationInfo);
        initFileOutPutPath();
        return new Generator(service,ftl,fileOutPathInfo);
    }

    private static void initFileOutPutPath() {
        if (PropertiesUtils.containsKey(ConfigConstants.XML_OUT_PUT_PATH)){
            fileOutPathInfo.setXmlOutPutPath(PropertiesUtils.getProperties(ConfigConstants.XML_OUT_PUT_PATH));
        };
        if (PropertiesUtils.containsKey(ConfigConstants.DAO_OUT_PUT_PATH)){
            fileOutPathInfo.setDaoOutPutPath(PropertiesUtils.getProperties(ConfigConstants.DAO_OUT_PUT_PATH));
        };
        if (PropertiesUtils.containsKey(ConfigConstants.POJO_OUT_PUT_PATH)){
            fileOutPathInfo.setPojoOutPutPath(PropertiesUtils.getProperties(ConfigConstants.POJO_OUT_PUT_PATH));
        };
        fileOutPathInfo.init(stratificationInfo);
    }

}