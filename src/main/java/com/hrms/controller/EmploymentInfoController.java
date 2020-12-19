package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmploymentInfoController {
	
	@GetMapping("/empInfo")
	public String DesignationMaster(Model model,HttpSession session) {
		
		session.setAttribute("username",session.getAttribute("username"));
			return "empInfoMaster";
		}
	
	

}
