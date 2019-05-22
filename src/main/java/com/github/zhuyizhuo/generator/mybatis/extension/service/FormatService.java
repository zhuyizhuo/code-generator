package com.github.zhuyizhuo.generator.mybatis.extension.service;

/**
 * class: FormatService <br>
 * description: 名称格式化 <br>
 * time: 2018/8/16 19:29
 *
 * @author yizhuo <br>
 * @Since 1.0
 * @version 1.4.0
 */
public interface FormatService {

    /**
     * name 格式化
     * @param name 数据库表名称的大写
     * @return 格式化后的名称
     */
    String format(String name);

}
