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

import com.hrms.ImageUtil;
import com.hrms.model.Bank;
import com.hrms.model.MenuModule;
import com.hrms.service.BankService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;


@Controller
public class BankController {
	int pageno=7;
	String reqPage="/bankMaster";
	@Autowired
	BankService bankService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
/**
 * Method to get Bank Result 	
 * @param model
 * @param session
 * @return
 */
@GetMapping("/bankMaster")
public String bankMaster(Model model,HttpSession session) {
	
	List<Bank> listBank = bankService.getAllBanks();
	model.addAttribute("listBank", listBank);
	String userCode= (String)session.getAttribute("username");
	if (session.getAttribute("username") == null) {
		return "redirect:" + "./";
	}
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
	session.setAttribute("imgUtil", new ImageUtil());
	session.setAttribute("username",session.getAttribute("username"));
	return pageMappingService.PageRequestMapping(reqPage,pageno);
	}

/**
 * Method to Save Bank Details
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveBank")
	public String saveBank(@ModelAttribute("bank") Bank bank, Model model) {
			bankService.addBank(bank);
			List<Bank> listBank = bankService.getAllBanks();
			model.addAttribute("listBank", listBank);
		
		   return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
		   	
	}	
/**
 * Method to Edit Bank Details
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/editBank/{id}"})
public String editbank(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	int editPageNo=8;
	String reqPageedit="/editBank";
	  
	Bank bankEdit = bankService.findBankById(id);
	  model.addAttribute("bankEdit", bankEdit);

    session.setAttribute("username",session.getAttribute("username")); 
    return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
}
/**
 * Method to Update Bank Details
 * @param model
 * @param session
 * @return
 */
@PostMapping("/updateBank")
public String updateBank(@ModelAttribute("bankupdate") Bank d, Model model) {

	  this.bankService.updateBank(d);
  	  
	  return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}
/**
 * Method to Delete Bank Details
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/deleteBank/{id}"})
public String deletebank(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	
	  
	  this.bankService.removeBank(id);

    session.setAttribute("username",session.getAttribute("username")); 
    return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
}


	


}
