package com.xyp.dao.impl;

import com.xyp.dao.UserDao;
import com.xyp.domain.User;

import java.sql.SQLException;

public class UserDaoImpl extends BasicDao<User> implements UserDao {
    @Override
    public User queryUserByName(String username) throws SQLException {
        String sql ="select id,username,password,email,role from t_user where username=?";
        User user = querySingle(sql, User.class, username);
        return user;
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) throws SQLException {
        String sql ="select id,username,password,email,role from t_user where username=? and password=?";
        User user = querySingle(sql, User.class, username,password);
        return user;
    }


    @Override
    public int saveUser(User user) throws SQLException {
        String sql ="insert into t_user(id,username,password,email,role) values(null,?,?,?,?)";
        int updateRows = update(sql, user.getUsername(), user.getPassword(), user.getEmail(),user.getRole());
        return updateRows;
    }
}
