package com.github.zhuyizhuo.generator.mybatis.extension.service;

import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;

/**
 * class: GeneratorService <br>
 * description: 生成器service <br>
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public interface GeneratorService {

    /**
     * 生成文件
     * @param generateInfo 所有生成文件所需数据
     */
    void generate(GenerateInfo generateInfo);

}
