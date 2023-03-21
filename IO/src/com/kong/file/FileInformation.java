package com.kong.file;

import java.io.File;

public class FileInformation {
    public static void main(String[] args) {

    }

    // 获取文件的信息
    public void info() {
        // 先创建文件对象
        File file = new File("e:\\new1.txt");

        // 调用响应的方法，得到信息
        String fileName = file.getName(); // 文件名
        String absolutePath = file.getAbsolutePath(); // 绝对路径
        String parent = file.getParent(); // 文件父级目录
        long length = file.length(); // 文件大小（字节）
        boolean exists = file.exists(); // 文件是否存在
        boolean file1 = file.isFile(); // 是不是一个文件
        boolean directory = file.isDirectory(); // 是不是一个目录
    }


}
