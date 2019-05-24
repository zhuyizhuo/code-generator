package com.github.zhuyizhuo.generator.mybatis.generator.support.mybatis;

import com.github.zhuyizhuo.generator.mybatis.generator.support.MethodDefinition;

/**
 * class: MybatisMethodDefinition <br>
 * description: TODO <br>
 * time: 2018/9/18
 *
 * @author yizhuo <br>
 * @since 1.3.2
 */
public class MybatisMethodDefinition extends MethodDefinition {

    private String id;
    private String parameterType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
}
