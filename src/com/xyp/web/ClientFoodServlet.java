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

public class ClientFoodServlet extends BaseServlet{
    FoodServiceImpl foodService = new FoodServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //获取请求参数，pageNo、pageSize
        //调用Service层方法page(pageNo,pageSize)得到Page对象
        //保存到request域中
        //请求转发到/page/manager_foot.jsp中
//        System.out.println("经过了clientFoodServlet");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Food> page=foodService.page(pageNo,pageSize);
        page.setUrl("client/foodServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);


    }
    protected void pageByTypePrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //获取请求参数，type ,min,max,pageNo、pageSize
        //调用Service层方法page(pageNo,pageSize)得到Page对象
        //保存到request域中
        //请求转发到
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        String type = req.getParameter("type");
        if("全部".equals(type)){
            pageByPrice(req,resp);

        }else{
            Page<Food> page=foodService.pageByTypePrice(pageNo,pageSize,type,min,max);
            StringBuilder sb = new StringBuilder("client/foodServlet?action=pageByTypePrice");
            if(req.getParameter("min")!= null){
                sb.append("&min=").append(req.getParameter("min"));
            }
            if(req.getParameter("max")!= null){
                sb.append("&max=").append(req.getParameter("max"));
            }if(req.getParameter("type")!= null){
                sb.append("&type=").append(req.getParameter("type"));
            }
            page.setUrl(sb.toString());
            req.setAttribute("page",page);
//            req.setAttribute("type",type);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        }



    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Food> page=foodService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/foodServlet?action=pageByPrice");
        if(req.getParameter("min")!= null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!= null){
            sb.append("&max=").append(req.getParameter("max"));
        }if(req.getParameter("type")!= null){
            sb.append("&type=").append(req.getParameter("type"));
        }
        page.setUrl(sb.toString());
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);


    }
    protected void ratingFood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //评分功能
        String foodName = req.getParameter("foodName");
        String ratingStr = req.getParameter("rating");
        double rating = Double.parseDouble(ratingStr);

        foodService.ratingFood(foodName,rating);
        //跳转原来的地址
        resp.sendRedirect(req.getHeader("Referer"));



    }
}
