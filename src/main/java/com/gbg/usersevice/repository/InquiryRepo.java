package com.gbg.usersevice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbg.entity.Inquiry;

public interface InquiryRepo extends JpaRepository<Inquiry, Integer>{
	
}
