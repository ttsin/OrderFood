package com.xyp.web;

import com.google.gson.Gson;
import com.xyp.domain.User;
import com.xyp.service.impl.UserServiceImpl;
import com.xyp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet{
    private UserServiceImpl userService = new UserServiceImpl();
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、销毁 Session 中用户登录的信息（或者销毁 Session）
        // 2、重定向到首页（或登录页面）。
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());

    }
        protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        try {
            User login = userService.login(WebUtils.copyParamToBean(req.getParameterMap(), new User()));
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
                req.getSession().setAttribute("loginUser",login);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

            }


        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



//        1.获取请求的参数（其中有密码，用doPost()方法）
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
    //内容回显
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.setAttribute("email",email);
        //  2.检查 验证码是否正确 先写死，后面再改，验证码是abcde

        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if(token != null && token.equalsIgnoreCase(code)){
            try {
                //验证码正确
                //3. 用户名是否已存在
                if(userService.existUsername(username)){
                    //返回true,则存在
                    System.out.println("用户名["+username+"]已存在");
                    req.setAttribute("errorMsg","用户名已存在");
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);


                }else{
                    //返回flase ,表示用户名不存在，可以使用
                    //4.保存用户信息
//                    userService.registerUser(new User(null,username,password,email));
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

    /**
     * 判断用户名是否存在，把结果返回给请求页面（响应），用到了AJAX技术（优点异步更新）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);

        //把结果封装成一个Map对象
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        //把它转成JSON对象，在返回给客户端
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);



    }


}
