package com.xyp.dao.impl;

import com.xyp.dao.FoodDao;
import com.xyp.domain.Food;

import java.sql.SQLException;
import java.util.List;

public class FoodDaoImpl extends BasicDao<Food> implements FoodDao {
    @Override
    public int addFood(Food food) throws SQLException {
        String sql= "insert into t_food(id,name,price,type,sales,rating,img_path) values(null,?,?,?,?,?,?)";

        int update = update(sql, food.getName(), food.getPrice(), food.getType(), food.getSales(), food.getRating(), food.getImgPath());
        return update;

    }

    @Override
    public int deleteFoodById(Integer id) throws SQLException {
        String sql="delete from t_food where id = ?";
        int update = update(sql, id);
        return update;
    }


    @Override
    public int updateFood(Food food) throws SQLException {
        String sql="update t_food set name=?,price=?,type=?,sales=?,rating=?,img_path=?where id= ?";
        int update = update(sql, food.getName(), food.getPrice(), food.getType(), food.getSales(), food.getRating(), food.getImgPath(), food.getId());
        return update;

    }


    @Override
    public Food queryFoodById(Integer id) throws SQLException {
        String sql="select id,name,price,type,sales,rating,img_path imgPath from t_food where id= ?";
        Food food = querySingle(sql, Food.class, id);
        return food;
    }

    @Override
    public Food queryFoodByName(String name) throws SQLException {
        String sql="select id,name,price,type,sales,rating,img_path imgPath from t_food where name= ?";
        Food food = querySingle(sql, Food.class, name);
        return food;
    }


    @Override
    public List<Food> queryFoods() throws SQLException {
        String sql="select id,name,price,type,sales,rating,img_path imgPath from t_food ";
        List<Food> list = queryMultiLine(sql, Food.class);
        return list;
    }

    @Override
    public int queryPageTotalCount() throws SQLException {
        String sql= "select count(*) from t_food";
        Number count = (Number) queryOnlyValue(sql);
        return count.intValue();
    }

    @Override
    public List<Food> queryPageItems(int begin, int pageSize) throws SQLException {
        String sql="select id,name,price,type,sales,rating,img_path imgPath from t_food limit ?,?";
        List<Food> items = queryMultiLine(sql, Food.class, begin, pageSize);
        return items;

    }

    @Override
    public int queryPageTotalCountByTypePrice(String type, int min, int max) throws SQLException {
        String sql= "select count(*) from t_food where (price between ? and ?) and type=?";
        Number count = (Number) queryOnlyValue(sql,min,max,type);
        return count.intValue();
    }

    @Override
    public List<Food> queryPageItemsByTypePrice(int begin, int pageSize, String type, int min, int max) throws SQLException {
        String sql="select id,name,price,type,sales,rating,img_path imgPath from t_food where (price between ? and ?) and type=? limit ?,?";
        List<Food> items = queryMultiLine(sql, Food.class, min,max,type,begin, pageSize);
        return items;

    }
    @Override
    public int queryPageTotalCountByPrice(int min, int max) throws SQLException {
        String sql= "select count(*) from t_food where price between ? and ?";
        Number count = (Number) queryOnlyValue(sql,min,max);
        return count.intValue();
    }
    @Override
    public List<Food> queryPageItemsByPrice(int begin, int pageSize,int min, int max) throws SQLException {
        String sql="select id,name,price,type,sales,rating,img_path imgPath from t_food where price between ? and ? limit ?,?";
        List<Food> items = queryMultiLine(sql, Food.class, min,max,begin, pageSize);
        return items;

    }
}
