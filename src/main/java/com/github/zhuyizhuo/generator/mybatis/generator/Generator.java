package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.vo.Ftl;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.Ftl;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:12
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

    public void generate(){
        DbService service =  DbServiceFactory.getDbService();
        Generator.printAll(service.getTableColumns());
    }

    public static void printAll(List<TableInfoFtl> dbTableInfoList) {
        try {
            if (dbTableInfoList == null || dbTableInfoList.size() == 0){
                LogUtils.printInfo("不存在需生成的数据.");
                return;
            }
            String fileOutPutPath = getFileOutPutPath();
            LogUtils.printInfo("fileOutPutPath: "+ fileOutPutPath);
            LogUtils.printInfo(ftlPathInfo.toString());
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                TableInfoFtl tableInfo = dbTableInfoList.get(i);

                methodInfo.initMethodName(tableInfo.getJavaTableName());
                stratificationInfo.initFilesName(tableInfo);
                ftl.setTableInfo(tableInfo);
                ftl.init();

                String xmlOutPutPath = fileOutPutPath + getXmlOutPutPath(stratificationInfo.getXmlFullPackage(), stratificationInfo.getXmlName());
                String pojoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getPojoFullPackage(), stratificationInfo.getPojoName());
                String daoOutPutPath = fileOutPutPath + getJavaOutPutPath(stratificationInfo.getDaoFullPackage(), stratificationInfo.getDaoName());

                Freemarker.printFile(ftlPathInfo.getMysqlXmlFtlPath(), xmlOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getPojoFtlPath(), pojoOutPutPath, ftl);
                Freemarker.printFile(ftlPathInfo.getDaoFtlPath(), daoOutPutPath, ftl);
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
