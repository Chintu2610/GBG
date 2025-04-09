package com.gbg.usersevice.model;

import java.util.List;
import java.util.Optional;

public class ApiResponseData {
    private boolean success;
    private String message;
    private String token; // This field is optional
    List<User> users;
    Optional<User> users1;

    public ApiResponseData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponseData(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }

 

	public ApiResponseData(boolean success, String message, List<User> users) {
		super();
		this.success = success;
		this.message = message;
		this.users = users;
	}

	
	public ApiResponseData(boolean success, String message, String token, Optional<User> users1) {
		super();
		this.success = success;
		this.message = message;
		this.token = token;
		this.users1 = users1;
	}

	// Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Optional<User> getUsers1() {
		return users1;
	}

	public void setUsers1(Optional<User> users1) {
		this.users1 = users1;
	}
	
	
    
    
}
