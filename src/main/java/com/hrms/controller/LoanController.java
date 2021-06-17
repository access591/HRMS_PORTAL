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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.Loan;
import com.hrms.model.MenuModule;
import com.hrms.service.LoanMaterService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class LoanController {
	int pageno=13;
	String reqPage="/loanMaster";
	@Autowired
	LoanMaterService loanMaterService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * 
	 * Request mapping  Loan list data
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/loanMaster")
	public String loanMaster(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<Loan> listLoan = loanMaterService.getAllLoans();
		model.addAttribute("listLoan", listLoan);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		 return pageMappingService.PageRequestMapping(reqPage,pageno);
	}

	/**
	 * Request Mapping save Loan Master
	 * 
	 * @param holiday
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveLoan")
	public String saveLoan(@ModelAttribute("loan") Loan loan, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
		boolean isloanExist = loanMaterService.checkLoanExists(loan);	
		if (isloanExist) {
		    redirectAttributes.addFlashAttribute("message", "Loan Name Already exists !  ");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		    return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}
	else 
	{
			loanMaterService.addLoan(loan);
			List<Loan> listLoan = loanMaterService.getAllLoans();
			model.addAttribute("listLoan", listLoan);
			session.setAttribute("username", session.getAttribute("username"));
	}
	
		return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);
	

	}

	/**
	 * Request Mapping fetching Id Base edit Loan data
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */

	@GetMapping(value = { "/editLoan/{id}" })
	public String editLoan(@PathVariable("id") String id, Model model, HttpSession session) {
		 int editPageNo=14;
			String reqPageedit="/editLoan";
		Loan loanEdit = loanMaterService.findLoanById(id);
		model.addAttribute("loanEdit", loanEdit);

		session.setAttribute("username", session.getAttribute("username"));
		 return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
	}

	/**
	 * Request Mapping Update Loan data
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@PostMapping("/updateLoan")
	public String updateLoan(@ModelAttribute("loanupdate") Loan loan, Model model) {

		this.loanMaterService.updateLoan(loan);

		return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}

	/**
	 * 
	 * @param Request Mapping Delete By Id Loan Data
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteLoan/{id}" })
	public String deleteLoan(@PathVariable("id") String id, Model model, HttpSession session) {
		this.loanMaterService.removeLoan(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}

}
