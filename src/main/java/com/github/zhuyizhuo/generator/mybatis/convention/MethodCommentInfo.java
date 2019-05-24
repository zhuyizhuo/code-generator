package com.github.zhuyizhuo.generator.mybatis.convention;

import com.github.zhuyizhuo.generator.mybatis.annotation.CoventionClass;

/**
 * class: MethodCommentInfo <br>
 * description: 方法描述 <br>
 * time: 2018/8/17 12:47
 *
 * @author yizhuo <br>
 * @version 1.3.0
 */
@CoventionClass
public class MethodCommentInfo {

    /** 参数对象描述 */
    private String paramsDescription = "传入参数";

    public String getParamsDescription() {
        return paramsDescription;
    }

    public void setParamsDescription(String paramsDescription) {
        this.paramsDescription = paramsDescription;
    }
}
