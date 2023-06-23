package com.xyp.dao.impl;

import com.xyp.dao.OrderDao;

import com.xyp.domain.Order;
import com.xyp.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BasicDao<Order> implements OrderDao{

    @Override
    public int saveOrder(Order order) throws SQLException {
        String sql ="insert into t_order(order_id,create_time,total_money,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryMyOrder(int userId) throws SQLException {
        String sql ="select order_id orderId,create_time createTime,total_money price,status,user_id userId from t_order where user_id=?";
        List<Order> orders = queryMultiLine(sql, Order.class, userId);
        return orders;
    }

    @Override
    public List<Order> queryAllOrder() throws SQLException {
        String sql ="select order_id orderId,create_time createTime,total_money price,status,user_id userId from t_order ";
        List<Order> orders = queryMultiLine(sql, Order.class);
        return orders;
    }

    @Override
    public int changeOrderStatus(String orderId,int status) throws SQLException {
        String sql="update t_order set status=? where order_id=?";
        return update(sql, status, orderId);
    }
}
