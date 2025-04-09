package com.gbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbg.service.QueryService;
import com.gbg.usersevice.model.InquiryRequest;

@RestController
public class Controller {
	@Autowired
	QueryService queryService;
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
}
