package com.kong.demo01;

public class Client {
    public static void main(String[] args) {
        //房东要租房子
        Host host = new Host();
        //代理，中介帮房东租房子，代理角色一般会有附属操作
        Proxy proxy = new Proxy(host);
        //你去找中介租房
        proxy.rent();
    }
}
