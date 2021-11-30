package com.case_study.ShoppingCart.services;

import java.util.List;

import com.case_study.ShoppingCart.entities.CartItem;
import com.case_study.ShoppingCart.entities.CartItemRequest;
import com.case_study.ShoppingCart.repositories.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.case_study.ShoppingCart.entities.Product;
import com.case_study.ShoppingCart.repositories.ProductRepository;
import com.case_study.ShoppingCart.entities.UserCredentials;
import com.case_study.ShoppingCart.repositories.UserRepository;
import com.case_study.ShoppingCart.repositories.UserRepository1;
import com.case_study.ShoppingCart.entities.Users;

@Service
public class CartItemService {
	CartItemRepository cartItemRepository;
	ProductRepository productRepository;
	UserRepository userRepository;
	UserRepository1 userRepository1;
	
	public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository,
						   UserRepository userRepository, UserRepository1 userRepository1) {
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.userRepository1 = userRepository1;
	}
	
	public List<CartItem> getCart(Long uid) {
		UserCredentials _user = userRepository1.findById(uid).get();
		
		if(_user == null) {
			throw new IllegalStateException("User not exist, so no product to show in the cart");
		}
		
		if(!_user.getIsEnabled()) {
			throw new IllegalStateException("You are not Logged IN");
		}
		
		System.out.printf("%nFetch Cart of User with User ID = %d%n%n", uid);
		Users user = userRepository.findById(uid).get();
		
		return cartItemRepository.getCartByUserId(user);
	}
	
	public CartItem getCartItem(Long uid, Long cid) {
		UserCredentials _user = userRepository1.findById(uid).get();
		
		if(_user == null) {
			throw new IllegalStateException("User not exist, so no product to show in the cart");
		}
		
		if(!_user.getIsEnabled()) {
			throw new IllegalStateException("You are not Logged IN");
		}
		
		System.out.printf("%nFetch Cart Item, CartItem ID = %d%n%n", cid);
		CartItem cartItem = cartItemRepository.findById(cid).get();
		
		return new CartItem(
			cartItem.getCartItemId(),
			cartItem.getProduct(),
			cartItem.getQuantity()
		);
	}
	
	@Transactional
	public CartItem addToCart(Long uid, Long pid) {
		UserCredentials _user = userRepository1.findById(uid).get();
		Product product = productRepository.findById(pid).get();
		
		if(_user == null) {
			throw new IllegalStateException("User not exist, so no product to show in the cart");
		}
		
		if(!_user.getIsEnabled()) {
			throw new IllegalStateException("You are not Logged IN");
		}
		
		System.out.printf("%nAdd product in User Cart, User ID = %d and Product ID = %d%n%n", uid, pid);
		Users user = userRepository.getById(uid);
		CartItem cartItem = cartItemRepository.getCartItem(user, product);
		
		if(cartItem != null) {
			Long quantity = cartItem.getQuantity();
			
			cartItem.setQuantity(quantity+1);
			
			return new CartItem(
				cartItem.getCartItemId(),
				cartItem.getProduct(),
				cartItem.getQuantity()
			);
		}
		
		CartItem cartItem2 = new CartItem(
			productRepository.findById(pid).get(),
			user,
			1L
		);
		
		cartItemRepository.save(cartItem2);
		
		return new CartItem(
			cartItem2.getCartItemId(),
			cartItem2.getProduct(),
			cartItem2.getQuantity()
		);
	}
	
	public String removeFromCart(Long uid, Long pid) {
		UserCredentials _user = userRepository1.findById(uid).get();
		Product product = productRepository.findById(pid).get();
		
		if(!_user.getIsEnabled()) {
			throw new IllegalStateException("You are not Logged IN");
		}
		
		if(product == null) {
			throw new IllegalStateException("Invalid Product ID");
		}
		
		Users user = userRepository.findById(uid).get();
		CartItem cartItem = cartItemRepository.getCartItem(user, product);
		cartItemRepository.deleteCartItem(cartItem.getCartItemId());
		
		System.out.printf("%nRemove Cart Item from User Cart, User ID = %d and Cart Item ID = %d%n%n", uid, cartItem.getCartItemId());
		
		return ("{" + product.getName() + " removed from cart}");
	}
	
	@Transactional
	public CartItem changeQuantity(Long cid, CartItemRequest cartItemRequest) {
		CartItem cartItem = cartItemRepository.findById(cid).get();
		
		if(cartItem == null) {
			throw new IllegalStateException("Cart Item doesn't exists::");
		}
		
		Users user = cartItemRepository.getById(cid).getUser();
		UserCredentials _user = userRepository1.findById(user.getUserID()).get();
		
		if(!_user.getIsEnabled()) {
			throw new IllegalStateException("You are not Logged IN");
		}
		
		System.out.printf("%nChange quantity of Cart Item:: Cart Item ID = %d%n%n", cartItem.getCartItemId());
		
		cartItem.setQuantity(cartItemRequest.getQuantity());
		return new CartItem(
			cartItem.getCartItemId(),
			cartItem.getProduct(),
			cartItem.getQuantity()
		);
	}
}