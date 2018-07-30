package com.zhuyizhuo.generator.mybatis;

import com.google.common.base.Splitter;
import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.zhuyizhuo.generator.mybatis.service.DbService;
import com.zhuyizhuo.generator.mybatis.utils.SqlSessionUtils;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
