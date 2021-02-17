package com.hrms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountryController {
@GetMapping	("/countryMaster")
public String countryMaster(Model model,HttpSession session)
{
	
	return "countryMaster";
}
}
