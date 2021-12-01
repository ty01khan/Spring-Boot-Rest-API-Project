package com.case_study.ShoppingCart.entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@SequenceGenerator(
            name = "sequence4",
            sequenceName = "sequence4",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence4"
    )
	private Long orderId;

	@ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	@JoinColumn(name = "userId", referencedColumnName = "userID")
	private Users user;

	@ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "products", referencedColumnName = "productId")
	private List<Product> products;
	private String orderStatus;

	public Orders(Users user, List<Product> products, String orderStatus) {
		super();
		this.user = user;
		this.products = products;
		this.orderStatus = orderStatus;
	}
}