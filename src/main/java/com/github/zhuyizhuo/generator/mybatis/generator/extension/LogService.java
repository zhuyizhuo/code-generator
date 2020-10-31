package com.github.zhuyizhuo.generator.mybatis.generator.extension;

import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;

/**
 * 日志输出, 打印生成模板的元数据 <br>
 * time: 2019/6/6
 *
 * @author zhuo <br>
 */
public interface LogService {

    /**
     *  输出生成信息
     * @param generateInfo 生成器处理后的生成数据使用的元数据
     */
    void logGenerateInfo(GenerateInfo generateInfo);

}
