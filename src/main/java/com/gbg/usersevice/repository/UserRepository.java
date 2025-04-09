package com.gbg.usersevice.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbg.usersevice.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.fullName LIKE %:name%")
    List<User> findByPartialName(@Param("name") String name);

	List<User> findByPhoneNumber(Long phoneNumber);

	Optional<User> findByEmail(String email);

	Optional<User> findByVerificationToken(String token);

	Optional<User> findByPasswordVerificationToken(String token);
	//Optional<User> findByPasswordVerificationToken(String token);


}
