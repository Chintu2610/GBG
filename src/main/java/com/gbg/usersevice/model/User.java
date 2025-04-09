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
	
	
	
	public User() {
		super();
	}



	public User(Long id, String firstName,  String lastName, long phoneNumber, String email,
			String password, String fullName, String roles, boolean enabled, LocalDateTime singupDateAndTime) {
		super();
		this.id = id;
		this.firstName = firstName;
		
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.firstName = fullName;
		this.roles = roles;
		this.enabled = enabled;
		this.singupDateAndTime = singupDateAndTime;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public long getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getRoles() {
		return roles;
	}



	public void setRoles(String roles) {
		this.roles = roles;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public String getVerificationToken() {
		return verificationToken;
	}



	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}



	public LocalDateTime getTokenExpiryDate() {
		return tokenExpiryDate;
	}



	public void setTokenExpiryDate(LocalDateTime tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}



	public String getPasswordVerificationToken() {
		return passwordVerificationToken;
	}



	public void setPasswordVerificationToken(String passwordVerificationToken) {
		this.passwordVerificationToken = passwordVerificationToken;
	}



	public LocalDateTime getPasswordTokenExpiryDate() {
		return passwordTokenExpiryDate;
	}



	public void setPasswordTokenExpiryDate(LocalDateTime passwordTokenExpiryDate) {
		this.passwordTokenExpiryDate = passwordTokenExpiryDate;
	}



	public LocalDateTime getSingupDateAndTime() {
		return singupDateAndTime;
	}



	public void setSingupDateAndTime(LocalDateTime singupDateAndTime) {
		this.singupDateAndTime = singupDateAndTime;
	}



	public LocalDateTime getLastLoginDateTime() {
		return lastLoginDateTime;
	}



	public void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}



	public String getLastLoginIp() {
		return lastLoginIp;
	}



	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}



	public String getLastLoginLocation() {
		return lastLoginLocation;
	}



	public void setLastLoginLocation(String lastLoginLocation) {
		this.lastLoginLocation = lastLoginLocation;
	}
	
	
	

	
	
	

}
