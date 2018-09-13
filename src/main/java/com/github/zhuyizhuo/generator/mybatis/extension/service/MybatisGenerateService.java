package com.github.zhuyizhuo.generator.mybatis.extension.service;

import com.github.zhuyizhuo.generator.mybatis.generator.support.CommentDefinition;
import com.github.zhuyizhuo.generator.mybatis.generator.support.MybatisXmlDefinition;

/**
 * class: MybatisGenerateService <br>
 * description: TODO <br>
 * time: 2018/9/12
 *
 * @author yizhuo <br>
 * @since #since#
 */
public interface MybatisGenerateService {

    /**
     * 添加类注释
     */
    void addXmlHeader(MybatisXmlDefinition mybatisXmlDefinition);
}
