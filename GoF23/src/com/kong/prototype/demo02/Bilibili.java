package com.kong.prototype.demo02;

import java.util.Date;

public class Bilibili {
    public static void main(String[] args) throws CloneNotSupportedException {
        //原型对象 v1
        Date date = new Date();
        Video v1 = new Video("狂神说Java", date);
        //v1克隆v2
        Video v2 = (Video) v1.clone();
        System.out.println("v1=>"+v1);
        System.out.println("v2=>"+v2);
        System.out.println("===============");
        date.setTime(213124124);
        System.out.println("v1=>"+v1);
        System.out.println("v2=>"+v2);
    }
}
