package com.github.zhuyizhuo.generator.mybatis.generator.extension;

/**
 * class: LogService <br>
 * description: 扩展日志输出 <br>
 * time: 2019/6/6
 *
 * @author yizhuo <br>
 */
public interface LogService {

    /**
     *  输出生成信息
     * @param obj
     */
    void logGenerateInfo(Object obj);

}
