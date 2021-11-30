package com.case_study.ShoppingCart.services;

import com.case_study.ShoppingCart.entities.UserCredentials;
import com.case_study.ShoppingCart.entities.Users;
import com.case_study.ShoppingCart.repositories.UserRepository;
import com.case_study.ShoppingCart.repositories.UserRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRepository1 userRepository1;

    @PostConstruct
    public void addAdmin() {
    	boolean isUserExists = userRepository1.findByEmail("admin@beehyv.com").isPresent();
        if(isUserExists) {
            System.out.println("Email already taken.");
            return;
        }
        
        System.out.printf("%nAdmin details is added to db%n%n");
    	String encodedPassword = Base64.getEncoder().encodeToString("admin".getBytes());
        UserCredentials user = new UserCredentials("admin", "admin@beehyv.com", encodedPassword);
        user.setIsEnabled(false);
        userRepository1.save(user);
        
        Users _user = new Users("admin", "admin@beehyv.com");
        userRepository.save(_user);
    }

    @Transactional
    public String loginUser(UserCredentials user) {
        Optional<UserCredentials> tempUser = userRepository1.findByEmail(user.getEmail());
        if(!tempUser.isPresent()) {
            throw new IllegalStateException("Incorrect email entered.");
        }
        
        String tempPassword = tempUser.get().getPassword();
        String decodedPassword = new String(Base64.getDecoder().decode(tempPassword));
        boolean isPasswordMatched = decodedPassword.equals(user.getPassword());
        if(!isPasswordMatched) {
            throw new IllegalStateException("Incorrect password entered.");
        }
        
        System.out.printf("%nUser Logged IN:: User Email = %s%n%n", user.getEmail());
        
        tempUser.get().setIsEnabled(true);
        return "{ result : Success }";
    }

    @Transactional
    public String signUpUser(UserCredentials user) {
        boolean isUserExists = userRepository1.findByEmail(user.getEmail()).isPresent();
        if(isUserExists) {
            throw new IllegalStateException("Email already taken.");
        }

        String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(encodedPassword);
        user.setIsEnabled(false);
        userRepository1.save(user);
        
        System.out.printf("%n%s:: New User Signed Up%n%n", user.getName());
        Users _user = new Users(user.getName(), user.getEmail());
        userRepository.save(_user);
        
        return "{ " + userRepository.findAddedUserID() + ": User ID }";
    }
    
    @Transactional
    public String logoutUser(UserCredentials user) {
        boolean userExist = userRepository1.existsById(user.getUserID());
        if(!userExist) {
            throw new IllegalStateException("Incorrect User ID");
        }

        System.out.printf("%nUser ID = %d:: Logged OUT%n%n", user.getUserID());
        UserCredentials tempUser = userRepository1.getById(user.getUserID());
        tempUser.setIsEnabled(false);
        return "{ 'result':'success'} ";
    }

    public Users getProfileById(Long id) {
        Users tempUser = userRepository.findById(id).get();
        UserCredentials user = userRepository1.getById(id);
        if(!user.isEnabled()) {
            throw new IllegalStateException("You have logged in with different user id.");
        }

        System.out.printf("%nUser ID = %d:: Fetch User profile%n%n", tempUser.getUserID());
        return tempUser;
    }

    @Transactional
    public String updateProfile(Users user) {
        Users tempUser = userRepository.findById(user.getUserID()).get();
        UserCredentials _user = userRepository1.getById(user.getUserID());
        
        if(tempUser == null) {
            throw new IllegalStateException("User ID = " + user.getUserID() + ", Not Found");
        }

        if(!_user.isEnabled()) {
            throw new IllegalStateException("User ID = " + user.getUserID() + ", Not logged in. First logged in to your account, then update.");
        }
        
        System.out.printf("%nUpdate details of User:: User ID = %d%n%n", tempUser.getUserID());
        tempUser.setName(user.getName());
        tempUser.setEmail(user.getEmail());
        tempUser.setPhone(user.getPhone());
        tempUser.setAddress(user.getAddress());
        
        _user.setName(user.getName());
        _user.setEmail(user.getEmail());
        return "{ 'result':'success' }";
    }
}
