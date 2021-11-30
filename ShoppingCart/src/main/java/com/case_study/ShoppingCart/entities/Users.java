package com.case_study.ShoppingCart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
	@SequenceGenerator(
            name = "sequence1",
            sequenceName = "sequence1",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence1"
    )
    private long userID;
    private String name;
    @Column(unique = true)
    private  String email;
    private String phone;
    @Embedded
    private Address address;
    
    public Users(String name, String email) {
    	this.name = name;
    	this.email = email;
    }
}