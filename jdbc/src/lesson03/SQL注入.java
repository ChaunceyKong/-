package lesson03;

import lesson02.utils.Jdbcutils;

import java.sql.*;

public class SQL注入 {
    public static void main(String[] args) {
        // login("zhangsan","123456");
        login("'' OR '1=1'","'' OR '1=1'");
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            conn= Jdbcutils.getConnection();

            // PreparedStatement 防止SQL注入的本质 把传递进来的参数当作字符
            // 假设其中存在转义字符，比如说' 会被直接转义 即变成\'
            String sql="select * from users where `NAME`=? and password=?";

            st=conn.prepareStatement(sql);

            st.setString(1,username);
            st.setString(2,password);

            rs=st.executeQuery();
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
