package lesson02;

import lesson02.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testQuery {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;

        try {
            conn= Jdbcutils.getConnection();
            st=conn.createStatement();

            //SQL
            String sql="SELECT * FROM users WHERE id=1";

            rs=st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,rs);
        }
    }
}
