package com.test.dummy.controller;


import com.test.dummy.Exception.OrderInvalidException;
import com.test.dummy.model.Order;
import com.test.dummy.model.Restaurant;
import com.test.dummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;


    @GetMapping("get_restaurents/")
    public List<Restaurant> getRestaurants() {
        return restaurantService.loadAndReturnRestaurants();
    }

    @GetMapping("update_price_of_item/{restaurantName}/{itemName}/{price}")
    public Restaurant updatePriceOfItem(@PathVariable String  restaurantName, @PathVariable String itemName, @PathVariable Integer price) {
        return restaurantService.updateItemPrice(restaurantName,itemName,price);
    }

    @PutMapping("assign_order/{restaurantName}")
    public Restaurant assignOrder(@PathVariable String restaurantName, @RequestBody List<Order> orders) throws OrderInvalidException {
        return restaurantService.assignOrders(orders, restaurantName);
    }

    @GetMapping("change_order_status/{restaurantName}/{customerEmail}/")
    public Restaurant updatePriceOfItem(@PathVariable Restaurant  restaurantName, @PathVariable String customerEmail) {
        return restaurantService.changeOrderStatus(restaurantName,customerEmail);
    }
}
