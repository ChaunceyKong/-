package lesson05;

import lesson02.utils.Jdbcutils;
import lesson05.utils.Jdbcutils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestDBPC {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement st=null;

        try {
            conn= Jdbcutils_DBCP.getConnection();
            //区别
            //使用 ? 占位符代替参数
            String sql="insert into users(`id`,`NAME`,`PASSWORD`,`email`,`birthday`) values(?,?,?,?,?)";

            st=conn.prepareStatement(sql); //预编译SQL，先写sql，然后不执行

            //手动给参数赋值
            st.setInt(1,4);
            st.setString(2,"kkk");
            st.setString(3,"123456");
            st.setString(4,"1111@qq.com");
            // 注意点： sql.Date    数据库    java.sql.Date()
            //         util.Date   java    new Date().getTime() 获得时间戳
            st.setDate(5,new java.sql.Date(new Date().getTime()));

            // 执行
            int i=st.executeUpdate();
            if (i>0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Jdbcutils_DBCP.release(conn,st,null);
        }
    }
}
