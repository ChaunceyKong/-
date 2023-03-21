package com.kong.builder.demo01;

public class Test {
    public static void main(String[] args) {
        // 指挥
        Director director = new Director();
        // 指挥 具体的工人 完成产品
        Product build = director.build(new Worker());
        System.out.println(build.toString());
    }
}
