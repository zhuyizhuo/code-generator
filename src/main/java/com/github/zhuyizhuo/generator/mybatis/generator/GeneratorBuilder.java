package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.*;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.vo.Ftl;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.io.InputStream;

/**
 * class: GeneratorBuilder <br>
 * description: Builds {@link Generator} instances. <br>
 *
 * @author yizhuo <br>
 * @version 1.2.0
 */
public class GeneratorBuilder {

    private CommentInfo commentInfo;
    private MethodInfo methodInfo;
    private StratificationInfo stratificationInfo;
    private FileOutPathInfo fileOutPathInfo;

    public GeneratorBuilder() {
        this(new CommentInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo) {
        this(commentInfo, new MethodInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo, MethodInfo methodInfo) {
        this(commentInfo,methodInfo,new StratificationInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo) {
        this(commentInfo,methodInfo,stratificationInfo,new FileOutPathInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo, FileOutPathInfo fileOutPathInfo) {
        this.commentInfo = commentInfo;
        this.methodInfo = methodInfo;
        this.stratificationInfo = stratificationInfo;
        this.fileOutPathInfo = fileOutPathInfo;
    }

    public GeneratorBuilder addXmlNameFormat(FormatService formatService){
        this.stratificationInfo.addXmlNameFormat(formatService);
        return this;
    }

    public GeneratorBuilder addBeanNameFormat(FormatService formatService){
        this.stratificationInfo.addBeanNameFormat(formatService);
        return this;
    }

    public GeneratorBuilder addDaoNameFormat(FormatService formatService){
        this.stratificationInfo.addDaoNameFormat(formatService);
        return this;
    }

    public MethodCommentInfo getMethodComment(){
        return this.methodInfo;
    }

    public Generator build(InputStream inputStream){
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printInfo("加载配置文件失败.");
        }
        DbService service =  DbServiceFactory.getDbService();

        stratificationInfo.init();

        Ftl ftl = new Ftl();
        ftl.setCommentInfo(commentInfo);
        ftl.setMethodInfo(methodInfo);
        ftl.setStratificationInfo(stratificationInfo);
        fileOutPathInfo.init(stratificationInfo);
        return new Generator(service,ftl,fileOutPathInfo);
    }

}