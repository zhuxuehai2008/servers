package com.zxh.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * 自定义接口，表名
 * @author sunyujia
 * 2014-06-08
 * @param <T>
 * @param <PK>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {

	public String value() default "";
	
}
