package com.xyp.test;

import com.xyp.dao.impl.UserDaoImpl;
import com.xyp.domain.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDaoImpl userDao = new UserDaoImpl();


    @Test
    public void queryUserByName() throws SQLException {
        if(userDao.queryUserByName("ttsin")==null){
            System.out.println("用户名可以使用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByNameAndPassword() throws SQLException {
        if(userDao.queryUserByNameAndPassword("ttsin","111222")==null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("登陆成功");
        }
    }

    @Test
    public void saveUser() throws SQLException {
        int rows= userDao.saveUser(new User(null, "ttsin2", "111222", "8212400@qq.com","user"));
        if(rows > 0){
            System.out.println("执行成功");
        }
    }
}