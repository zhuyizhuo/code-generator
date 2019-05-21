package com.github.zhuyizhuo.generator.mybatis.constants;

import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;

/**
 * class: MethodEnableConstants <br>
 * description: 方法是否启用常量 <br>
 * time: 2018/8/17 12:09
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class MethodEnableConstants {

    public static final String INSERT_METHOD_ENABLED = MethodEnums.INSERT.getPropertiesEnabledKey();
    public static final String BATCH_INSERT_METHOD_ENABLED = MethodEnums.BATCH_INSERT.getPropertiesEnabledKey();

    public static final String DELETE_METHOD_ENABLED = MethodEnums.DELETE_BY_WHERE.getPropertiesEnabledKey();
    public static final String DELETE_BY_PRIMARY_KEY_METHOD_ENABLED = MethodEnums.DELETE_BY_PRIMARY_KEY.getPropertiesEnabledKey();

    public static final String UPDATE_BY_PRIMARY_KEY_METHOD_ENABLED = MethodEnums.UPDATE_BY_PRIMARY_KEY.getPropertiesEnabledKey();

    public static final String QUERY_METHOD_ENABLED = MethodEnums.QUERY_BY_WHERE.getPropertiesEnabledKey();
    public static final String QUERY_BY_PRIMARY_KEY_ENABLED = MethodEnums.QUERY_BY_PRIMARY_KEY.getPropertiesEnabledKey();
    public static final String COUNT_METHOD_ENABLED = MethodEnums.COUNT_BY_WHERE.getPropertiesEnabledKey();

}
