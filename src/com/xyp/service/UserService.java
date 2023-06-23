package com.xyp.service;

import com.xyp.domain.User;

import java.sql.SQLException;

public interface UserService {
    //一个业务一个方法
    //对user表操作，其中注册是一个业务，登录是一个业务，根据username判断用户是否存在也是一个业务
    //所以，三个业务，三个方法


    /**
     * 注册用户
     * @param user
     */

    public void registerUser(User user) throws SQLException;

    /**
     * 用户登录
     * @param user
     * @return 返回值是null，登录失败
     */
    public User login(User user) throws SQLException;

    /**
     * 检查用户名是否存在
     * @param username
     * @return 返回true,说明用户名存在，flase说明用户名可用
     */

    public boolean existUsername(String username) throws SQLException;

}
