package com.xyp.filter;

import com.xyp.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ManagerFilter 类过滤 对manger目录下的文件（后台管理）的请求，要求必须登录
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断session域中的loginUser是否为空，为空就登录
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);

        }

    }

    @Override
    public void destroy() {

    }
}
