package com.kong.file;

import java.io.File;
import java.io.IOException;

/**
 * 演示创建文件
 */
public class FileCreate {
    public static void main(String[] args) {

    }

    // 方式一
    public void create01() {
        String filePath = "e:\\new1.txt";
        File file = new File(filePath);

        try {
            file.createNewFile();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方式二
    // e:\new1.txt
    public void create02() {
        File parentFile = new File("e:\\");
        String fileName = "new2.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方式三
    public void create03() {
        String parentPath = "e:\\";
        String fileName = "new3.txt";
        File file = new File(parentPath, fileName);
        try {
            file.createNewFile();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
