package com.case_study.ShoppingCart.services;

import java.util.ArrayList;
import java.util.List;

import com.case_study.ShoppingCart.entities.Orders;
import com.case_study.ShoppingCart.entities.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.case_study.ShoppingCart.repositories.OrderRepository;
import com.case_study.ShoppingCart.repositories.ProductRepository;
import com.case_study.ShoppingCart.entities.CartItem;
import com.case_study.ShoppingCart.repositories.CartItemRepository;
import com.case_study.ShoppingCart.repositories.UserRepository;
import com.case_study.ShoppingCart.repositories.UserRepository1;
import com.case_study.ShoppingCart.entities.Users;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRepository1 userRepository1;

	public Orders createOrder(Long uid) {
		Users user = userRepository.getById(uid);
		List<CartItem> products = cartItemRepository.getCartByUserId(user);

		if(products.isEmpty()) {
			throw new IllegalStateException("Cart is empty");
		}

		List<Product> product = new ArrayList<Product>();
		for(CartItem cartItem : products) {
			product.add(cartItem.getProduct());
		}
		
		System.out.printf("%nOrder is being placed%n%n");
		Orders order = new Orders(
			user,
			product,
			"Order Placed"
		);
		orderRepository.save(order);
		
		cartItemRepository.deleteAllByUser(user);
		System.out.printf("%nAll Item is deleted from db with User ID = %d%n%n", uid);
		return order;
	}

	public List<Orders> orderHistory(Long uid) {
		Users user = userRepository.findById(uid).get();
		System.out.printf("Fetch Order history:: User ID = %d%n%n", uid);

		return orderRepository.getHistory(user);
	}
}
