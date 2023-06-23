package com.xyp.domain;

import java.math.BigDecimal;

public class Food {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String type;
    private Integer sales;  //已售
    private Double rating;  //评分
    private String imgPath="static/img/default.jpg";

    public Food() {
    }

    public Food(Integer id, String name, BigDecimal price, String type, Integer sales, Double rating, String imgPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.sales = sales;
        this.rating = rating;
        if(imgPath != null && imgPath !=""){
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if(imgPath != null && imgPath !=""){
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", sales=" + sales +
                ", rating=" + rating +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
