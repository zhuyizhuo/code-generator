package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * class: ColumnDefinition <br>
 * description: TODO <br>
 * time: 2018/9/12
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class ColumnDefinition {
    /** 表所有字段 */
    private List<JavaColumnInfo> columnLists = Lists.newArrayList();
    /** 主键字段 */
    private List<JavaColumnInfo> primaryKeyColumns = Lists.newArrayList();
    /** 非主键字段 */
    private List<JavaColumnInfo> otherColumns = Lists.newArrayList();

}
