package com.test.dummy.model;

import java.io.Serializable;
import java.util.List;

public class Restaurant implements Serializable {

    private String name;
    private Integer orderCapacity;
    private List<Item> MenuList;
    private List<Order> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderCapacity() {
        return orderCapacity;
    }

    public void setOrderCapacity(Integer orderCapacity) {
        this.orderCapacity = orderCapacity;
    }

    public List<Item> getMenuList() {
        return MenuList;
    }

    public void setMenuList(List<Item> menuList) {
        MenuList = menuList;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
