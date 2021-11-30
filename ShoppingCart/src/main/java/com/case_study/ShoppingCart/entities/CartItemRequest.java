package com.case_study.ShoppingCart.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemRequest {
	private Long quantity;
}
