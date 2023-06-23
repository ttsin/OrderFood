package com.xyp.test;

import com.xyp.dao.OrderDao;
import com.xyp.dao.impl.OrderDaoImpl;
import com.xyp.domain.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() throws SQLException {

        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0, 1));
        orderDao.saveOrder(new Order("1234567892",new Date(),new BigDecimal(100),0, 2));

    }

    @Test
    public void queryMyOrder() throws SQLException {
        List<Order> orders = orderDao.queryMyOrder(1);
        for (Order order : orders) {
            System.out.println(order);

        }

    }

    @Test
    public void queryAllOrder() throws SQLException {
        List<Order> orders = orderDao.queryAllOrder();
        for (Order order : orders) {
            System.out.println(order);

        }
    }

    @Test
    public void changeOrderStatus() throws SQLException {
        orderDao.changeOrderStatus("1234567890",1);
        orderDao.changeOrderStatus("1234567890",2);
    }
}