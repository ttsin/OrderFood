package com.xyp.dao;

import com.xyp.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem) throws SQLException;
    public List<OrderItem> queryOrderDetailsByOrderID(String  orderId)throws SQLException;

}
