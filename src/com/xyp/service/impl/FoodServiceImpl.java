package com.xyp.service.impl;

import com.xyp.dao.impl.FoodDaoImpl;
import com.xyp.domain.Food;
import com.xyp.domain.Page;
import com.xyp.service.FoodService;

import java.sql.SQLException;
import java.util.List;

public class FoodServiceImpl implements FoodService {
    FoodDaoImpl foodDao = new FoodDaoImpl();

    @Override
    public int addFood(Food food) throws SQLException {

        return foodDao.addFood(food);
    }

    @Override
    public int deleteFoodById(Integer id) throws SQLException {
        return foodDao.deleteFoodById(id);
    }

    @Override
    public int updateFood(Food food) throws SQLException {
        return foodDao.updateFood(food);
    }

    @Override
    public Food queryFoodById(Integer id) throws SQLException {
        return foodDao.queryFoodById(id);
    }

    @Override
    public Food queryFoodByName(String name) throws SQLException {
        return foodDao.queryFoodByName(name);
    }

    @Override
    public List<Food> queryFoods() throws SQLException {
        return foodDao.queryFoods();
    }

    @Override
    public Page<Food> page(int pageNo, int pageSize) throws SQLException {
        //这个方法返回Page对象，一个Page对象有5个参数，2个参数已知
        //求剩下的三个参数，pageTotal、pageTotalCount、items
        //其要求返回Page对象，没有就创建
        Page<Food> page = new Page<Food>();


        page.setPageSize(pageSize);
        int pageTotalCount = foodDao.queryPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;

        }

        //也可以把数据边界检查分为最小范围检查和最大范围检查，
        // 分别写到setPageNo()和setPageTotal()，
        // 能够有效避免必须先写setPageTotal()后写setPageNo()的弊端
        page.setPageNo(pageNo);

        page.setPageTotal(pageTotal);


        int begin = (page.getPageNo() - 1) * pageSize;
        List<Food> list = foodDao.queryPageItems(begin, pageSize);
        page.setItems(list);

        return page;


    }

    @Override
    public int ratingFood(String foodName,Double rating) throws SQLException {
        Food food = foodDao.queryFoodByName(foodName);
        double newRating = ((food.getRating() * (food.getSales() - 1) + rating) / food.getSales());
        food.setRating(newRating);
        return foodDao.updateFood(food);
    }

    @Override
    public Page<Food> pageByTypePrice(int pageNo, int pageSize, String type, int min, int max) throws SQLException {
        //这个方法返回Page对象，一个Page对象有5个参数，2个参数已知
        //求剩下的三个参数，pageTotal、pageTotalCount、items
        //其要求返回Page对象，没有就创建
        Page<Food> page = new Page<Food>();

        page.setPageSize(pageSize);
        int pageTotalCount = foodDao.queryPageTotalCountByTypePrice(type,min,max);
        page.setPageTotalCount(pageTotalCount);

        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;

        }

        //也可以把数据边界检查分为最小范围检查和最大范围检查，
        // 分别写到setPageNo()和setPageTotal()，
        // 能够有效避免必须先写setPageTotal()后写setPageNo()的弊端
        page.setPageNo(pageNo);

        page.setPageTotal(pageTotal);


        int begin = (page.getPageNo() - 1) * pageSize;
        List<Food> list = foodDao.queryPageItemsByTypePrice(begin, pageSize,type,min,max);
        page.setItems(list);

        return page;

    }
    @Override
    public Page<Food> pageByPrice(int pageNo, int pageSize, int min, int max) throws SQLException {
        //这个方法返回Page对象，一个Page对象有5个参数，2个参数已知
        //求剩下的三个参数，pageTotal、pageTotalCount、items
        //其要求返回Page对象，没有就创建
        Page<Food> page = new Page<Food>();

        page.setPageSize(pageSize);
        int pageTotalCount = foodDao.queryPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;

        }

        //也可以把数据边界检查分为最小范围检查和最大范围检查，
        // 分别写到setPageNo()和setPageTotal()，
        // 能够有效避免必须先写setPageTotal()后写setPageNo()的弊端
        page.setPageNo(pageNo);

        page.setPageTotal(pageTotal);


        int begin = (page.getPageNo() - 1) * pageSize;
        List<Food> list = foodDao.queryPageItemsByPrice(begin, pageSize,min,max);
        page.setItems(list);
        return page;

    }


}
