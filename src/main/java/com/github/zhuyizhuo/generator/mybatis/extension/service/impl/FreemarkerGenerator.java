package com.github.zhuyizhuo.generator.mybatis.extension.service.impl;

import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.extension.service.GeneratorService;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.LogUtils;

/**
 * class: FreemarkerGenerator <br>
 * description: 默认freemarker生成器实现 <br>
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class FreemarkerGenerator implements GeneratorService {
    private FtlPathInfo ftlPathInfo;
    private FileOutPathInfo fileOutPathInfo;

    public FreemarkerGenerator(FileOutPathInfo fileOutPathInfo) {
        this(new FtlPathInfo(), fileOutPathInfo);
    }

    public FreemarkerGenerator(FtlPathInfo ftlPathInfo, FileOutPathInfo fileOutPathInfo) {
        this.ftlPathInfo = ftlPathInfo;
        this.fileOutPathInfo = fileOutPathInfo;
    }

    @Override
    public void generate(GenerateInfo generateInfo) {
        fileOutPathInfo.formatPath(generateInfo.getStratificationInfo());
        try {
            Freemarker.printFile(ftlPathInfo.getPojoFtlPath(), fileOutPathInfo.getPojoOutPutFullPath(), generateInfo);
            Freemarker.printFile(ftlPathInfo.getDaoFtlPath(), fileOutPathInfo.getDaoOutPutFullPath(), generateInfo);
            Freemarker.printFile(ftlPathInfo.getMybatisXmlFtlPath(), fileOutPathInfo.getXmlOutPutFullPath(), generateInfo);
        } catch (Exception e){
            LogUtils.printErrInfo("生成异常");
            e.printStackTrace();
        }
    }
}
