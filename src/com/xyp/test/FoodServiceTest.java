package com.xyp.test;

import com.xyp.domain.Food;
import com.xyp.domain.Page;
import com.xyp.service.impl.FoodServiceImpl;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class FoodServiceTest {
    FoodServiceImpl foodService = new FoodServiceImpl();

    @Test
    public void addFood() throws SQLException {
        foodService.addFood(new Food(null,"酸辣猪脚饭",new BigDecimal(12.0),"米饭",0,0.0,"static/img/default.jpg"));

    }

    @Test
    public void deleteFoodById() throws SQLException {
        foodService.deleteFoodById(15);

    }

    @Test
    public void updateFood() throws SQLException {
        foodService.updateFood(new Food(14,"酸辣猪脚饭",new BigDecimal(12.0),"米饭",0,0.0,"static/img/default.jpg"));


    }

    @Test
    public void queryFoodById() throws SQLException {
        Food food = foodService.queryFoodById(14);
        System.out.println(food);
    }

    @Test
    public void queryFoods() throws SQLException {
        List<Food> foods = foodService.queryFoods();
        for (Food food : foods) {
            System.out.println(food.getId()+"\t"+food.getName());
        }
    }
    @Test
    public void ratingFood() throws SQLException {

//        Food food = new Food(null, "黄焖鸡米饭", new BigDecimal(12.0), "米饭", 10, 10.0, "static/img/default.jpg");
        foodService.ratingFood("桂林米粉",2.0);
//        System.out.println(food.getRating());
    }


    @Test
    public void page() throws SQLException {
        Page<Food> page = foodService.page(1, 4);
        System.out.println(page);

    }
    @Test
    public void pageByTypePrice() throws SQLException {
        Page<Food> page = foodService.pageByTypePrice(1,2,"粉",0,10);
        System.out.println(page);

    }
    @Test
    public void pageByPrice() throws SQLException {
        Page<Food> page = foodService.pageByPrice(1, 2,0,10);
        System.out.println(page);

    }

}