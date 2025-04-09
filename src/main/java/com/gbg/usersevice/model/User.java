package com.gbg.usersevice.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private long phoneNumber;
	@Column(unique = true)
	private String email;
	private String password;
	
	private String fullName;
	
	private String roles;
	
    private boolean enabled = false;
    private String verificationToken;
    private LocalDateTime tokenExpiryDate;
    
    private String passwordVerificationToken;
    private LocalDateTime passwordTokenExpiryDate;
    
    
    
    private LocalDateTime singupDateAndTime;
    
    private LocalDateTime lastLoginDateTime;
    
    private String lastLoginIp;         // To store the user's IP address
    private String lastLoginLocation; 
	
	
	

}
