package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {
	
	@GetMapping("budgetprovisionpage")
	public String budgetProvisionPage() {
		
		return "budgetProvision";
		
	}
	
	@GetMapping("orderissuetracking")
	public String orderIssueTrackingPage() {
		
		return "orderIssueTracking";
		
	}

}
