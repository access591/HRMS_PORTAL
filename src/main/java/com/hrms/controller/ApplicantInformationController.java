package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApplicantInformationController {

	@GetMapping("applicantInformation")
	public String applicantInformationPage(Model model) {
		
		
		return "applicantInformation";  //applicantInformation.html
	}
}
