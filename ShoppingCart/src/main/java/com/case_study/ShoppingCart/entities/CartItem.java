package com.case_study.ShoppingCart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	
	@SequenceGenerator(
            name = "sequence3",
            sequenceName = "sequence3",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence3"
    )
	private Long  cartItemId;
	
	@ManyToOne(targetEntity = Product.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "productCartId", referencedColumnName = "productId")
	private Product product;
	
	@ManyToOne(targetEntity = Users.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@JoinColumn(name = "userid", referencedColumnName = "userID")
	private Users user;
	
	private Long quantity;
	
	public CartItem(Long cartItemId, Product product, Long quantity) {
		this.cartItemId = cartItemId;
		this.product = product;
		this.quantity = quantity;
	}
	
	public CartItem(Product product, Users user, Long quantity) {
		super();
		this.product = product;
		this.user = user;
		this.quantity = quantity;
	}
}