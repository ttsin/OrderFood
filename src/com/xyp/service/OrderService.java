package com.xyp.service;

import com.xyp.domain.Cart;
import com.xyp.domain.Order;
import com.xyp.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return 返回orderID
     * @throws SQLException
     */
    public String createOrder(Cart cart,int userId) throws SQLException;

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    public List<Order> myOrders(int userId) throws SQLException;

    /**
     * 查看订单详细商品
     * @param orderId
     * @return
     */
    public List<OrderItem> orderDetails(String  orderId) throws SQLException;

    /**
     * 查看所有的订单
     * @return
     */
    public List<Order> allOrders() throws SQLException;

    /**
     * 发货
     * @param orderId
     */
    public void sendOrder(String orderId) throws SQLException;

    /**
     * 签收货物
     * @param orderId
     */
    public void receiveOrder(String orderId) throws SQLException;

}
