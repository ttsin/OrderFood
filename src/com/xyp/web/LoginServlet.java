package com.xyp.web;

import com.xyp.domain.User;
import com.xyp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 1.获取login.html表单中的信息(表单中有密码，所以用post方法发送请求)
 * 2.使用uerService中的login()方法判断是否存在该用户
 *   存在：
 *      跳到login_success.html
 *   不存在：
 *      跳回login.html
 *
 *
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User login = userService.login(new User(null, username, password, null,"user"));
            if(login == null){
                //没有该用户，登录失败
                System.out.println("该用户不存在");
                //请求转发，请求域中的数据login.jsp中也能用
                req.setAttribute("errorMsg","用户名或密码错误");
                req.setAttribute("uesrname",username);
                req.setAttribute("password",password);
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

            }else{
                //存在该用户，登陆成功
                System.out.println("登陆成功");
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);


            }


        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
