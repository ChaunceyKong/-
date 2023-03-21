package com.kong.builder.demo02;
// 具体的建造者
public class Worker extends Builder{
    private  Product product;

    public Worker() {
        product = new Product();
    }

    @Override
    public Builder builderA(String msg) {
        product.setBuildA(msg);
        return this;
    }

    @Override
    public Builder builderB(String msg) {
        product.setBuildB(msg);
        return this;
    }

    @Override
    public Builder builderC(String msg) {
        product.setBuildC(msg);
        return this;
    }

    @Override
    public Builder builderD(String msg) {
        product.setBuildD(msg);
        return this;
    }

    @Override
    Product getProduct() {
        return product;
    }
}
