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
import com.hrms.model.MenuModule;
import com.hrms.model.MiscAllowance;
import com.hrms.service.MiscAllowanceDeductionService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class MiscAllowancesDeductionController 
{
	int pageno=15;
	String reqPage="/miscAllowances";
	@Autowired
	MiscAllowanceDeductionService miscAllowanceDeductionService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;

	/**
	 * 
	 * Request mapping MiscAllowanceDeduction  list data
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/miscAllowances")
public String miscAllowances(Model model,HttpSession session) 
{
	 List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
      model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction); 
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
  	if (modules != null) {
  		model.addAttribute("modules", modules);
  	}	session.setAttribute("imgUtil", new ImageUtil());
      session.setAttribute("username",session.getAttribute("username"));
      return pageMappingService.PageRequestMapping(reqPage,pageno);

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
 public String saveAllowncesDeduction(@ModelAttribute("miscAllowance") MiscAllowance miscAllowance, Model model,HttpSession session)
 { 
	
	     String insertedBY = (String) session.getAttribute("userlogin");
	     miscAllowance.setInsBy(insertedBY);
        miscAllowanceDeductionService.addMiscAllowanceDeduction(miscAllowance);
         List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionService.getAllMiscAllowanceDeduction();
         model.addAttribute("listMiscAllowanceDeduction", listMiscAllowanceDeduction); 
         session.setAttribute("username",session.getAttribute("username"));
	
	
	 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

 
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
	 int editPageNo=16;
		String reqPageedit="/editAllowanceDeduction";
	MiscAllowance editMiscAllowance = miscAllowanceDeductionService.findMiscAllowanceDeductionById(id);
	  model.addAttribute("editMiscAllowance", editMiscAllowance);
		session.setAttribute("imgUtil", new ImageUtil());
    session.setAttribute("username",session.getAttribute("username")); 
    return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
    
}
/**
 * 
 * @param Request Mapping update AllowanceDeduction Data
 * @param model
 * @param session
 * @return
 */
@PostMapping("/updateAllowanceDeduction")
public String updateAllowncesDeduction(@ModelAttribute("miscAllowanceUpdate") MiscAllowance miscAllowance, Model model,HttpSession session) {

	String updatedBY = (String) session.getAttribute("userlogin");
	miscAllowance.setUpdBy(updatedBY);
	  this.miscAllowanceDeductionService.updateMiscAllowanceDeduction(miscAllowance);

	  return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
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
    return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);
 
}




}
