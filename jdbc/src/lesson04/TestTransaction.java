package lesson04;

import lesson02.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn= Jdbcutils.getConnection();
            //关闭数据库的子动提交,自动会开启事务
            conn.setAutoCommit(false); // 开启事务

            String sql1="update account set money=money-100 where `NAME`='A'";
            st=conn.prepareStatement(sql1);
            st.executeUpdate();

            String sql2="update account set money=money+100 where `NAME`='B'";
            st=conn.prepareStatement(sql2);
            st.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("成功！");

        } catch (SQLException e) {
            try {
                conn.rollback(); //如果失败就回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,rs);
        }
    }
}
