package com.zhuyizhuo.generator.mybatis.database;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.zhuyizhuo.generator.mybatis.database.convention.StratificationInfo;
import com.zhuyizhuo.generator.mybatis.database.dto.JavaTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.database.vo.Ftl;
import com.zhuyizhuo.generator.utils.Freemarker;
import com.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.zhuyizhuo.generator.utils.PropertiesUtils;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:12
 */
public class Generator {
    public static void printAll(List<DbTableInfo> dbTableInfoList) {
        try {
            JavaTableInfo javaTableInfo = new JavaTableInfo();
            FtlPathInfo ftlPathInfo = new FtlPathInfo();
            String ftlFilePath = GeneratorStringUtils.getFrontPath(ftlPathInfo.getMysqlXmlFtlPath());
            System.out.println("ftlFilePath:" + ftlFilePath);
            String xmlFtlFileName = GeneratorStringUtils.getFileName(ftlPathInfo.getMysqlXmlFtlPath());
            String pojoFtlFileName = GeneratorStringUtils.getFileName(ftlPathInfo.getPojoFtlPath());
            System.out.println("xmlFtlFileName:" + xmlFtlFileName);

            Ftl ftl = new Ftl();
            ftl.setJavaTableInfo(javaTableInfo);

            for (int i = 0; i < dbTableInfoList.size(); i++) {
                DbTableInfo tableInfo = dbTableInfoList.get(i);
                javaTableInfo.setJavaTableName(getJavaTableName(tableInfo.getTableName()));
                ftl.getStratificationInfo().setDaoName(getJavaTableName(tableInfo.getTableName()));
                ftl.getMethodInfo().setInsertMethodName(javaTableInfo.getJavaTableName());
                ftl.setDbTableInfo(tableInfo);

                String xmlOutPutPath = getXmlOutPutPath(tableInfo.getTableName().toLowerCase());
                String pojoOutPutPath = getPOJOOutPutPath(javaTableInfo.getJavaTableName());
                System.out.println("xmlOutPutPath: " + xmlOutPutPath);
                System.out.println("pojoOutPutPath: " + pojoOutPutPath);
                Freemarker.printFile(ftlFilePath, xmlFtlFileName, xmlOutPutPath, ftl);
                Freemarker.printFile(ftlFilePath, pojoFtlFileName, pojoOutPutPath, ftl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPOJOOutPutPath(String javaTaleName) {
        return getFileOutPutPath() + changePackage2Path(new StratificationInfo().getPojoFullPackage()) + "/" + javaTaleName + ".java";
    }

    private static String getXmlOutPutPath(String javaTaleName) {
        return getFileOutPutPath() + changePackage2Path(new StratificationInfo().getXmlFullPackage()) + "/" + javaTaleName + ".xml";
    }

    public static String getFileOutPutPath(){
        return PropertiesUtils.getProperties(ConfigConstants.FILE_OUT_PUT_PATH) + "/";
    }

    public static String changePackage2Path(String packagePath){
        return packagePath.replaceAll("\\.","/");
    }

    private static String getJavaTableName(String tableName) {
        return GeneratorStringUtils.changeTableName2Java1(tableName,"_");
    }

    private static String getFileName(String tableName) {
        return tableName.toLowerCase();
    }

}
