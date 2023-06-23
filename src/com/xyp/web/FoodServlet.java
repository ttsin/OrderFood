package com.xyp.web;

import com.xyp.domain.Food;
import com.xyp.domain.Page;
import com.xyp.service.impl.FoodServiceImpl;
import com.xyp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FoodServlet extends BaseServlet {
    FoodServiceImpl foodService = new FoodServiceImpl();

    protected void addFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //获取传入的参数，称为一个food对象
        //添加到数据库
        //跳转到菜品list界面，（用重定向，不能用请求转发，因为请求转发是一次请求，
        // 当用户按下F5快捷键时，浏览器会把最后一次请求发送给服务器，
        // 而此时最后一次请求是food_edit.jsp文件发送给FoodServlet添加菜品，会出现多次添加的问题，
        // 而重定向是两次请求，此时的最后一次请求是foodServlet 跳转到food_manager.jsp界面，避免了上述问题）

        // 获取传入的参数，成为一个food对象
        Food food = WebUtils.copyParamToBean(req.getParameterMap(), new Food());
        //添加到数据库

        foodService.addFood(food);
        // 使用重定向跳转到food_manager/jsp页面，food_manager.jsp页面的访问一定要先调用 foodServlet中的list方法
        // 不然没有数据，/manager/foodServlet?action=list

        int pageTotal = WebUtils.parseInt(req.getParameter("pageTotal"), 0);
        pageTotal+=1;
        //因为当添加菜品时，pageTotal（总分页数）可能会因为添加的一个菜品而改变
        // 如果改变，pageTotal+1，正好是最后一页，
        // 如果不改变，因为数据检验操作，浏览器上的pageNo > 实际的pageTotal时，会令pageNo=pageTotal，
        //也正好是最后一页

        resp.sendRedirect(req.getContextPath()+"/manager/foodServlet?action=page&pageNo="+pageTotal);

    }

    protected void updateFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Food food = WebUtils.copyParamToBean(req.getParameterMap(), new Food());
        foodService.updateFood(food);
        resp.sendRedirect(req.getContextPath()+"/manager/foodServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    protected void deleteFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
//        System.out.println("deleteFood()方法被调用了");

        //获取id
        //删除id菜品
        // 重定向到 /manager/foodServlet?action=list
        String strId = req.getParameter("id");
        int id = Integer.parseInt(strId);
        foodService.deleteFoodById(id);
        resp.sendRedirect(req.getContextPath()+"/manager/foodServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        FoodServiceImpl foodService = new FoodServiceImpl();
        List<Food> foods = foodService.queryFoods();
        req.setAttribute("foods",foods);
        req.getRequestDispatcher("/pages/manager/food_manager.jsp").forward(req,resp);


    }
    protected void getFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        Food food = foodService.queryFoodById(i);
        req.setAttribute("food",food);
        //因为请求转发是一次请求，浏览器中的地址不变，所以跳到food_edit.jsp界面时，浏览器的地址连接
        // 还是<a href="manager/foodServlet?action=getFood&id=${food.id}&method=updateFood&pageNo=${requestScope.page.pageNo}">修改</a>
        //会带上pageNo=....,所以在food_edit.jsp界面中获取pageNo，这里不用获取
        req.getRequestDispatcher("/pages/manager/food_edit.jsp").forward(req,resp);

    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //获取请求参数，pageNo、pageSize
        //调用Service层方法page(pageNo,pageSize)得到Page对象
        //保存到request域中
        //请求转发到/page/manager_foot.jsp中
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Food> page=foodService.page(pageNo,pageSize);
        page.setUrl("manager/foodServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/food_manager.jsp").forward(req,resp);


    }

}
