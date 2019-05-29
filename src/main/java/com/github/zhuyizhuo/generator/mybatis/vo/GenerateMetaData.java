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
    private Map<String,List<TemplateGenerateInfo>> tableInfosMap = new LinkedHashMap<>();
    /**
     *  所有表的所有模块集合
     */
    private List<TemplateGenerateInfo> tableInfos = new ArrayList<>();

    public Map<String, List<TemplateGenerateInfo>> getTableInfosMap() {
        return tableInfosMap;
    }

    public void addGenerateInfo(String tableName, TemplateGenerateInfo generateInfo) {
        if (GeneratorStringUtils.isBlank(tableName) || generateInfo == null){
            return;
        }
        if (tableInfosMap.get(tableName) != null){
            tableInfosMap.get(tableName).add(generateInfo);
        } else {
            List<TemplateGenerateInfo> generateInfos = new ArrayList<>();
            generateInfos.add(generateInfo);
            tableInfosMap.put(tableName,generateInfos);
        }
        tableInfos.add(generateInfo);
    }

    public int getCount() {
        return tableInfos.size();
    }
}
