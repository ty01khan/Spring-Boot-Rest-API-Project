package com.case_study.ShoppingCart.repositories;

import com.case_study.ShoppingCart.entities.UserCredentials;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository1 extends JpaRepository<UserCredentials, Long> {
	Optional<UserCredentials> findByEmail(String email);
}
