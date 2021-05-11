package com.hrms.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicantInformationController {

	@GetMapping("applicantInformation")
	public String applicantInformationPage(Model model) {
		
		
		return "applicantInformation";  //applicantInformation.html
	}
}
