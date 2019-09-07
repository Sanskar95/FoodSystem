package com.test.dummy.service;


import com.test.dummy.Exception.OrderInvalidException;
import com.test.dummy.model.Item;
import com.test.dummy.model.Order;
import com.test.dummy.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private ItemService itemService;

    public List<Restaurant> loadAndReturnRestaurants(){

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurantOne= new Restaurant();
        restaurantOne.setName("restaurant1");
        itemService.assignItemsToRestaurant(restaurantOne);
        restaurantOne.setOrderCapacity(2);
        restaurants.add(restaurantOne);

        Restaurant restaurantTwo = new Restaurant();
        restaurantTwo.setName("restaurant2");
        itemService.assignItemsToRestaurant(restaurantTwo);
        restaurantTwo.setOrderCapacity(3);
        restaurants.add(restaurantTwo);

        Restaurant restaurantThree = new Restaurant();
        restaurantThree.setName("restaurant3");
        itemService.assignItemsToRestaurant(restaurantThree);
        restaurantTwo.setOrderCapacity(1);
        restaurants.add(restaurantThree);


        return  restaurants;
    }

    public Restaurant getRestaurantByName(String name){
        Restaurant restaurant= loadAndReturnRestaurants().stream().filter(restaurant1 -> restaurant1.getName().equals(name)).findFirst().get();
        return  restaurant;
    }


    public Restaurant updateItemPrice(String restaurantName, String itemName, Integer price){
        Restaurant restaurant= getRestaurantByName(restaurantName);
        List<Item> items = restaurant.getMenuList();
        for(Item item: items){
            if(item.getName().equals(itemName)){
                item.setCost(price);
            }
        }

        return restaurant;
    }

    public Restaurant assignOrders(List<Order> orders, String restaurantName) throws OrderInvalidException {
       Restaurant restaurant= getRestaurantByName(restaurantName);
       if(checkValidityOfOrder(orders,restaurant) && checkOrderSizeValidity(orders, restaurant)){
           restaurant.setOrders(orders);
           return  restaurant;
       }
       else throw new OrderInvalidException("Cant assign this order,sleep hungry now");
    }

    private Boolean checkValidityOfOrder(List<Order> orders, Restaurant restaurant){
        Boolean validFlag= true;
        for(Order order: orders){
//            if(!restaurant.getMenuList().containsAll(order.getOrderList())){
//                validFlag= false;
//            }

            List<String> itemNames= restaurant.getMenuList().stream().map(Item::getName).collect(Collectors.toList());
            for(Item item: order.getOrderList()){
                if(!itemNames.contains(item.getName())){
                    validFlag=false;
                    break;
                }
            }

            if(!validFlag){
                break;
            }

        }
        return  validFlag;
    }

    private Boolean checkOrderSizeValidity(List<Order> orders, Restaurant restaurant){
        Boolean sizeFlag = true;
        if(restaurant.getOrderCapacity()<orders.size()){
            sizeFlag = false;
        }

        return sizeFlag;
    }


    public Restaurant changeOrderStatus(Restaurant restaurant, String email){

       List<Order> orders= restaurant.getOrders();
       for(Order order: orders){
           if(order.getCustomerEmail().equals(email)){
               order.setOrderStatus("Dispatched");
           }
       }

       return  restaurant;
    }



}
