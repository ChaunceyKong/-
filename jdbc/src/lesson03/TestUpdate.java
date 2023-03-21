package lesson03;

import lesson02.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement st=null;

        try {
            conn= Jdbcutils.getConnection();
            //区别
            //使用 ? 占位符代替参数
            String sql="update users set `NAME`=? where id=?;";

            st=conn.prepareStatement(sql); //预编译SQL，先写sql，然后不执行

            //手动给参数赋值

            st.setString(1,"ppp");
            st.setInt(2,1);

            // 执行
            int i=st.executeUpdate();
            if (i>0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,null);
        }
    }
}
