package com.case_study.ShoppingCart.repositories;

import com.case_study.ShoppingCart.entities.Orders;
import com.case_study.ShoppingCart.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	@Query("SELECT o FROM Orders o WHERE o.user=?1")
	public List<Orders> getHistory(Users user);
}