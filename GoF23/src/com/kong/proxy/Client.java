package com.kong.proxy;

public class Client {
    public static void main(String[] args) {
        //目标类
        Host host = new Host();
        //代理类，现在没有
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象
        pih.setRent(host);
        //动态生成的代理类
        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }
}
