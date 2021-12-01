package com.case_study.ShoppingCart.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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

	@SuppressWarnings("deprecation")
	@ElementCollection
	@org.hibernate.annotations.ForeignKey(name = "none")
	private List<CartItem> products;
	private String orderStatus;

	public Orders(Users user, List<CartItem> products, String orderStatus) {
		super();
		this.user = user;
		this.products = products;
		this.orderStatus = orderStatus;
	}
}