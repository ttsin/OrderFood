package com.xyp.domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    public void addItem(CartItem cartItem) {
        //思路，添加商品项，首先要判断这个商品是否存在，存在，商品数量累加，该商品项的总金额更新，不存在，商品加入到集合中
        // Map 集合，通过key,来查找和储存数据，键是唯一的
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    public void deleteItem(Integer id) {
        //思路：判断这个商品是否在items集合中，如果在，就删除，不在，不作任何操作
        CartItem item = items.get(id);
        if (item != null) {
            items.remove(id);
        }

    }

    public void clean() {
        //思路：清除所有的商品项，即清除items集合
        items.clear();

    }

    public void updateCount(Integer id, Integer count) {
        //思路：找打这个商品项，更改商品数量，并更改商品总价
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));

        }


    }


    public Cart() {
    }

    public Cart( Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        //商品的总数量要看items中的商品的总数量，要遍历循环得出，不能直接设置它的值，所以setTotalCount()方法应该删除
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        //购物车的总价钱的要看items中的商品的总价钱，要遍历循环得出，不能直接设置它的值，所以setTotalPrice()方法应该删除
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
