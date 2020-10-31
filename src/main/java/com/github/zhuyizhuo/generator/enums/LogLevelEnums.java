package com.github.zhuyizhuo.generator.enums;

/**
 * 日志级别枚举
 */
public enum LogLevelEnums {
    /** 打印堆栈 */
    DEBUG(1),
    /** 普通日志 */
    INFO(2),
    ;

    private int level;

    LogLevelEnums(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
