package com.xyp.web;

import com.xyp.domain.Cart;
import com.xyp.domain.Order;
import com.xyp.domain.OrderItem;
import com.xyp.domain.User;
import com.xyp.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet {
    OrderServiceImpl orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //生成订单,拿到cart，和userId，调用orderService.createOrder(cart,userId)
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        User user = (User)req.getSession().getAttribute("loginUser");
        if(user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        String orderId = orderService.createOrder(cart, user.getId());
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //查看我的订单，调用方法，
        User loginUser = (User)req.getSession().getAttribute("loginUser");
        List<Order> myOrders = orderService.myOrders(loginUser.getId());
        req.setAttribute("myOrders",myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }
    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //点击订单详情，查看这个订单的订单项
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.orderDetails(orderId);
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req,resp);

    }
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //订单收货
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        myOrders(req,resp);

    }

}
