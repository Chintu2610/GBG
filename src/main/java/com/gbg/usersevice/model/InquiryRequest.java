package com.gbg.usersevice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InquiryRequest {
	 private String fullname;
	    private String mobile;
	    private String email;
	    private String commodity;
	    private String packing;
	    private String delivery;
	    private String price;
	    private String payment;
	    private String quantity;
	    private String conditions;
}
