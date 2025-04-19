package com.gbg.usersevice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.gbg.usersevice.model.User;

public interface UserInfoServiceInterface {

	User addUser(User user);
	

	List<User> getAllUsers();

	Optional<User> getUserById(Long id);



	List<User> getUsersByName(String name);



	List<User> getUsersByPhoneNumber(Long phoneNumber);



	Optional<User> getUsersByEmail(String email);



	void verifyEmail(String token);



	boolean isUserVerified(String email);



	boolean isUserExists(String email);




	void sendPasswordResetLink(String email);




	void resetPassword(String token, String password);









	void deleteUserById(Long id);


	 Optional<User> updateUser(Long id, User user);







	

}
