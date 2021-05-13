package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterviewController {
	
	@GetMapping("interviewDetails")
	public String interviewDetails() {
		
		System.out.println("hii");
		return "interviewDetails"; //interviewDetails.html
	}
	
	
	@GetMapping("interviewApproval")
	public String interviewApproval() {
		
		return "interviewApproval";
	}
	
	
	@GetMapping("interviewFinalSelection")
	public String interviewFinalSelection() {
		
		return "interviewFinalSelection";  //interviewFinalSelection.html
	}

}
