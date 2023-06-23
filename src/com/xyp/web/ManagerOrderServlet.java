package com.xyp.web;

import com.xyp.domain.Order;
import com.xyp.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManagerOrderServlet extends BaseServlet{
    OrderServiceImpl orderService = new OrderServiceImpl();

    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //查看所有的订单
        List<Order> allOrders = orderService.allOrders();
        req.setAttribute("allOrders",allOrders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);


    }
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //订单发货
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        allOrders(req, resp);

    }
}
