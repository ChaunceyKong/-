package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
public class Test03 {
    //注解可以显式赋值，如果没有默认值，我们就必须给注解赋值
    @MyAnnotation2(age=1,name="kkk")
    public void test(){

    }

    //只有参数是value时，参数名字可以省略
    @MyAnnotation3("xxx")
    public void test2(){}
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //注解的参数：参数类型 + 参数名();
    //注意；这并不是方法
    String name() default "";
    int age();
    int id() default -1;

    String[] schools() default {"清华大学","北京大学"};
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}