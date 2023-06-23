package com.xyp.web;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import com.xyp.domain.Cart;
import com.xyp.domain.CartItem;
import com.xyp.domain.Food;
import com.xyp.service.impl.FoodServiceImpl;
import com.xyp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class CartServlet extends BaseServlet {
    FoodServiceImpl foodService = new FoodServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //分析要做什么：1.获取商品的id
        //2.根据id找到该商品Food，调用FoodServlet.queryFoodByID()应该是这个方法
        //3.根据id的属性，new一个CartItem对象，通过cart.addItem(),加入购物车中
        //4.重定向到首页
        String foodId = req.getParameter("foodId");
        Food food = foodService.queryFoodById(WebUtils.parseInt(foodId, 0));
        //一个用户一个购物车，先判断session中是否有之前创建的购物车，
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            //没有创建过购物车，就创建购物车，并保存到session域中
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(new CartItem(food.getId(), food.getName(), 1, food.getPrice(), food.getPrice()));
//        System.out.println(cart);
        //把添加的商品保存到session域中，以方便与首页的回显
        req.getSession().setAttribute("lastName",food.getName());
        //不能直接重定向到首页，因为把下一页的商品加入到购物车中时，地址的参数会变化，
        //请求头中的参数Referer，能当前请求发送时的浏览器的地址，即从哪里来，回哪里去
        resp.sendRedirect(req.getHeader("Referer"));


    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //分析要做什么：1.获取商品的id
        //2.根据id找到该商品Food，调用FoodServlet.queryFoodByID()应该是这个方法
        //3.根据id的属性，new一个CartItem对象，通过cart.addItem(),加入购物车中
        String foodId = req.getParameter("foodId");
        Food food = foodService.queryFoodById(WebUtils.parseInt(foodId, 0));
        //一个用户一个购物车，先判断session中是否有之前创建的购物车，
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            //没有创建过购物车，就创建购物车，并保存到session域中
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(new CartItem(food.getId(), food.getName(), 1, food.getPrice(), food.getPrice()));

        //给客户端发送购物车总数和商品名
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",food.getName());
        //转成JSON
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);


    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //思路：拿到id,拿到在session域中的cart，并调用删除方法
        // 增删改操作，用重定向
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart !=null){
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));

    }
    protected void clean(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //思路：拿到在session域中的cart，并调用清空方法
        // 增删改操作，用重定向
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart !=null){
            cart.clean();
        }
        resp.sendRedirect(req.getHeader("Referer"));

    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //思路：修改商品的数量，拿到参数商品id,和要修改的数量
        // 2.获取购物车，调用方法updateCount（），
        // 3.重定向
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart !=null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));

    }
}
