package com.zhuyizhuo.generator.mybatis;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.zhuyizhuo.generator.mybatis.dto.JavaTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.vo.Ftl;
import com.zhuyizhuo.generator.utils.Freemarker;
import com.zhuyizhuo.generator.utils.GeneratorStringUtils;
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
    private static Ftl ftl = new Ftl();

    public static void printAll(List<DbTableInfo> dbTableInfoList) {
        try {
            ftl.setCommentInfo(commentInfo);
            StratificationInfo stratificationInfo = new StratificationInfo(getBasePkg());
            JavaTableInfo javaTableInfo = null;
            String fileOutPutPath = getFileOutPutPath();
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                javaTableInfo = new JavaTableInfo();

                DbTableInfo tableInfo = dbTableInfoList.get(i);
                String javaTableName = getJavaTableName(tableInfo.getTableName());
                javaTableInfo.setJavaTableName(javaTableName);
                List<JavaColumnInfo> javaColumnLists = tableInfo.getJavaColumnLists();
                for (int j = 0; j < javaColumnLists.size(); j++) {
                    javaTableInfo.addImportPackages(javaColumnLists.get(j).getJavaDataTypeFullPath());
                }
                stratificationInfo.setDaoName(javaTableName);
                stratificationInfo.setPojoName(javaTableName);

                ftl.setDbTableInfo(tableInfo);
                ftl.setJavaTableInfo(javaTableInfo);
                ftl.setStratificationInfo(stratificationInfo);
                ftl.getMethodInfo().setInsertMethodName(javaTableInfo.getJavaTableName());

                String xmlOutPutPath = fileOutPutPath + getXmlOutPutPath(stratificationInfo.getXmlFullPackage(),tableInfo.getTableName().toLowerCase());
                String pojoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getPojoFullPackage(),ftl.getStratificationInfo().getPojoName());
                String daoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getDaoFullPackage(),ftl.getStratificationInfo().getDaoName());
                
                String bootStrapOutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getBasePackage(), "BootStrap");
                System.out.println("fileOutPutPath: "+ fileOutPutPath);
                System.out.println("xmlOutPutPath: " + xmlOutPutPath);
                System.out.println("pojoOutPutPath: " + pojoOutPutPath);
                System.out.println("daoOutPutPath: " + daoOutPutPath);
                System.out.println("bootStrapOutPath: " + bootStrapOutPath);

                Freemarker.printFile(ftlPathInfo.getMysqlXmlFtlPath(), xmlOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getPojoFtlPath(), pojoOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getDaoFtlPath(), daoOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getBootStrapFtlPath(), bootStrapOutPath, ftl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getBasePkg() {
        return PropertiesUtils.getProperties(ConfigConstants.BASE_PACKAGE);
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

    private static String getJavaTableName(String tableName) {
        return GeneratorStringUtils.changeTableName2Java1(tableName,"_");
    }

    private static String getFileName(String tableName) {
        return tableName.toLowerCase();
    }

}
