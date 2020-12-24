package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.hrms.model.Loan;
import com.hrms.service.LoanMaterService;

@Controller
public class LoanController {
	@Autowired
	LoanMaterService loanMaterService;
	
	@GetMapping("/loanMaster")
	public String LoanMaster(Model model,HttpSession session) {
		
	 List<Loan>listLoan = loanMaterService.getAllLoans();
       model.addAttribute("listLoan", listLoan); 
	  session.setAttribute("username",session.getAttribute("username"));
			return "/loanMaster";
		}
	@PostMapping("/saveLoan")
	  public String SaveLoan(@ModelAttribute("loan") Loan loan, Model model,HttpSession session) {
			if (loan.getLoan_Code() != "") {
				loanMaterService.addLoan(loan); 
				List<Loan>listLoan = loanMaterService.getAllLoans();
				 model.addAttribute("listLoan", listLoan); 
				session.setAttribute("username",session.getAttribute("username"));
	 
	  }
		return"redirect:/loanMaster";
	  
	  }
	

	
	@GetMapping(value = {"/editLoan/{id}"})
	public String editLoan(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		Loan loanEdit = loanMaterService.findLoanById(id);
		  model.addAttribute("loanEdit", loanEdit);

	    session.setAttribute("username",session.getAttribute("username")); 
	       return "/editLoan"; 
	}
	@PostMapping("/updateLoan")
	public String updateLoan(@ModelAttribute("loanupdate") Loan L, Model model) {

		  this.loanMaterService.updateLoan(L);
	  	  
		  return "redirect:/loanMaster";
	}
	@GetMapping(value = {"/deleteLoan/{id}"})
	public String deleteLoan(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		  this.loanMaterService.removeLoan(id);
	    session.setAttribute("username",session.getAttribute("username")); 
	    return "redirect:/loanMaster";
	}
	
	

}
