package com.kong.builder.demo02;
// 抽象的建造者
public abstract class Builder {

    public abstract Builder builderA(String msg);// 汉堡
    public abstract Builder builderB(String msg);// 可乐
    public abstract Builder builderC(String msg);// 薯条
    public abstract Builder builderD(String msg);// 甜点

    abstract Product getProduct();

}
