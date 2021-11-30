package com.case_study.ShoppingCart.controllers;

import com.case_study.ShoppingCart.entities.UserCredentials;
import com.case_study.ShoppingCart.services.UserService;
import com.case_study.ShoppingCart.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 1.-------------------- Login ---------------------
     */
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials user) {
        return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
    }

    /**
     * 2.-------------------- SignUp ---------------------
     */
    @PostMapping(path = "/signup")
    public ResponseEntity<String> register(@RequestBody UserCredentials user) {
    	System.out.println(user.getName() + ", User signing up.");
        return new ResponseEntity<>(userService.signUpUser(user), HttpStatus.OK);
    }

    /**
     * 3.-------------------- LogOut ---------------------
     */
    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(@RequestBody UserCredentials user) {
        return new ResponseEntity<>(userService.logoutUser(user), HttpStatus.OK);
    }

    /**
     * 4.-------------------- Get Profile ---------------------
     */
    @GetMapping(path = "/getprofile/{userId}")
    public ResponseEntity<Users> getProfile(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.getProfileById(id), HttpStatus.OK);
    }

    /**
     * 5.-------------------- Update Profile ---------------------
     */
    @PutMapping(path = "/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody Users user) {
        return new ResponseEntity<>(userService.updateProfile(user), HttpStatus.OK);
    }
}
