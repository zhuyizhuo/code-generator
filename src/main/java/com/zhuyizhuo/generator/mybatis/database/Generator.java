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
            String daoFtlFileName = GeneratorStringUtils.getFileName(ftlPathInfo.getDaoFtlPath());
            System.out.println("xmlFtlFileName:" + xmlFtlFileName);
            System.out.println("pojoFtlFileName:" + pojoFtlFileName);
            System.out.println("daoFtlFileName:" + daoFtlFileName);

            Ftl ftl = new Ftl();
            ftl.setJavaTableInfo(javaTableInfo);
            StratificationInfo stratificationInfo = new StratificationInfo();
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                DbTableInfo tableInfo = dbTableInfoList.get(i);
                String javaTableName = getJavaTableName(tableInfo.getTableName());
                javaTableInfo.setJavaTableName(javaTableName);
                ftl.getStratificationInfo().setDaoName(javaTableName);
                ftl.getStratificationInfo().setPojoName(javaTableName);

                ftl.getMethodInfo().setInsertMethodName(javaTableInfo.getJavaTableName());
                ftl.setDbTableInfo(tableInfo);


                String fileOutPutPath = getFileOutPutPath();
                String xmlOutPutPath = fileOutPutPath + getXmlOutPutPath(stratificationInfo.getXmlFullPackage(),tableInfo.getTableName().toLowerCase());
                String pojoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getPojoFullPackage(),ftl.getStratificationInfo().getPojoName());
                String daoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getDaoFullPackage(),ftl.getStratificationInfo().getDaoName());
                System.out.println("xmlOutPutPath: " + xmlOutPutPath);
                System.out.println("pojoOutPutPath: " + pojoOutPutPath);
                System.out.println("daoOutPutPath: " + daoOutPutPath);

                Freemarker.printFile(ftlFilePath, xmlFtlFileName, xmlOutPutPath, ftl);
                Freemarker.printFile(ftlFilePath, pojoFtlFileName, pojoOutPutPath, ftl);
                Freemarker.printFile(ftlFilePath, daoFtlFileName, daoOutPutPath, ftl);
                Freemarker.printFile(ftlFilePath, "bootStrap.ftl", fileOutPutPath+getJavaOutPutPath(stratificationInfo.getBasePackage(),"BootStrap"), ftl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getJavaOutPutPath(String pojoFullPackage, String javaTaleName) {
        return changePackage2Path(pojoFullPackage) + "/" + javaTaleName + ".java";
    }

    private static String getXmlOutPutPath(String xmlFullPackage, String javaTaleName) {
        return changePackage2Path(xmlFullPackage) + "/" + javaTaleName + ".xml";
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
