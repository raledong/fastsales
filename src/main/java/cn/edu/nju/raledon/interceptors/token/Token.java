package cn.edu.nju.raledon.interceptors.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by rale on 7/13/17.
 * 令牌
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {


    boolean save() default false;

    boolean remove() default false;

}
