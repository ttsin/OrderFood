package com.xyp.dao;

import com.xyp.domain.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao {
    public int addFood(Food food) throws SQLException;
    public int deleteFoodById(Integer id) throws SQLException;
    public int updateFood(Food food) throws SQLException;
    public Food queryFoodById(Integer id) throws SQLException;
    public Food queryFoodByName(String name) throws SQLException;
    public List<Food> queryFoods() throws SQLException;

    public int queryPageTotalCount() throws SQLException;

    public List<Food> queryPageItems(int begin, int pageSize) throws SQLException;

    public int queryPageTotalCountByTypePrice(String type, int min, int max) throws SQLException;

    public List<Food> queryPageItemsByTypePrice(int begin, int pageSize, String type, int min, int max) throws SQLException;

    public int queryPageTotalCountByPrice( int min, int max) throws SQLException;

    public List<Food> queryPageItemsByPrice(int begin, int pageSize, int min, int max) throws SQLException;
}
