package com.xyp.test;

import com.xyp.domain.Cart;
import com.xyp.domain.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(2,"螺蛳粉",1,new BigDecimal(8),new BigDecimal(8)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(2,"螺蛳粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.deleteItem(1);
        System.out.println(cart);

    }

    @Test
    public void clean() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(2,"螺蛳粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.clean();
        System.out.println(cart);

    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(1,"桂林米粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.addItem(new CartItem(2,"螺蛳粉",1,new BigDecimal(8),new BigDecimal(8)));
        cart.updateCount(2,10);
        System.out.println(cart);

    }
}