package lesson02;

import lesson02.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testDelete {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn= Jdbcutils.getConnection(); //获取数据库连接
            st = conn.createStatement();

            String sql = "DELETE FROM users WHERE id='4'";

            int i=st.executeUpdate(sql);
            if (i>0){
                System.out.println("删除成功！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,rs);
        }

    }
}
