package com.github.zhuyizhuo.generator.mybatis.extension.service.impl;

import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.extension.service.GeneratorService;
import com.github.zhuyizhuo.generator.mybatis.extension.service.vo.FilePathVO;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

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
    /** 模板路径及输出文件路径 */
    private List<FilePathVO> filePathList = new ArrayList<FilePathVO>();

    public FreemarkerGenerator(FileOutPathInfo fileOutPathInfo) {
        this(new FtlPathInfo(), fileOutPathInfo);
    }

    public FreemarkerGenerator(FtlPathInfo ftlPathInfo, FileOutPathInfo fileOutPathInfo) {
        this.ftlPathInfo = ftlPathInfo;
        this.fileOutPathInfo = fileOutPathInfo;
    }

    private void initFilePathList() {
        addInOutPath(ftlPathInfo.getPojoFtlPath(), fileOutPathInfo.getPojoOutPutFullPath());
        addInOutPath(ftlPathInfo.getDaoFtlPath(), fileOutPathInfo.getDaoOutPutFullPath());
        addInOutPath(ftlPathInfo.getMybatisXmlFtlPath(), fileOutPathInfo.getXmlOutPutFullPath());
    }

    public void addInOutPath(String templatePath, String fileOutPath) {
        FilePathVO filePathVO = new FilePathVO();
        filePathVO.setTemplatePath(templatePath);
        filePathVO.setFileOutPath(fileOutPath);
        filePathList.add(filePathVO);
    }

    @Override
    public void generate(GenerateInfo generateInfo) {
        fileOutPathInfo.formatPath(generateInfo.getStratificationInfo());
        initFilePathList();
        try {
            for (int i = 0; i < filePathList.size(); i++) {
                FilePathVO filePathVO = filePathList.get(i);
                Freemarker.printFile(filePathVO.getTemplatePath(),filePathVO.getFileOutPath(), generateInfo);
            }
        } catch (Exception e){
            LogUtils.printErrInfo("生成异常");
            e.printStackTrace();
        }
    }
}
