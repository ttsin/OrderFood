package com.xyp.test;

import com.xyp.dao.impl.OrderItemDaoImpl;
import com.xyp.domain.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() throws SQLException {

        orderItemDao.saveOrderItem(new OrderItem(null,"桂林米饭", 1,new BigDecimal(8),new BigDecimal(8),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"蛋炒饭", 1,new BigDecimal(8),new BigDecimal(8),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"蛋炒粉", 1,new BigDecimal(8),new BigDecimal(8),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"玉米蒸饺", 1,new BigDecimal(8),new BigDecimal(8),"1234567890"));
    }

    @Test
    public void queryOrderDetailsByOrderID() throws SQLException {
        List<OrderItem> orderItems = orderItemDao.queryOrderDetailsByOrderID("1234567890");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }

    }
}