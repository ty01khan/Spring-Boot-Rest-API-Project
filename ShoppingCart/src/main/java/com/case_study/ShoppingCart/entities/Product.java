package com.case_study.ShoppingCart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@SequenceGenerator(
            name = "sequence2",
            sequenceName = "sequence2",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence2"
    )
    private Long productId;
    @Column(unique = true)
    private String name;
    private Long price;
    private String details;
    private String category;
    @ElementCollection
    private List<String> subcategory;

    public Product(String name, Long price, String details, String category, List<String> subcategory) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.category = category;
        this.subcategory = subcategory;
    }
}
