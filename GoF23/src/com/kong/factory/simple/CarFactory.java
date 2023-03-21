package com.kong.factory.simple;

//静态工厂模式（简单工厂模式）
//增加一个新的产品，如果不修改代码，做不到！违反开闭原则
public class CarFactory {
    public static Car getCar(String car) {
        if (car.equals("五菱")) {
            return new WuLing();
        } else if (car.equals("特斯拉")) {
            return new Tesla();
        } else {
            return null;
        }
    }
}
