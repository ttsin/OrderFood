package com.xyp.service.impl;

import com.xyp.dao.impl.FoodDaoImpl;
import com.xyp.dao.impl.OrderDaoImpl;
import com.xyp.dao.impl.OrderItemDaoImpl;
import com.xyp.domain.*;
import com.xyp.service.OrderService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    FoodDaoImpl foodDao = new FoodDaoImpl();


    @Override
    public String createOrder(Cart cart, int userId) throws SQLException {
        //三大步：保存订单，保存订单项，修改商品销量，清空购物车
        //创建一个订单，只需要new一个订单对象，但是，orderID不确定，orderID必须是唯一的
        //用时间戳+userId
        //要保存订单，也要把购物车中的商品保存到t_order_item表中，同时还要更改这个商品的销量
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        //遍历购物车中的商品项，把商品new成订单项，并保存到数据库中t_order_item中
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //遍历购物车中的每一个商品项,entry 是个键值对
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            //通过foodId找到food对象，更改food对象的销量，在更新数据库中的food数据
            Food food = foodDao.queryFoodById(cartItem.getId());
            food.setSales(food.getSales() + cartItem.getCount());
            foodDao.updateFood(food);

        }
        cart.clean();//清空购物车
        return orderId;

    }

    @Override
    public List<Order> myOrders(int userId) throws SQLException {
        //查询我的订单
        List<Order> orders = orderDao.queryMyOrder(userId);
        return orders;
    }

    @Override
    public List<OrderItem> orderDetails(String orderId) throws SQLException {
        //查看订单详细，
        List<OrderItem> orderItems = orderItemDao.queryOrderDetailsByOrderID(orderId);

        return orderItems;
    }

    @Override
    public List<Order> allOrders() throws SQLException {
        //查询所有的订单
        List<Order> orders = orderDao.queryAllOrder();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) throws SQLException {
        //发货,status=1
        orderDao.changeOrderStatus(orderId, 1);

    }

    @Override
    public void receiveOrder(String orderId) throws SQLException {
        //收货，status=2
        orderDao.changeOrderStatus(orderId, 2);

    }
}
