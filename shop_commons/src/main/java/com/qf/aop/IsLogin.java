package com.qf.aop;

import java.lang.annotation.*;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/18 9:27
 * @description：自定义注解
 * 4个源注解，最基本的用来标记注解的注解
 * @Documented:当前程序如果生成javadoc文档是否将当前注解添加进去（几乎不用）
 * @Target:当前注解的标记范围(重要)
 * ElementType.ANNOTATION_TYPE:当前注解可以标记其他注解
 * ElementType.CONSTRUCTOR:标记构造方法
 * ElementType.FIELD:标记成员变量
 * ElementType.METHOD：标记方法
 * ElementType.PACKAGE:标记包
 * ElementType.PARAMETER:标记方法参数
 * ==（重要）ElementType.TYPE:标记类、接口、内部类、枚举
 *
 * @Retention:当前注解的有效范围
 * RetentionPolicy.SOURCE:当前注解只在源码中有效，编译后消失
 * RetentionPolicy.CLASS：当前注解只在编译文件中有效，运行后失效
 * ==（重要）RetentionPolicy.RUNTIME:当前注解在运行时有效，如果注解需要配合反射使用，则需要使用这个范围
 *
 * 注解的方法：
 *      声明格式：返回值类型 方法名() [default]方法的默认返回值;
 *   注意：
 *      1.注解中方法的名字如果为value，可以省略方法名（有多个方法不可省略）
 *      2.如果一个方法没有设置默认返回值，则在使用注解时必须设置这个方法的返回值
 *      3.注解的返回值可以是一个数组，在使用时用a={1,2,3},一个可以省略{}
 * @modified By：
 * @version: $version$
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsLogin {

    boolean mustLogin() default false;
}
