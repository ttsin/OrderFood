package com.xyp.test;

import com.xyp.utils.JDBCUtilsByDruid;
import org.junit.Test;

import java.sql.SQLException;

public class JdbcUtilsTest {
//    public static void main(String[] args) throws SQLException {
//
//    }
    @Test
    public void test() throws SQLException {
        System.out.println("hello");
        System.out.println(JDBCUtilsByDruid.getConnection());
    }




}
