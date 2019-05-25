package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.FtlPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.db.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.dto.FilePathInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.utils.Freemarker;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.List;
import java.util.Map;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 18:12
 */
public class Generator {
    /** 生成所需数据 */
    private GenerateInfo generateInfo;
    /** 输出路径信息 */
    private FileOutPathInfo fileOutPathInfo;
    /** 分层信息 */
    private StratificationInfo stratificationInfo;
    /** 方法信息 */
    private MethodInfo methodInfo;

    public Generator(GenerateInfo generateInfo, FileOutPathInfo fileOutPathInfo, StratificationInfo stratificationInfo, MethodInfo methodInfo) {
        this.generateInfo = generateInfo;
        this.fileOutPathInfo = fileOutPathInfo;
        this.stratificationInfo = stratificationInfo;
        this.methodInfo = methodInfo;
    }

    public void generate(){
        try {
            DbService dbService = DbServiceFactory.getDbService();
            List<TableInfo> tableColumns = dbService.getTableColumns();
            try {
                printAll(tableColumns);
            } catch (Exception e){
                LogUtils.printErrInfo("生成数据异常!Exception:" + e.getMessage());
                LogUtils.printException(e);
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

//            List<TemplateGenerateInfo> infoHolders = new ArrayList<>();
//            TemplateGenerateInfo infoHolder = null;
            // 循环多表数据
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                TableInfo tableInfo = dbTableInfoList.get(i);
                Map<String, MethodDescription> methodDescriptionMap =
                        this.methodInfo.initMethodName(tableInfo.getTableName(), tableInfo.getTableNameCamelCase());

                Map<String,JavaClassDefinition> javaClassDefinitionMap = this.stratificationInfo.initFilesName(tableInfo.getTableName(), tableInfo.getTableNameCamelCase());
                // 初始化 方法名
                generateInfo.init(tableInfo, javaClassDefinitionMap, methodDescriptionMap);
                // 初始化输出路径
                List<FilePathInfo> pathInfos = fileOutPathInfo.formatPath(javaClassDefinitionMap, tableInfo.getTableName());

//                RealGenerateInfo info ;
//                for (int j = 0; j < pathInfos.size(); j++) {
//                    info = new RealGenerateInfo();
//                    FilePathInfo pathInfo = pathInfos.get(j);
//                    //TODO generateInfo 应该循环的时候重新初始化  和目前设计冲突
//                    pathInfo.setGenerateInfo(info);
//                }
//                infoHolder = new TemplateGenerateInfo(FtlPathInfo.pojoFtlPath,fileOutPathInfo.getPojoOutPutFullPath(), generateInfo);
//                infoHolders.add(infoHolder);
                LogUtils.printJsonInfo("输出对象:" , generateInfo);

                Freemarker.printFile(FtlPathInfo.pojoFtlPath, fileOutPathInfo.getPojoOutPutFullPath(), generateInfo);
                if(generateInfo.getTableInfo().isHasPrimaryKey()){
                    Freemarker.printFile(FtlPathInfo.PRIVATE_KEY_DAO_TEMPLATE_PATH, fileOutPathInfo.getDaoOutPutFullPath(), generateInfo);
                    Freemarker.printFile(FtlPathInfo.PRIVATE_KEY_MYBATIS_TEMPLATE_PATH, fileOutPathInfo.getXmlOutPutFullPath(), generateInfo);
                } else {
                    Freemarker.printFile(FtlPathInfo.NOKEY_MAPPER_TEMPLATE_PATH, fileOutPathInfo.getDaoOutPutFullPath(), generateInfo);
                    Freemarker.printFile(FtlPathInfo.NOKEY_MYBATIS_TEMPLATE_PATH, fileOutPathInfo.getXmlOutPutFullPath(), generateInfo);
                }
            }

        } catch (Exception e) {
            LogUtils.printErrInfo("生成数据异常!");
            LogUtils.printException(e);
        }
    }

}
