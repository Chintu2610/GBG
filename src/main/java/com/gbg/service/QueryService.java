package com.gbg.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gbg.entity.Inquiry;
import com.gbg.usersevice.model.InquiryRequest;
import com.gbg.usersevice.repository.InquiryRepo;
import com.gbg.usersevice.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QueryService {
	@Autowired
	InquiryRepo inquiryRepo;
	
	@Autowired
	EmailService emailService;
	public ResponseEntity<Void> saveQuery(InquiryRequest inquiryRequest) {
		if(inquiryRequest==null)
		{
			log.error("Received null InquiryRequest. Skipping save operation.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Inquiry inquiry =inquiryRepo.save(Inquiry.builder()
		        .fullname(inquiryRequest.getFullname())
		        .mobile(inquiryRequest.getMobile())
		        .email(inquiryRequest.getEmail())
		        .commodity(inquiryRequest.getCommodity())
		        .packing(inquiryRequest.getPacking())
		        .delivery(inquiryRequest.getDelivery())
		        .price(inquiryRequest.getPrice())
		        .payment(inquiryRequest.getPayment())
		        .quantity(inquiryRequest.getQuantity())
		        .conditions(inquiryRequest.getConditions())
		        .build());
		log.info("Inquiry saved successfully for ", inquiryRequest.getFullname());
		emailService.sendInquiryEmail(inquiry);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
