package com.xyp.utils;
// 3.Apace-JBUtils  工具类jar包（ crud操作 ）

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBCUtilsByDruid 工具类，获取数据库连接和关闭连接的方法
 */
public class JDBCUtilsByDruid {
     //导入jar包
    // 1. mysql驱动（连接）
    // 2. 德鲁伊 druid 数据库连接池
    private static DataSource dataSource;
    private static ThreadLocal<Connection> connectionThreadLocal= new ThreadLocal<Connection>();


    static{

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("E:\\develop_tools\\java\\ideaCode\\OrderFood2\\src\\druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    //得到连接
    public static Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
        Connection connection=connectionThreadLocal.get();
        if(connection == null){//为空，当前线程首次连接数据库
            connection=dataSource.getConnection();//得到连接
            connectionThreadLocal.set(connection);//连接与当前线程绑定（存起来）
            connection.setAutoCommit(false);//设置手动管理事务
        }
        return connectionThreadLocal.get();
    }

    /**
     * 提交事务，并关闭连接
     */
    public static void commitAndClose(){
        Connection connection = connectionThreadLocal.get();
        if(connection != null){//非空，说明之前使用过
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错（Tomcat 服务器底层使用课线程池技术）
        connectionThreadLocal.remove();
    }
    /**
     * 回滚事务，并关闭连接
     */
    public static void rollbackAndClose(){
        Connection connection = connectionThreadLocal.get();
        if(connection != null){//非空，说明之前使用过
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错（Tomcat 服务器底层使用课线程池技术）
        connectionThreadLocal.remove();
    }

//    /**
//     * 关闭ResultSet结果集，关闭Statement预处理对象,关闭连接，放回数据库连接池
//     * @param resultSet
//     * @param statement
//     * @param connection
//     * @throws SQLException
//     */
//    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
//        if(resultSet != null){
//            resultSet.close();
//        }
//        if(statement != null){
//            statement.close();
//        }
//        if(connection != null){
//            connection.close();
//        }
//    }



}
