package com.zhuyizhuo.generator.mybatis;

import com.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.zhuyizhuo.generator.mybatis.service.DbService;

/**
 * mybatis代码生成器
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/27 20:55
 */
public class BootStrap {

    public static void main(String[] args) throws Exception {
        DbService service =  DbServiceFactory.getDbService();
        Generator.printAll(service.getTableColumns());
    }

}
