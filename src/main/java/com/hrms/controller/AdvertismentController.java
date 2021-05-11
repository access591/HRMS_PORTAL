package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdvertismentController {

	@GetMapping("/advertisment")
	public String advertismentPage() {
		
		return "Advertisment";
	}
}
