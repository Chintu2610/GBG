package com.gbg.usersevice.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gbg.usersevice.model.User;
import com.gbg.usersevice.repository.UserRepository;




@Service
public class UserInfoService implements UserInfoServiceInterface, UserDetailsService{
	
	@Autowired
	UserRepository userrepository;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 EmailService emailService;
	 
	 @Autowired
	 private JavaMailSender mailSender;
	
	 @Override
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	     Optional<User> userInfo = userrepository.findByEmail(email);
	     userInfo.ifPresent(user -> {
	         System.out.println("Loaded user roles: " + user.getRoles());
	     });
	     return userInfo.map(UserInfoDetails::new)
	             .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
	 }


    @Override
    public User addUser(User user) {
        String token = UUID.randomUUID().toString();
        // Combine first name and last name to set full name

        user.setFullName(user.getFullName());

        // Set user roles (assuming roles are set as "USER")
        user.setRoles("ROLE_USER");
        
        // Get the current date and time in the Indian time zone
        ZonedDateTime indiaTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

        // Convert ZonedDateTime to LocalDateTime if needed
        LocalDateTime localDateTime = indiaTime.toLocalDateTime();

        // Set the signup date and time with the local Indian time
        user.setSingupDateAndTime(localDateTime);
        // Save user and send verification email
        
        user.setVerificationToken(token);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userrepository.save(user);
        
        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), token,user.getFirstName());
        return savedUser;
    }

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		 List<User> users = userrepository.findAll();
		 
		 return users;
	}

	@Override
	public Optional<User> getUserById(Long id) {
		// TODO Auto-generated method stub
		return userrepository.findById(id);
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return userrepository.findByPartialName(name);
	}

	@Override
	public List<User> getUsersByPhoneNumber(Long phoneNumber) {
		// TODO Auto-generated method stub
		return userrepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public Optional<User> getUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return userrepository.findByEmail(email);
	}
	
	
	@Override
	 public void verifyEmail(String token) {
	        Optional<User> userOptional = userrepository.findByVerificationToken(token);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            user.setEnabled(true);
	            user.setVerificationToken(null);
	            userrepository.save(user);
	        } else {
	            throw new RuntimeException("Invalid verification token");
	        }
	    }



	
	
	@Override
	public boolean isUserVerified(String email) {
	    Optional<User> user = userrepository.findByEmail(email);
	    return user.map(User::isEnabled).orElse(false);
	}


	@Override
	public boolean isUserExists(String email) {
		// TODO Auto-generated method stub
		return userrepository.findByEmail(email).isPresent();
	}

	
	 public void sendPasswordResetLink(String email) {
	        // Find user by email
	        Optional<User> optionalUser = userrepository.findByEmail(email);
	        if (!optionalUser.isPresent()) {
	            throw new UsernameNotFoundException("User not found");
	        }

	        User user = optionalUser.get();  // Get the actual User object

	        // Generate reset token
	        String resetToken = UUID.randomUUID().toString();
	        user.setPasswordVerificationToken(resetToken);
	        user.setPasswordTokenExpiryDate(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
	        userrepository.save(user);

	        // Construct reset link
	        String resetLink = "https://tradabulls.com/reset-password?token=" + resetToken;
	        //String resetLink = "http://localhost:3000/reset-password?token=" + resetToken;

	        // Create email content
	        String subject = "Password Reset Request";
	        String content = "Dear " + user.getFirstName() + ",\n\n"
	                + "To reset your password, please click the link below:\n"
	                + resetLink + "\n\n"
	                + "If you did not request a password reset, please ignore this email.\n\n"
	                + "Regards,\n"
	                + "Tradabulls";

	        // Send email
	        sendEmail(user.getEmail(), subject, content);
	    }

	    private void sendEmail(String recipientEmail, String subject, String content) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(recipientEmail);
	        message.setSubject(subject);
	        message.setText(content);
	        mailSender.send(message);
	    }
	
	    public void resetPassword(String token, String newPassword) {
	        try {
	            Optional<User> optionalUser = userrepository.findByPasswordVerificationToken(token);
	            
	            if (!optionalUser.isPresent()) {
	                throw new RuntimeException("Invalid or expired token");
	            }

	            User user = optionalUser.get();  // Get the actual User object

	            // Update password
	            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
	            user.setPasswordVerificationToken(null);
	            user.setPasswordTokenExpiryDate(null);
	            userrepository.save(user);
	        } catch (Exception e) {
	            e.printStackTrace();  // Log the exception
	            throw new RuntimeException("An error occurred while resetting the password");
	        }
	    }


		@Override
		public void deleteUserById(Long id) {
			// TODO Auto-generated method stub
			  userrepository.deleteById(id);
		}


		 @Override
		    public Optional<User> updateUser(Long id, User user) {
		        Optional<User> existingUserOpt = userrepository.findById(id);
		        if (existingUserOpt.isPresent()) {
		            User existingUser = existingUserOpt.get();
		            existingUser.setFirstName(user.getFirstName());
		            existingUser.setLastName(user.getLastName());
		            existingUser.setPhoneNumber(user.getPhoneNumber());
		            existingUser.setEmail(user.getEmail());
		            existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // Make sure this is encoded before saving
		            existingUser.setFullName(user.getFullName());
		          //  existingUser.setRoles(user.getRoles());
		           // existingUser.setEnabled(user.isEnabled());
		           // existingUser.setVerificationToken(user.getVerificationToken());
		            //existingUser.setTokenExpiryDate(user.getTokenExpiryDate());
		            //existingUser.setPasswordVerificationToken(user.getPasswordVerificationToken());
		            //existingUser.setPasswordTokenExpiryDate(user.getPasswordTokenExpiryDate());
		            //existingUser.setSingupDateAndTime(user.getSingupDateAndTime());
		            //existingUser.setLastLoginDateTime(user.getLastLoginDateTime());
		            userrepository.save(existingUser);
		            return Optional.of(existingUser);
		        } else {
		            return Optional.empty();
		        }
		    }


	


	
	
	
	
	

}
