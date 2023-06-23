package com.xyp.dao;

import com.xyp.domain.User;

import java.sql.SQLException;

public interface UserDao {
    //测试快捷键：Ctrl+Shift+t

    //注册页面对User表的操作有：
    //1.根据用户名查询用户，如若存在，则用户名冲突，不能用
    //2.保存用户信息
    // 根据登录页面对User表的操作有：
    // 1.根据用户名和密码查询用户信息，返回null,则说明该用户不存在，需要注册

//    1.根据用户名查询用户，如若存在，则用户名冲突，不能用


    public User queryUserByName(String username) throws SQLException;
    public User queryUserByNameAndPassword(String username,String password) throws SQLException;
    public int saveUser(User user) throws SQLException;
}
