package com.case_study.ShoppingCart.controllers;

import java.util.List;

import com.case_study.ShoppingCart.entities.Product;
import com.case_study.ShoppingCart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;
	
	/**
     * 6.-------------------- Add Product ---------------------
     */
	@PostMapping(path = "/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
	}
	
	/**
     * 7.-------------------- Modify Product ---------------------
     */
	@PutMapping(path = "/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
	}
	
	/**
     * 8.-------------------- Get Product ---------------------
     */
	@GetMapping(path = "/getById/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}
	
	/**
     * 9.-------------------- Get Product By Category ---------------------
     */
	@GetMapping(path = "/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) {
		return new ResponseEntity<>(productService.getProductByCategory(category), HttpStatus.OK);
	}
	
	/**
     * 10.-------------------- Get Product By Search String ---------------------
     */
	@GetMapping("/search/{searchString}")
	public ResponseEntity<List<Product>> getProductBySearchString(@PathVariable("searchString") String search) {
		return new ResponseEntity<>(productService.getProductBySearch(search), HttpStatus.OK);
	}
}
