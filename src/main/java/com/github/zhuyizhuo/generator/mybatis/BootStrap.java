package com.github.zhuyizhuo.generator.mybatis;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.io.Resources;

/**
 * mybatis代码生成器
 * @author yizhuo 2018/7/27 20:55
 * @version 1.0
 */
public class BootStrap {

    public static void main(String[] args) throws Exception {
        PropertiesUtils.loadProperties(Resources.getResourceAsStream(ConfigConstants.PROPERTIES_FILE_PATH));
        generate();
    }

    public static void generate(){
        DbService service =  DbServiceFactory.getDbService();
        Generator.printAll(service.getTableColumns());
    }

}
