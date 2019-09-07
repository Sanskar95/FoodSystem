package com.test.dummy.service;


import com.test.dummy.ApplicationConstants;
import com.test.dummy.model.Item;
import com.test.dummy.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {



    private List<Item> getListOfItems(){
        List<Item> items = new ArrayList<>();
        for(Integer i = 0; i<15; i++){
            Item item = new Item();
            item.setName("item"+i.toString());
            item.setCost(i+ ApplicationConstants.COST_INCREMENT);
            item.setId(i);
            items.add(item);
        }

        return items;
    }

    public Restaurant assignItemsToRestaurant(Restaurant restaurant){
        switch (restaurant.getName()){
            case "restaurant1":
                Integer counter = 0;
                List<Item> itemsToAssign = new ArrayList<>();
                for(int i=0;i<=4;i++){
                    itemsToAssign.add(getListOfItems().get(i));
                }
                restaurant.setMenuList(itemsToAssign);
                break;

            case "restaurant2":
                List<Item> itemsToAssignTwo = new ArrayList<>();
                for(int j=5;j<=9;j++){
                    itemsToAssignTwo.add(getListOfItems().get(j));
                }
                restaurant.setMenuList(itemsToAssignTwo);
                break;

            case "restaurant3":
                List<Item> itemsToAssignThree = new ArrayList<>();
                for(int k=10;k<=14;k++){
                    itemsToAssignThree.add(getListOfItems().get(k));
                }
                restaurant.setMenuList(itemsToAssignThree);
                break;


        }

        return restaurant;
    }
}
