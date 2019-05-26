package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.convention.ClassCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.database.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.generator.support.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleEnums;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.mybatis.generator.service.GenerateService;
import com.github.zhuyizhuo.generator.mybatis.generator.service.impl.FreemarkerGenerateServiceImpl;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateMetaData;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.TemplateGenerateInfo;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.List;
import java.util.Map;

/**
 *  生成器
 * @author yizhuo
 * @since  1.0
 * time: 2018/7/29 18:12
 * @version 1.4.0
 * modify time : 2019-5-26 22:17:56
 */
public class Generator {
    /** 类注释信息 */
    private ClassCommentInfo classCommentInfo;
    /** 输出路径信息 */
    private FileOutPathInfo fileOutPathInfo;
    /** 方法信息 */
    private MethodInfo methodInfo;
    /** 代码生成器接口 */
    private GenerateService generateService = new FreemarkerGenerateServiceImpl();

    public Generator(FileOutPathInfo fileOutPathInfo, MethodInfo methodInfo) {
        this.classCommentInfo = ContextHolder.getBean("classCommentInfo");
        this.fileOutPathInfo = fileOutPathInfo;
        this.methodInfo = methodInfo;
    }

    public void generate(){
        try {
            DbService dbService = DbServiceFactory.getDbService();
            List<TableInfo> tableColumns = dbService.getTableColumns();
            try {
                doGenerate(tableColumns);
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
                LogUtils.printException(e);
            }
        }
    }

    public void doGenerate(List<TableInfo> dbTableInfoList) {
        try {
            if (dbTableInfoList == null || dbTableInfoList.size() == 0){
                LogUtils.printInfo("不存在需生成的数据.");
                return;
            }

            GenerateMetaData generateMetaData = new GenerateMetaData();
            TemplateGenerateInfo infoHolder = null;
            GenerateInfo generateInfo;
            ModuleEnums[] modules = ModuleEnums.values();
            // 循环多表数据
            for (int i = 0; i < dbTableInfoList.size(); i++) {
                TableInfo tableInfo = dbTableInfoList.get(i);
                Map<String,JavaClassDefinition> javaClassDefinitionMap = this.fileOutPathInfo.initFilesNameAndFormatPath(tableInfo.getTableName(), tableInfo.getTableNameCamelCase());
                Map<String, MethodDescription> methodDescriptionMap = this.methodInfo.initMethodName(tableInfo);
                // 初始化 方法名
                generateInfo = new GenerateInfo(this.classCommentInfo,javaClassDefinitionMap, methodDescriptionMap, tableInfo);
                generateInfo.initXmlInfo();

                for (int j = 0; j < modules.length; j++) {
                    infoHolder = new TemplateGenerateInfo(modules[j].toString(), fileOutPathInfo.getOutputFullPath(modules[j]), generateInfo);
                    generateMetaData.addGenerateInfo(tableInfo.getTableName(),infoHolder);
                }
            }
            generateService.generate(generateMetaData);
        } catch (Exception e) {
            LogUtils.printErrInfo("生成数据异常!");
            LogUtils.printException(e);
        }
    }

}
