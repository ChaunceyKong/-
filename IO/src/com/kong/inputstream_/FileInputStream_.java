package com.kong.inputstream_;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStream_ {
    public static void main(String[] args) {

    }

    /**
     * 单个字节的读取，效率低
     * -> 使用 read(byte[] b) 优化
     */
    public void readFile01() {
        String filePath = "e:\\hello.txt";
        int readData = 0;
        FileInputStream fileInputStream = null;
        try {
            // 创建 FileInputStream 对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            // 从该输入流读取一个字节数据
            // 如果返回 -1，表示读取完毕
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData); // 转成char显示
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用 read(byte[] b) 优化
     */
    public void readFile02() {
        String filePath = "e:\\hello.txt";
        // 字节数组
        byte[] buf = new byte[8]; // 一次读取8个字节
        int readLen = 0;
        FileInputStream fileInputStream = null;
        try {
            // 创建 FileInputStream 对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            // 从该输入流读取一个字节数据
            // 如果返回 -1，表示读取完毕
            // 如果读取正常，返回实际读取的字节数
            while ((readLen = fileInputStream.read(buf)) != -1) {
                System.out.print(new String(buf, 0, readLen)); // 转成char显示
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
