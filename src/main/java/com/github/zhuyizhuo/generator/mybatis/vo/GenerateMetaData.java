package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  生成文件所需元信息
 */
public class GenerateMetaData {
    /***
     *  表名 -> 该表需生成的所有模块集合
     */
    private Map<String,List<ModulePathInfo>> modulePathInfoMap = new LinkedHashMap<>();
    /***
     *  表名对应生成信息
     */
    private Map<String,GenerateInfo> tableGenerateInfoMap = new ConcurrentHashMap<>();

    public Map<String, List<ModulePathInfo>> getModulePathInfoMap() {
        return modulePathInfoMap;
    }

    public void addModulePathInfo(String tableName, ModulePathInfo modulePathInfo) {
        if (GeneratorStringUtils.isBlank(tableName) || modulePathInfo == null){
            return;
        }
        if (modulePathInfoMap.get(tableName) != null){
            modulePathInfoMap.get(tableName).add(modulePathInfo);
        } else {
            List<ModulePathInfo> modules = new ArrayList<>();
            modules.add(modulePathInfo);
            modulePathInfoMap.put(tableName, modules);
        }
    }

    public void addGenerateInfo(String tableName, GenerateInfo generateInfo){
        if (GeneratorStringUtils.isBlank(tableName) || generateInfo == null){
            return;
        }
        tableGenerateInfoMap.put(tableName, generateInfo);
    }

    /**
     * 根据表名 获取 生成所需信息
     * @param tableName 表名
     * @return 生成所需信息
     */
    public GenerateInfo getGenerateInfoByTableName(String tableName){
        return tableGenerateInfoMap.get(tableName);
    }

    public Map<String, GenerateInfo> getTableGenerateInfoMap() {
        return tableGenerateInfoMap;
    }

}
