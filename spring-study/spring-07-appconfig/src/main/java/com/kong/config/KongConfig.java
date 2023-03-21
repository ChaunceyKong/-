package com.kong.config;

import com.kong.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //这个也会被Spring注册到容器中，因为它本来就是一个@Component
//@Configuration 代表这时一个配置类，就和我们之前beans.xml一样
@Import(KongConfig2.class)

public class KongConfig {

    // 注册一个bean，就相当于我们之前写的一个bean标签
    // 这个方法的名字，就相当于bean标签的id属性
    // 这个方法的返回值，就相当于bean标签的class属性
    @Bean
    public User getUser(){
        return new User(); //返回要注入Spring的对象
    }
}
