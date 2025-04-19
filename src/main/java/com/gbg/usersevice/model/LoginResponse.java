package com.gbg.usersevice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private String email;
	private String token;
	private String message;
	public LoginResponse(String message)
	{
		this.message=message;
	}
}
