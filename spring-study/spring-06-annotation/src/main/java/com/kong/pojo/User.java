package com.kong.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 等价于 <bean id="user" class="com.kong.pojo.User"/>
// @Component 组件
@Component
@Scope("singleton")
public class User {

    public String name;

    //相当于 <property name="name" value="kong"/>
    @Value("kong")
    public void setName(String name) {
        this.name = name;
    }
}
