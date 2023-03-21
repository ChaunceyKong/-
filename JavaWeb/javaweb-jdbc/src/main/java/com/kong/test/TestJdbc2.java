package com.kong.test;

import java.sql.*;

public class TestJdbc2 {
    public static void main(String[] args) throws Exception {
        //配置信息
        // useUnicode=true&characterEncoding=utf8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";

        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.编写SQL
        String sql = "insert into users(id,name,password,email,birthday) values (?,?,?,?,?)";

        //4.预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,4); //给第一个占位符？ 的值赋为1
        preparedStatement.setString(2,"孔祥宇"); //给第二个占位符？ 的值赋为孔祥宇
        preparedStatement.setString(3,"123456"); //给第三个占位符？ 的值赋为123456
        preparedStatement.setString(4,"1234@qq.com"); //给第四个占位符？ 的值赋为1234@qq.com
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime())); //给第五个占位符？ 的值赋为1

        //5.执行sql
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("插入成功！");
        }

        //6.关闭连接释放资源，先开后关
        preparedStatement.close();
        connection.close();
    }
}
