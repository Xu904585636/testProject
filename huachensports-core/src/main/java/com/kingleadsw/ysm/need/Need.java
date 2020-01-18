package com.kingleadsw.ysm.need;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/27 14:10
 * @Description:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface Need
{
    int log() default 0;

    boolean token() default false;

    boolean repeat() default false;
}
