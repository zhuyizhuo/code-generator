package com.github.zhuyizhuo.generator.exception;

/**
 * 自定义生成器异常
 *
 * @author zhuo
 * @since 1.4.3
 */
public class GeneratorException extends RuntimeException{

    /** 异常信息 */
    private String message;

    public GeneratorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
