package com.github.zhuyizhuo.generator.mybatis.generator.factory;

import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.enums.TemplateTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.service.GenerateService;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl.MybatisPlusGenerateImpl;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl.MysqlGenerateImpl;
import com.github.zhuyizhuo.generator.mybatis.generator.service.template.freemarker.impl.OracleGenerateImpl;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.utils.LogUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成器工厂 <br>
 * time: 2019/5/28
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
public class GenerateServiceFactory {

    private static Map<String,GenerateService> serviceMap = new ConcurrentHashMap<>();

    static{
        serviceMap.put(TemplateTypeEnums.MYSQL.toString(), new MysqlGenerateImpl());
        serviceMap.put(TemplateTypeEnums.ORACLE.toString(), new OracleGenerateImpl());
        serviceMap.put(TemplateTypeEnums.MYBATIS_PLUS.toString(), new MybatisPlusGenerateImpl());
    }

    public static GenerateService getGenerateService() {
        String dbType = ContextHolder.getConfig(ConfigConstants.DB_TYPE).toUpperCase();
        GenerateService generateService = serviceMap.get(dbType);
        if (generateService == null){
            String errorMsg =  ConfigConstants.DB_TYPE + "配置类型不支持,所支持类型请参照 "+ DbTypeEnums.class.getName();
            LogUtils.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return generateService;
    }
}
