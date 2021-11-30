package com.case_study.ShoppingCart.controllers;

import java.util.List;

import com.case_study.ShoppingCart.entities.CartItemRequest;
import com.case_study.ShoppingCart.services.CartItemService;
import com.case_study.ShoppingCart.entities.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartItemController {
	
	@Autowired
	CartItemService cartItemService;
	
	/**
     * 12.-------------------- Get Cart ---------------------
     */
	@GetMapping(path = "/{userId}/getCart")
	public ResponseEntity<List<CartItem>> getCart(@PathVariable("userId") Long uid) {
		return new ResponseEntity<>(cartItemService.getCart(uid), HttpStatus.OK);
	}
	
	/**
     * 13.-------------------- Get Cart Item ---------------------
     */
	@GetMapping(path = "/{userId}/getCartItem/{cartItemId}")
	public ResponseEntity<CartItem> getCartItem(@PathVariable("userId") Long uid, @PathVariable("cartItemId") Long cid) {
		return new ResponseEntity<>(cartItemService.getCartItem(uid, cid), HttpStatus.OK);
	}
	
	/**
     * 14.-------------------- Add To Cart ---------------------
     */
	@GetMapping(path = "/{userId}/add/{productId}")
	public ResponseEntity<CartItem> addToCart(@PathVariable("userId") Long uid, @PathVariable("productId") Long pid) {
		return new ResponseEntity<>(cartItemService.addToCart(uid, pid), HttpStatus.OK);
	}
	
	/**
     * 15.-------------------- Remove From Cart ---------------------
     */
	@GetMapping(path = "/{userId}/remove/{productId}")
	public ResponseEntity<String> removeFromCart(@PathVariable("userId") Long uid, @PathVariable("productId") Long pid) {
		return new ResponseEntity<String>(cartItemService.removeFromCart(uid, pid), HttpStatus.OK);
	}
	
	/**
     * 16.-------------------- Change Quantity of Product ---------------------
     */
	@PostMapping(path = "/changeQuantity/{cartItemId}")
	public ResponseEntity<CartItem> changeQuantity(@PathVariable("cartItemId") Long cid, @RequestBody CartItemRequest cartItemRequest) {
		return new ResponseEntity<CartItem>(cartItemService.changeQuantity(cid, cartItemRequest), HttpStatus.OK);
	}
}