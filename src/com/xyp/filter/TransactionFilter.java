package com.xyp.filter;

import com.xyp.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtilsByDruid.commitAndClose();//提交事务
        } catch (Exception e) {
            JDBCUtilsByDruid.rollbackAndClose();//回滚事务
            e.printStackTrace();
             throw new RuntimeException(e);//把错误抛给Tomcat服务器，以便于error页面的跳转
        }

    }

    @Override
    public void destroy() {

    }
}
