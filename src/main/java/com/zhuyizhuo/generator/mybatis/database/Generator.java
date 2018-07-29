package com.zhuyizhuo.generator.mybatis.database;

import com.zhuyizhuo.generator.mybatis.database.convention.FtlPathInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.utils.Freemarker;
import com.zhuyizhuo.generator.utils.GeneratorStringUtils;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:12
 */
public class Generator {
    public static void printAll(DbTableInfo dbTableInfo) {
        try {
            FtlPathInfo ftlPathInfo = new FtlPathInfo();
            String ftlFilePath = GeneratorStringUtils.getFrontPath(ftlPathInfo.getMysqlXmlFtlPath());
            System.out.println("ftlFilePath:" + ftlFilePath);
            String ftlFileName = GeneratorStringUtils.getFileName(ftlPathInfo.getMysqlXmlFtlPath());
            System.out.println("ftlFileName:" + ftlFileName);
            Freemarker.print(ftlFilePath, ftlFileName, dbTableInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
