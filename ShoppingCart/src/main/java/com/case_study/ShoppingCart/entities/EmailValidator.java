package com.case_study.ShoppingCart.entities;

import java.util.function.Predicate;

public class EmailValidator implements Predicate<String> {

	@Override
	public boolean test(String email) {
		if(email.endsWith("@beehyv.com"))
			return true;
		return false;
	}
}
