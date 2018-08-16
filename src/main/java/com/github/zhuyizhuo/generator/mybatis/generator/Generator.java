package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.vo.Ftl;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:12
 */
public class Generator {
    private static FtlPathInfo ftlPathInfo = new FtlPathInfo();
    private DbService service;
    private Ftl ftl;
    private FileOutPathInfo fileOutPathInfo;

    public Generator(DbService service, Ftl ftl, FileOutPathInfo fileOutPathInfo) {
        this.service = service;
        this.ftl = ftl;
        this.fileOutPathInfo = fileOutPathInfo;
    }

    public void generate(){
        printAll(service.getTableColumns());
    }

    public void printAll(List<TableInfoFtl> dbTableInfoList) {
        try {
            if (dbTableInfoList == null || dbTableInfoList.size() == 0){
                LogUtils.printInfo("不存在需生成的数据.");
                return;
            }

            for (int i = 0; i < dbTableInfoList.size(); i++) {
                init(dbTableInfoList.get(i));

                Freemarker.printFile(ftlPathInfo.getPojoFtlPath(), fileOutPathInfo.getPojoOutPutFullPath(), ftl);
                Freemarker.printFile(ftlPathInfo.getDaoFtlPath(), fileOutPathInfo.getDaoOutPutFullPath(), ftl);
                Freemarker.printFile(ftlPathInfo.getMybatisXmlFtlPath(), fileOutPathInfo.getXmlOutPutFullPath(), ftl);
            }
        } catch (Exception e) {
            LogUtils.printInfo("生成数据异常!");
            e.printStackTrace();
        }
    }

    private void init(TableInfoFtl tableInfo) {
        ftl.init(tableInfo);
        fileOutPathInfo.formatPath(ftl.getStratificationInfo());
    }

}
