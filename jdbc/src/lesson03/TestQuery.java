package lesson03;

import lesson02.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TestQuery {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;

        try {
            conn= Jdbcutils.getConnection();
            //区别
            //使用 ? 占位符代替参数
            String sql="select * from users where id=?";

            st=conn.prepareStatement(sql); //预编译SQL，先写sql，然后不执行

            //手动给参数赋值
            st.setInt(1,1);

            // 执行
            rs=st.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,rs);
        }
    }
}
