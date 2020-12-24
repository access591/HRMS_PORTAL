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

import com.hrms.model.Bank;
import com.hrms.service.BankService;


@Controller
public class BankController {

	@Autowired
	BankService bankService;
	
	
@GetMapping("/bankMaster")
public String bankMaster(Model model,HttpSession session) {
	
	List<Bank> listBank = bankService.getAllBanks();
	model.addAttribute("listBank", listBank);
	session.setAttribute("username",session.getAttribute("username"));
		return "BankMaster";
	}


@PostMapping("/saveBank")
	public String SaveBank(@ModelAttribute("bank") Bank bank, Model model) {
		if (bank.getBank_Code() != "") {
			bankService.addBank(bank);
			
			List<Bank> listBank = bankService.getAllBanks();
			model.addAttribute("listBank", listBank);
		} 
		return "redirect:/bankMaster";

	}	

@GetMapping(value = {"/editBank/{id}"})
public String editbank(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  
	Bank bankEdit = bankService.findBankById(id);
	  model.addAttribute("bankEdit", bankEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return "/editBank"; 
}

@PostMapping("/updateBank")
public String updateBank(@ModelAttribute("bankupdate") Bank d, Model model) {

	  this.bankService.updateBank(d);
  	  
	  return "redirect:/bankMaster";
}

@GetMapping(value = {"/deleteBank/{id}"})
public String deletebank(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	
	  
	  this.bankService.removeBank(id);

    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/bankMaster";
}


	


}
