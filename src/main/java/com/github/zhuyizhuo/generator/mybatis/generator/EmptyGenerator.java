package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.utils.LogUtils;

/**
 * 空生成器实例 当配置有误时返回
 *
 * @author zhuo
 * @since 1.4.3
 */
public class EmptyGenerator implements Generator {
    @Override
    public void generate() {
        LogUtils.printInfo("请检查配置是否正确, 文档地址: " +
                "http://zhuyizhuo.online/code-generator-doc/guide/quickstart.html \n");
    }
}
