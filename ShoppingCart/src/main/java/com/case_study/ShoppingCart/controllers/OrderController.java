package com.case_study.ShoppingCart.controllers;

import com.case_study.ShoppingCart.entities.Orders;
import com.case_study.ShoppingCart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(path = "/{userId}/getOrders")
    public ResponseEntity<List<Orders>> getOrders(@PathVariable("userId") Long uid) {
        return new ResponseEntity<>(orderService.orderHistory(uid), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/createOrder")
    public ResponseEntity<Orders> createOrder(@PathVariable("userId") Long uid) {
    	Orders order = orderService.createOrder(uid);
    	System.out.println("Controller = " + order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
