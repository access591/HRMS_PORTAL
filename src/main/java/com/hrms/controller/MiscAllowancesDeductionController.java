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

import com.hrms.model.Grade;
import com.hrms.model.MenuModule;
import com.hrms.model.MiscAllowance;
import com.hrms.service.MiscAllowanceDeductionService;
import com.hrms.service.ModuleService;

@Controller
public class MiscAllowancesDeductionController 
{
	@Autowired
	MiscAllowanceDeductionService miscAllowanceDeductionService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 
	 * Request mapping MiscAllowanceDeduction  list data
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/miscAllowances")
public String MiscAllowances(Model model,HttpSession session) 
{
	 List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
      model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction); 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
  	if (modules != null) {
  		model.addAttribute("modules", modules);
  	}
      session.setAttribute("username",session.getAttribute("username"));
       return "miscAllowanceDeduction"; 
}
/**
 * Request Mapping save AllowncesDeduction
 * 
 * @param holiday
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveAllowncesDeduction")
 public String SaveAllowncesDeduction(@ModelAttribute("miscAllowance") MiscAllowance miscAllowance, Model model,HttpSession session)
 {
	if (miscAllowance.getAllowance_Code()!= "")
	{
        miscAllowanceDeductionService.addMiscAllowanceDeduction(miscAllowance);
         List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
         model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction); 
         session.setAttribute("username",session.getAttribute("username"));
	} 
	
	return"redirect:/miscAllowances";
 
 }
/**
 * Request Mapping fetching Id Base AllowanceDeduction  Loan data
 * 
 * @param id
 * @param model
 * @param session
 * @return
 */

@GetMapping(value = {"/editAllowanceDeduction/{id}"})
public String editAllowncesDeduction(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	MiscAllowance editMiscAllowance = miscAllowanceDeductionService.findMiscAllowanceDeductionById(id);
	  model.addAttribute("editMiscAllowance", editMiscAllowance);

    session.setAttribute("username",session.getAttribute("username")); 
       return "/editAllowanceDeduction"; 
}
/**
 * 
 * @param Request Mapping update AllowanceDeduction Data
 * @param model
 * @param session
 * @return
 */
@PostMapping("/updateAllowanceDeduction")
public String updateAllowncesDeduction(@ModelAttribute("miscAllowanceUpdate") MiscAllowance M, Model model) {

	  this.miscAllowanceDeductionService.updateMiscAllowanceDeduction(M);
  	  
	  return "redirect:/miscAllowances";
}
/**
 * 
 * @param Request Mapping Delete By Id AllowanceDeduction Data
 * @param model
 * @param session
 * @return
 */
@GetMapping(value = {"/deleteAllowanceDeduction/{id}"})
public String deleteAllowncesDeduction(@PathVariable("id")String id,  Model model,HttpSession session)
 { 
	  this.miscAllowanceDeductionService.removeMiscAllowanceDeduction(id);
    session.setAttribute("username",session.getAttribute("username")); 
    return"redirect:/miscAllowances";
}




}
