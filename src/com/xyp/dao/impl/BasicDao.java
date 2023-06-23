package com.xyp.dao.impl;

import com.xyp.utils.JDBCUtilsByDruid;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * BasicDao 对表进行crud操作，被XxxDao类继承
 */
public class BasicDao<T> {
    //查询多行多列
    //查询一行数据
    //查询单行单列（一个值）
    //dml操作（增删改）
    private QueryRunner queryRunner = new QueryRunner();

    //查询多行多列
    public List<T> queryMultiLine(String sql, Class<T> clazz, Object... parameters) throws SQLException {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            List<T> list = queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsByDruid.close(null, null, connection);
//        }
    }
    //查询一行数据
    public T querySingle(String sql,Class<T> clazz,Object... parameters) throws SQLException {
        Connection connection=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            T t = queryRunner.query(connection, sql, new BeanHandler<T>(clazz), parameters);
            return t;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsByDruid.close(null,null,connection);
//        }

    }
    //查询一个数
    public Object queryOnlyValue(String sql,Object... parameters) throws SQLException {
        Connection connection=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Object o = queryRunner.query(connection, sql, new ScalarHandler<>(), parameters);
            return o;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsByDruid.close(null,null,connection);
//        }

    }
    //dml
    public int update(String sql,Object... parameters) throws SQLException {
        Connection connection=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int updateRows = queryRunner.update(connection, sql, parameters);
            return updateRows;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            JDBCUtilsByDruid.close(null,null,connection);
//        }

    }
}
