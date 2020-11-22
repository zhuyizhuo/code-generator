package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.enums.ErrorTypeEnums;
import com.github.zhuyizhuo.generator.enums.FileTypeEnums;
import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.database.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.database.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.service.GenerateService;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.TemplateGenerateService;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.mybatis.generator.support.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateMetaData;
import com.github.zhuyizhuo.generator.mybatis.vo.ModulePathInfo;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author zhuo
 * @since  1.0
 */
public class DefaultGenerator implements Generator{
    /** 输出路径信息 */
    private FileOutPathInfo fileOutPathInfo;
    /** 方法信息 */
    private MethodInfo methodInfo;
    /** 代码生成器接口 */
    private GenerateService generateService;

    DefaultGenerator(FileOutPathInfo fileOutPathInfo, MethodInfo methodInfo, GenerateService generateService) {
        this.fileOutPathInfo = fileOutPathInfo;
        this.methodInfo = methodInfo;
        this.generateService = generateService;
    }

    /**
     * 替换系统默认模板
     * @param moduleType 模块类型
     * @param templatePath 对应的模板路径
     * @since 1.5.0
     */
    void replaceDefaultTemplate(ModuleTypeEnums moduleType, String templatePath){
        if (generateService instanceof TemplateGenerateService) {
            TemplateGenerateService service = (TemplateGenerateService) this.generateService;
            service.addTemplate(moduleType.name(), templatePath);
        }
    }

    /**
     *  新增自定义 java 模块
     * @param javaModuleInfo java 模块信息
     */
    void addJavaTemplate(JavaModuleInfo javaModuleInfo){
        if (generateService instanceof TemplateGenerateService) {
            TemplateGenerateService service = (TemplateGenerateService) this.generateService;
            service.addTemplate(javaModuleInfo.getModuleType(), javaModuleInfo.getTemplatePath());
        }
        this.fileOutPathInfo.addJavaTemplate(javaModuleInfo);
    }

    /**
     *  新增自定义模块
     * @param customizeModuleInfo 自定义模块信息
     */
    void addCustomizeModuleInfo(CustomizeModuleInfo customizeModuleInfo){
        if (generateService instanceof TemplateGenerateService) {
            TemplateGenerateService service = (TemplateGenerateService) this.generateService;
            service.addTemplate(customizeModuleInfo.getModuleType(), customizeModuleInfo.getTemplatePath());
        }
        this.fileOutPathInfo.addCustomizeModule(customizeModuleInfo);
    }

    /**
     * 生成文件
     */
    @Override
    public void generate(){
        List<TableInfo> tableColumns;
        try {
            DbService dbService = DbServiceFactory.getDbService();
            tableColumns = dbService.getTableColumns();
        } catch (UnsupportedOperationException ue){
            LogUtils.error(ue.getMessage());
            LogUtils.printException(ue);
            return;
        } catch (Exception e){
            LogUtils.printException(e);
            Throwable cause = e.getCause();
            if (cause == null) {
                return;
            }
            String error = cause.toString();
            if (error.contains(ErrorTypeEnums.CHECK_DEPENDENCE.getErrorMsg())) {
                LogUtils.error(ErrorTypeEnums.CHECK_DEPENDENCE.getMessage());
            } else if(error.contains(ErrorTypeEnums.CHECK_DATABASE_CONFIG.getErrorMsg())){
                LogUtils.error(ErrorTypeEnums.CHECK_DATABASE_CONFIG.getMessage());
            } else {
                LogUtils.error(ErrorTypeEnums.ERROR_DATABASE_CONFIG.getMessage());
            }
            return;
        }
        try {
            doGenerate(tableColumns);
        } catch (UnsupportedOperationException ue){
            LogUtils.error(ue.getMessage());
        } catch (Exception e){
            LogUtils.error("生成数据异常!Exception:" + e.getMessage());
            LogUtils.printException(e);
        }
    }

    private void doGenerate(List<TableInfo> dbTableInfoList) {
        if (dbTableInfoList == null || dbTableInfoList.size() == 0){
            LogUtils.info("不存在需生成的数据.");
            return;
        }

        GenerateMetaData generateMetaData = new GenerateMetaData();
        ModulePathInfo modulePathInfo = null;
        GenerateInfo generateInfo;
        // 循环多表数据
        for (int i = 0; i < dbTableInfoList.size(); i++) {
            TableInfo tableInfo = dbTableInfoList.get(i);
            String tableName = tableInfo.getTableName();
            this.fileOutPathInfo.initFileNamesAndOutPutFullPath(tableName, tableInfo.getTableNameCamelCase());

            Map<String, MethodDescription> methodDescriptionMap = this.methodInfo.initMethodName(tableInfo.getTableName());
            // 初始化 方法名
            generateInfo = new GenerateInfo(ContextHolder.getBean("classCommentInfo"),
                    this.fileOutPathInfo.getJavaClassDefinitionMap(),
                    methodDescriptionMap, tableInfo);

            List<ModuleInfo> allModule = this.fileOutPathInfo.getAllModule();
            for (int j = 0; j < allModule.size(); j++) {
                ModuleInfo info = allModule.get(j);
                modulePathInfo = new ModulePathInfo(info.getModuleType(), info.getOutPutFullPath());
                if (FileTypeEnums.XML.equals(info.getFileType())){
                    generateInfo.initXmlInfo();
                }
                generateMetaData.addModulePathInfo(tableName, modulePathInfo);
                generateMetaData.addGenerateInfo(tableName, generateInfo);
            }
        }
        generateService.generate(generateMetaData);
    }

}
