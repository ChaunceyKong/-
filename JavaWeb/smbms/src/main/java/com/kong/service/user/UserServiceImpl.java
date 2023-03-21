package com.kong.service.user;

import com.kong.dao.BaseDao;
import com.kong.dao.user.UserDao;
import com.kong.dao.user.UserDaoImpl;
import com.kong.pojo.User;
import org.junit.Test;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService{
    // 业务层都会调用dao层，所以我们要引入dao层
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnect();
            user = userDao.getLoginUser(connection,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        if (user != null && user.getUserPassword().equals(password)) {
            return user;
        }
        else {
            return null;
        }
    }

    public boolean updatePwd(int id, String pwd) {
        Connection connection = null;
        boolean flag = false;
        // 修改密码
        try {
            connection = BaseDao.getConnect();
            if (userDao.updatePwd(connection,id,pwd)>0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    public int getUserCount(String username, int userRole) {

        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnect();
            count = userDao.getUserCount(connection, username, userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }

        return count;
    }

    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("queryUserName--->" + queryUserName);
        System.out.println("queryUserRole--->" + queryUserRole);
        System.out.println("currentPageNo--->" + currentPageNo);

        try {
            connection = BaseDao.getConnect();
            userList = userDao.getUserList(connection, queryUserName, queryUserRole, currentPageNo, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }



}
