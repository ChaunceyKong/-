package lesson02;

import lesson02.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testUpdate {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn= Jdbcutils.getConnection(); //获取数据库连接
            st = conn.createStatement();

            String sql = "UPDATE users SET `NAME`='kuangshen',`email`='2222@qq.com' WHERE id='1'";

            int i=st.executeUpdate(sql);
            if (i>0){
                System.out.println("更新成功！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,rs);
        }

    }
}
