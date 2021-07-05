package com.github.zhuyizhuo.generator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解用来标识资源文件
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resource {

    /**
     * 标识资源文件配置
     * @return 资源文件的配置 key
     */
    String value();

}