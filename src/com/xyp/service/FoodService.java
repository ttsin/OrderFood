package com.xyp.service;

import com.xyp.domain.Food;
import com.xyp.domain.Page;

import java.sql.SQLException;
import java.util.List;

public interface FoodService {
    public int addFood(Food food) throws SQLException;
    public int deleteFoodById(Integer id) throws SQLException;
    public int updateFood(Food food) throws SQLException;
    public Food queryFoodById(Integer id) throws SQLException;
    public Food queryFoodByName(String name) throws SQLException;
    public List<Food> queryFoods() throws SQLException;

    public Page<Food> page(int pageNo, int pageSize) throws SQLException;
    public int ratingFood(String foodName,Double rating) throws SQLException;


    public Page<Food> pageByTypePrice(int pageNo, int pageSize, String type, int min, int max) throws SQLException;
    public Page<Food> pageByPrice(int pageNo, int pageSize, int min, int max) throws SQLException;
}
