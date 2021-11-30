package com.case_study.ShoppingCart.repositories;

import java.util.List;

import com.case_study.ShoppingCart.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.case_study.ShoppingCart.entities.Product;
import com.case_study.ShoppingCart.entities.Users;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.user=?1 AND ci.product=?2")
	public CartItem getCartItem(Users user, Product product);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.user=?1")
	public List<CartItem> getCartByUserId(Users user);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CartItem ci WHERE ci.cartItemId=?1")
	public void deleteCartItem(Long cid);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM CartItem ci WHERE cartItemId IN :itemIds")
	public void deleteAllItem(@Param("itemIds") List<Long> itemIds);

	@Transactional
	@Modifying
	@Query("DELETE FROM CartItem ci WHERE ci.user=?1")
	public void deleteAllByUser(Users user);
}
