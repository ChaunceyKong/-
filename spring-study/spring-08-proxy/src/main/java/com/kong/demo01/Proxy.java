package com.kong.demo01;

public class Proxy implements Rent{

    private Host host;

    public Proxy() {
    }
    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        sign();
        fee();
    }

    //看房
    public void seeHouse() {
        System.out.println("中介带你去看房");
    }

    //收费
    public void fee() {
        System.out.println("收中介费");
    }

    //签合同
    public void sign() {
        System.out.println("签合同");
    }
}
