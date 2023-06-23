package com.xyp.service.impl;

import com.xyp.dao.impl.UserDaoImpl;
import com.xyp.domain.User;
import com.xyp.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) throws SQLException {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) throws SQLException {
        User user1 = userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
        return user1;

    }

    @Override
    public boolean existUsername(String username) throws SQLException {
        User user = userDao.queryUserByName(username);
        if (user != null){
            return true; //存在
        }else{
            return false;//不存在
        }

    }
}
