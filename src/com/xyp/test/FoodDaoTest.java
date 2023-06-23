package com.xyp.test;

import com.xyp.dao.impl.FoodDaoImpl;
import com.xyp.domain.Food;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FoodDaoTest {
    FoodDaoImpl foodDao = new FoodDaoImpl();

    @Test
    public void addFood() throws SQLException {
        foodDao.addFood(new Food(null,"酸辣猪脚饭",new BigDecimal(12.0),"米饭",0,0.0,"static/img/default.jpg"));


    }

    @Test
    public void deleteFoodById() throws SQLException {
        foodDao.deleteFoodById(13);
    }

    @Test
    public void updateFood() throws SQLException {
        foodDao.updateFood(new Food(14,"酸辣猪脚饭",new BigDecimal(15.0),"米饭",0,0.0,"static/img/default.jpg"));

    }

    @Test
    public void queryFoodById() throws SQLException {
        Food food = foodDao.queryFoodById(14);
        System.out.println(food);
    }

    @Test
    public void queryFoods() throws SQLException {
        List<Food> foods = foodDao.queryFoods();
        for (Food food : foods) {
            System.out.println(food.getId()+"\t"+food.getName());
        }
    }
    @Test
        public void queryPageTotalCount() throws SQLException {
            int i = foodDao.queryPageTotalCount();
            System.out.println(i);
        }

        @Test
        public void queryPageItems() throws SQLException {
            List<Food> foods = foodDao.queryPageItems(0, 4);
            for (Food food : foods) {
                System.out.println(food);
            }

    }
    @Test
    public void queryPageTotalCountByTypePrice() throws SQLException {
        int i = foodDao.queryPageTotalCountByTypePrice("粉",0,10);
        System.out.println(i);
    }

    @Test
    public void queryPageItemsByTypePrice() throws SQLException {
        List<Food> foods = foodDao.queryPageItemsByTypePrice(0,2,"粉",0,10);
        for (Food food : foods) {
            System.out.println(food);
        }

    }
    @Test
    public void queryPageTotalCountByPrice() throws SQLException {
        int i = foodDao.queryPageTotalCountByPrice(0,10);
        System.out.println(i);
    }

    @Test
    public void queryPageItemsByPrice() throws SQLException {
        List<Food> foods = foodDao.queryPageItemsByPrice(0,2,0, 10);
        for (Food food : foods) {
            System.out.println(food);
        }

    }
}