package com.hrms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StateController {
@GetMapping("/stateMaster")
public String stateMaster(Model model,HttpSession session)
	{
		
		return "stateMaster";
	}
}

