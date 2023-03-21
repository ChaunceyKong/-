package com.kong.inputstream_;

import java.io.*;

public class ObjectInputStream_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 指定反序列化的文件
        String filePath = "e:\\data.txt";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

        // 读取
        // 1.读取(反序列化)的顺序，需要和保存数据(序列化)的顺序一致
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        Object dog = ois.readObject();
        System.out.println(dog);

        // 关闭流
        ois.close();
    }
}
