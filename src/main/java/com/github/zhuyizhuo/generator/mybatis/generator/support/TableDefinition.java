package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * class: TableDefinition <br>
 * description: TODO <br>
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class TableDefinition {

    /** 数据库名称 */
    private String tableSchema;
    /** 表名 */
    private String tableName;
    /** 表注释 */
    private String tableComment;
    /**  表名转驼峰 首字母大写 */
    private String camelCaseTableName;
    /** 列信息定义 */
    private ColumnDefinition columnDefinition;
    /** 是否有主键 */
    private boolean hasPrimaryKey = true;
    /** 是否单个主键 */
    private boolean singlePrimaryKey = true;


}
