package com.zhuyizhuo.generator.mybatis;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.zhuyizhuo.generator.mybatis.service.DbService;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.io.Resources;

/**
 * mybatis代码生成器
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/27 20:55
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
