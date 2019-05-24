package com.github.zhuyizhuo.generator.mybatis.dto;

/**
 * class: ParamDescription <br>
 * description: 方法参数信息 <br>
 * time: 2019/5/24
 *
 * @author yizhuo <br>
 * @since #since#
 */
public class ParamDescription {
    /** 注释 */
    private String comment;

    public ParamDescription(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
