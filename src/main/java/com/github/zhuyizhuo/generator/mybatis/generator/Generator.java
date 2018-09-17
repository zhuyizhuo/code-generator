package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.db.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.extension.service.GeneratorService;
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
    private GeneratorService generatorService;
    private DbService service;
    private GenerateInfo generateInfo;

    public Generator(DbService service, GenerateInfo generateInfo, GeneratorService generatorService) {
        this.service = service;
        this.generateInfo = generateInfo;
        this.generatorService = generatorService;
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

            for (int i = 0; i < dbTableInfoList.size(); i++) {
                generateInfo.init(dbTableInfoList.get(i));
                System.out.println(generateInfo.getMybatisXmlDefinition());
                generatorService.generate(generateInfo);
            }
        } catch (Exception e) {
            LogUtils.printErrInfo("生成数据异常!");
            e.printStackTrace();
        }
    }

}
