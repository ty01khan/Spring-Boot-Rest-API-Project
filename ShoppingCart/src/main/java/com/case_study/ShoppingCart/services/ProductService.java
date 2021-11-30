package com.case_study.ShoppingCart.services;

import com.case_study.ShoppingCart.entities.Product;
import com.case_study.ShoppingCart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

    public Product addProduct(Product product) {
    	System.out.printf("%nAdd product:: %s%n%n", product.getName());
    	
        productRepository.save(product);
        return productRepository.findAddedProduct();
    }
    
    @Transactional
    public Product updateProduct(Product product) {
    	Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
    	if(!optionalProduct.isPresent()) {
    		throw new IllegalStateException("Product ID = " + product.getProductId() + ", doesn't exists.");
    	}
    	
    	System.out.printf("%nUpdate product details:: Product ID = %d%n%n", product.getProductId());
    	
    	Product p = optionalProduct.get();
    	p.setName(product.getName());
    	p.setPrice(product.getPrice());
    	p.setDetails(product.getDetails());
    	p.setCategory(product.getCategory());
    	p.setSubcategory(product.getSubcategory());
    	
    	return p;
    }
    
    public Product getProduct(Long productId) {
    	Optional<Product> optionalProduct = productRepository.findById(productId);
    	if(!optionalProduct.isPresent()) {
    		throw new IllegalStateException("Product ID = " + productId + ", doesn't exists.");
    	}
    	
    	System.out.printf("%nFetch product:: Product ID = %d%n%n", productId);
    	return optionalProduct.get();
    }
    
    public List<Product> getProductByCategory(String category) {
    	List<Product> productList = productRepository.findAllProductsByCategory(category);
    	if(productList.isEmpty()) {
    		throw new IllegalStateException("No products exists of " + category + " category.");
    	}
    	
    	System.out.printf("%nFetch product by category:: Catefory = %s%n%n", category);
    	return productList;
    }
    
    public List<Product> getProductBySearch(String search) {
    	List<Product> productList = productRepository.findAllProductsByName(search);
    	if(productList.isEmpty()) {
    		throw new IllegalStateException(search + ": No such product exists.");
    	}
    	
    	System.out.printf("%nFetch product having string %s", search);
    	return productList;
    }
}
