package com.xyp.web;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决响应的中文乱码的问题
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        try {
            // action = login ,方法也是login()
            //根据action的值，反射到上面的方法,在调用方法，避免了大量的else if判断
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp); //调用方法

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
    }


//        if("regist".equals(action)){
//            regist(req, resp);
//        }else if("login".equals(action)){
//            login(req, resp);
//
//        }
    }

}
