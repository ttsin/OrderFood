package com.xyp.dao.impl;

import com.xyp.dao.OrderItemDao;
import com.xyp.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BasicDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) throws SQLException {
        String sql ="insert into t_order_item(name,price,total_money,count,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getCount(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderDetailsByOrderID(String orderId) throws SQLException {
        String sql="select id,name,price,total_money totalPrice,count,order_id orderId from t_order_item where order_id=?";
        List<OrderItem> orderItems = queryMultiLine(sql, OrderItem.class, orderId);
        return  orderItems;
    }

}
