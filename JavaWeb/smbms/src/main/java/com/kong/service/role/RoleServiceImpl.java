package com.kong.service.role;

import com.kong.dao.BaseDao;
import com.kong.dao.role.RoleDao;
import com.kong.dao.role.RoleDaoImpl;
import com.kong.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService{

    //引入Dao
    private RoleDao roleDao;
    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    public List<Role> getRoleList() {
        Connection connect = null;
        List<Role> roleList = null;
        try {
            connect = BaseDao.getConnect();
            roleList = roleDao.getRoleList(connect);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connect , null, null);
        }
        return roleList;
    }

}
