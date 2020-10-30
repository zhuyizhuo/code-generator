package com.github.zhuyizhuo.generator.mybatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 此注解用来标识约定类
 * 用此注解配置的类 将会对类中的属性注入配置文件中的对应配置
 * 如果类中属性没有配置则赋值默认值
 *
 * @author zhuo <br>
 * @since 1.4.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CoventionClass {
}