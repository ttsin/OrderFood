package com.xyp.test;

import com.xyp.domain.Cart;
import com.xyp.domain.CartItem;
import com.xyp.domain.Order;
import com.xyp.domain.OrderItem;
import com.xyp.service.OrderService;
import com.xyp.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() throws SQLException {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "桂林米粉", 1, new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(2, "螺蛳粉", 1, new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(3, "蛋炒饭", 1, new BigDecimal(8),new BigDecimal(8)));

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }

    @Test
    public void myOrders() throws SQLException {
        List<Order> orders = orderService.myOrders(1);
        for (Order order : orders) {
            System.out.println(order);

        }
    }

    @Test
    public void orderDetails() throws SQLException {
        List<OrderItem> orderItems = orderService.orderDetails("16870075508231");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);

        }
    }

    @Test
    public void allOrders() throws SQLException {
        List<Order> orders = orderService.allOrders();
        for (Order order : orders) {
            System.out.println(order);

        }

    }

    @Test
    public void sendOrder() throws SQLException {
        orderService.sendOrder("16870075508231");
    }

    @Test
    public void receiveOrder() throws SQLException {
        orderService.receiveOrder("16870075508231");
    }
}