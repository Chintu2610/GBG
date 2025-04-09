package com.gbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.gbg.service.QueryService;
import com.gbg.usersevice.model.AuthRequest;
import com.gbg.usersevice.model.InquiryRequest;
import com.gbg.usersevice.model.User;
import com.gbg.usersevice.service.EmailService;
import com.gbg.usersevice.service.JwtService;
import com.gbg.usersevice.service.UserInfoService;
import com.gbg.usersevice.service.UserInfoServiceInterface;

@RestController
public class Controller {
	@Autowired
	QueryService queryService;
	@Autowired
	UserInfoServiceInterface userinfoservice;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	EmailService emailService;
	@GetMapping("/test")
	public ResponseEntity<String>  test()
	{
		return new ResponseEntity<>("its running..",HttpStatusCode.valueOf(200));
	}
	@PostMapping("/query/save")
	public ResponseEntity<Void>  saveQuery(@RequestBody InquiryRequest inquiryRequest)
	{
		return queryService.saveQuery(inquiryRequest);
		
	}
	@PostMapping("/signUp")
    public ResponseEntity<User> addUser(@RequestBody User user) {
		
		String fname = user.getFirstName();
		String lname = user.getLastName();
		
		String fullname = fname+" "+lname;
		
		user.setFullName(fullname);
				
        User savedUser = userinfoservice.addUser(user);
        
        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), user.getVerificationToken());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
	 @PostMapping("/login")
	    public ResponseEntity<String> loginUser(@RequestBody AuthRequest authRequest) {
	        try {
	            // Check if email exists and is verified
	            if (!userinfoservice.isUserExists(authRequest.getEmail())) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email doesn't exist");
	            }

	            Authentication authenticate = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
	            );

	            if (authenticate.isAuthenticated()) {
	                if (!userinfoservice.isUserVerified(authRequest.getEmail())) {
	                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email not verified");
	                }
	                String token = jwtService.generateToken(authRequest.getEmail());
	                return ResponseEntity.ok(token);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
	            }
	        } catch (UsernameNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email doesn't exist");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
	        }
	    }
	    @GetMapping("/verify")
	    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
	        try {
	            userinfoservice.verifyEmail(token);
	            return ResponseEntity.ok("Email verified successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body("Invalid or expired token");
	        }
	    }
	    @GetMapping("/getusers")
	    public ResponseEntity<String> getUsers() {
	       return  ResponseEntity.ok("no ofusers is 10");
	    }
}
