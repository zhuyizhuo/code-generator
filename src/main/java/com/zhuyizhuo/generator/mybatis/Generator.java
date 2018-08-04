package com.zhuyizhuo.generator.mybatis;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.zhuyizhuo.generator.mybatis.vo.Ftl;
import com.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.zhuyizhuo.generator.utils.Freemarker;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 18:12
 */
public class Generator {
    private static FtlPathInfo ftlPathInfo = new FtlPathInfo();
    private static CommentInfo commentInfo = new CommentInfo();
    private static MethodInfo methodInfo = new MethodInfo();
    private static StratificationInfo stratificationInfo = new StratificationInfo();
    private static Ftl ftl = new Ftl();

    static{
        ftl.setCommentInfo(commentInfo);
        ftl.setMethodInfo(methodInfo);
        ftl.setStratificationInfo(stratificationInfo);
    }

    public static void printAll(List<TableInfoFtl> dbTableInfoList) {
        try {
            String fileOutPutPath = getFileOutPutPath();
            System.out.println("fileOutPutPath: "+ fileOutPutPath);

            String bootStrapOutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getBasePackage(), "BootStrap");
            System.out.println("bootStrapOutPath: " + bootStrapOutPath);
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                TableInfoFtl tableInfo = dbTableInfoList.get(i);

                methodInfo.initMethodName(tableInfo.getJavaTableName());
                stratificationInfo.initFilesName(tableInfo);
                ftl.setTableInfo(tableInfo);
                ftl.init();

                String xmlOutPutPath = fileOutPutPath + getXmlOutPutPath(stratificationInfo.getXmlFullPackage(), stratificationInfo.getXmlName());
                String pojoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getPojoFullPackage(), stratificationInfo.getPojoName());
                String daoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getDaoFullPackage(), stratificationInfo.getDaoName());
                System.out.println("xmlOutPutPath: " + xmlOutPutPath);
                System.out.println("pojoOutPutPath: " + pojoOutPutPath);
                System.out.println("daoOutPutPath: " + daoOutPutPath);

                Freemarker.printFile(ftlPathInfo.getMysqlXmlFtlPath(), xmlOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getPojoFtlPath(), pojoOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getDaoFtlPath(), daoOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getBootStrapFtlPath(), bootStrapOutPath, ftl);
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
        String path = PropertiesUtils.getProperties(ConfigConstants.FILE_OUT_PUT_PATH);
        if (StringUtils.isBlank(path)){
             path = System.getProperty("user.dir") + "/src/main/java/";
        }
        return path + "/";
    }

    public static String changePackage2Path(String packagePath){
        return packagePath.replaceAll("\\.","/");
    }

}
