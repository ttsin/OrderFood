package com.xyp.dao;

import com.xyp.domain.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return 受影响的行数
     * @throws SQLException
     */
    public int saveOrder(Order order) throws SQLException;

    /**
     * 查询我的订单
     * @param userId 用户id
     * @return  一个订单集合
     * @throws SQLException
     */
    public List<Order> queryMyOrder(int userId) throws SQLException;

    /**
     * 查询所有的订单
     * @return 所有的订单集合
     * @throws SQLException
     */
    public List<Order> queryAllOrder() throws SQLException;

    /**
     * 改变订单状态，（初始化是0，未发货，管理员可以更改为发货，用户可以更改为收到货）
     * @param orderId
     * @return
     * @throws SQLException
     */

    public int changeOrderStatus(String orderId,int status) throws SQLException;




}
