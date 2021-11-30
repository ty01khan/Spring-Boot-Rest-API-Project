package com.case_study.ShoppingCart.repositories;

import com.case_study.ShoppingCart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);
    Optional<Product> findById(Long productId);
    Optional<Product> findProductByCategory(String category);
    Optional<Product> findByCategory(String category);

    @Query ("SELECT p FROM Product p WHERE p.productId = (SELECT MAX(productId) FROM Product)")
    public Product findAddedProduct();
    
    @Query ("SELECT p FROM Product p WHERE p.category LIKE %?1%")
    public List<Product> findAllProductsByCategory(String category);

    @Query ("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    public List<Product> findAllProductsByName(String search);
}
