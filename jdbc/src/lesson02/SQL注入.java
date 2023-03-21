package lesson02;

import lesson02.utils.Jdbcutils;
import sun.security.timestamp.TSRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args) {
        // login("kuangshen","123456");
        login("' OR '1=1","' OR '1=1");
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            conn= Jdbcutils.getConnection();
            st=conn.createStatement();

            // SELECT * FROM users WHERE `NAME`='kuangshen' AND `PASSWORD`='123456';
            // SELECT * FROM users WHERE `NAME`='' OR '1=1' AND `PASSWORD`='' OR '1=1';
            String sql="select * from users where `NAME`='"+username+"' AND `PASSWORD`='"+password+"'";

            rs=st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("PASSWORD"));
                System.out.println("====================");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Jdbcutils.release(conn,st,rs);
        }
    }

}
