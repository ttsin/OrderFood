package com.xyp.test;

import com.xyp.domain.User;
import com.xyp.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void registerUser() throws SQLException {
        userService.registerUser(new User(null,"xixi","111222","xixi@qq.com","admin"));

    }

    @Test
    public void login() throws SQLException {
        if(userService.login(new User(null,"ttsin","111222",null,"admin"))==null){
            System.out.println("登录失败");
        }else{
            System.out.println("登陆成功");
        }
    }

    @Test
    public void existUsername() throws SQLException {
        if(userService.existUsername("ttsin")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}