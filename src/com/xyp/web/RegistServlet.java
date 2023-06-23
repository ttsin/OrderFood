package com.xyp.web;
import com.xyp.domain.User;
import com.xyp.service.impl.UserServiceImpl;
import jdk.nashorn.internal.ir.CallNode;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * 用于接受from表单提交过来的请求，判断其中的数据，数据无误，跳转到注册成功页面regist_success.html
 *
 */

public class RegistServlet extends HttpServlet {
    /**
     * 1.获取请求的参数（其中有密码，用doPost()方法）
     * 2.检查 验证码是否正确
     * 正确
     *     3.检查用户名是否可用
     *          可用
     *              调用Service保存到数据库
     *              跳到注册成功页面 regist_success.jsp
     *          不可用
     *              跳回注册页面
     *
     * 不正确
     *    跳回注册页面
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
//        1.获取请求的参数（其中有密码，用doPost()方法）
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        2.检查 验证码是否正确 先写死，后面再改，验证码是abcde
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("email",email);
        if("abcde".equalsIgnoreCase(code)){
            //验证码正确
            //3. 用户名是否已存在
            try {
                if(userService.existUsername(username)){
                   //返回true,则存在
                    System.out.println("用户名["+username+"]已存在");
                    req.setAttribute("errorMsg","用户名已存在");
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);


                }else{
                    //返回flase ,表示用户名不存在，可以使用
                    //4.保存用户信息
                    userService.registerUser(new User(null,username,password,email,"user"));
                    //跳转到用户成功注册界面
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }
            } catch (SQLException e) {
               throw new RuntimeException(e);
            }

        }else{
            //不相等
            req.setAttribute("errorMsg","验证码错误");
            System.out.println("验证码["+code+"]不正确");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }



    }


}
