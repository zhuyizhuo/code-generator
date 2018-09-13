package com.github.zhuyizhuo.generator.mybatis.extension.service;

import com.github.zhuyizhuo.generator.mybatis.generator.support.CommentDefinition;

/**
 * class: CommentService <br>
 * description: TODO <br>
 * time: 2018/9/11 20:09
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public interface CommentService {

    /**
     * 添加类注释
     */
    void addClassComment(CommentDefinition commentDefinition);

}
