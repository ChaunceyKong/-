package com.kong.file;

import java.io.File;

public class Directory_ {
    public static void main(String[] args) {

    }

    // 判断 e:\\new1.txt 文件是否存在，如果存在就删除
    public void m1() {
        String filePath = "e:\\new1.txt";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }

        } else {
            System.out.println("该文件不存在！");
        }
    }

    // 判断 e:\\demo 目录是否存在，如果存在就删除
    public void m2() {
        String filePath = "e:\\demo";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }

        } else {
            System.out.println("该目录不存在！");
        }
    }

    // 判断 e:\\demo\\a\\b\\c 目录是否存在，如果存在就提示已经存在，否则就创建
    public void m3() {
        String directoryPath = "e:\\demo\\a\\b\\c";
        File file = new File(directoryPath);
        if (file.exists()) {
            System.out.println("目录存在！");
        } else {
            if (file.mkdirs()) {
                System.out.println("创建成功！");
            } else {
                System.out.println("创建失败！");
            }
        }
    }
}
