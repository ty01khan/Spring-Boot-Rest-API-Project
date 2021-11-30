package com.case_study.ShoppingCart.repositories;

import com.case_study.ShoppingCart.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    @Query("SELECT a FROM Users a WHERE a.email=?1")
    public Users findByEmail1(String email);
    
    @Query ("SELECT max(user.userID) FROM Users user")
    public Long findAddedUserID();
}
