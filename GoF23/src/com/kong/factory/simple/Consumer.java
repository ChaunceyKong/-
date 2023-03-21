package com.kong.factory.simple;

public class Consumer {
    public static void main(String[] args) {
        // 接口，所有的实现类
        // Car car = new WuLing();
        // Car car1 = new Tesla();

        // 2、使用工厂创建
        Car car = CarFactory.getCar("五菱");
        Car car1 = CarFactory.getCar("特斯拉");

        car.name();
        car1.name();
    }
}
