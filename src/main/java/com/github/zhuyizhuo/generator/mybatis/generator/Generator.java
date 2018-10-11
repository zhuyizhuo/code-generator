package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.db.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.extension.service.GeneratorService;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:12
 */
public class Generator {
    /** 具体的生成器 */
    private GeneratorService generatorService;
    /** 数据源 */
    private DbService service;
    /** 生成所需数据 */
    private GenerateInfo generateInfo;
    /** 输出路径信息 */
    private FileOutPathInfo fileOutPathInfo;
    /** 分层信息 */
    private StratificationInfo stratificationInfo;

    public Generator(GenerateInfo generateInfo, GeneratorService generatorService,FileOutPathInfo fileOutPathInfo,StratificationInfo stratificationInfo) {
        this.service = DbServiceFactory.getDbService();
        this.generateInfo = generateInfo;
        this.generatorService = generatorService;
        this.fileOutPathInfo = fileOutPathInfo;
        this.stratificationInfo = stratificationInfo;
    }

    public void generate(){
        try {
            List<TableInfo> tableColumns = service.getTableColumns();
            try {
                printAll(tableColumns);
            } catch (Exception e){
                LogUtils.printErrInfo("生成数据异常!Exception:" + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e){
            Throwable cause = e.getCause();
            if (cause != null && cause.toString().contains("Error setting driver on UnpooledDataSource.")){
                LogUtils.printErrInfo("请检查是否添加对应数据库驱动依赖!");
                LogUtils.printErrInfo("Exception: " + cause.toString());
            } else {
                LogUtils.printErrInfo("查询数据库结构异常!Exception:" + e.getMessage());
            }
        }
    }

    public void printAll(List<TableInfo> dbTableInfoList) {
        try {
            if (dbTableInfoList == null || dbTableInfoList.size() == 0){
                LogUtils.printInfo("不存在需生成的数据.");
                return;
            }

            FtlPathInfo ftlPathInfo = new FtlPathInfo();
            //循环多表数据
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                this.stratificationInfo.initFilesName(dbTableInfoList.get(i).getTableName());
                generateInfo.init(dbTableInfoList.get(i));
                generateInfo.setStratificationInfo(this.stratificationInfo);
                generateInfo.initXmlInfo(this.stratificationInfo);
                //初始化输出路径
                fileOutPathInfo.formatPath(this.stratificationInfo);

                // 初始化输入输出
                generatorService.addInOutPath(ftlPathInfo.getPojoFtlPath(), fileOutPathInfo.getPojoOutPutFullPath())
                        .addInOutPath(ftlPathInfo.getDaoFtlPath(), fileOutPathInfo.getDaoOutPutFullPath())
                        .addInOutPath(ftlPathInfo.getMybatisXmlFtlPath(), fileOutPathInfo.getXmlOutPutFullPath());

                generatorService.generate(generateInfo);
            }
        } catch (Exception e) {
            LogUtils.printErrInfo("生成数据异常!");
            e.printStackTrace();
        }
    }

}
