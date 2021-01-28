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


import com.hrms.model.Leave;
import com.hrms.model.MenuModule;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;


@Controller
public class LeaveMasterController {
	int pageno=11;
	String reqPage="/leaveMaster";
	@Autowired
	LeaveService leaveService;
	@Autowired
	ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;

	/**
	 * 
	 * Request mapping LeaveMaster list data 
	 * @param model
	 * @param session
	 * @return
	 */
@GetMapping("/leaveMaster")
public String LeaveMaster(Model model,HttpSession session) {
	
	List<Leave> listLeave = leaveService.getAllLeaves();
	model.addAttribute("listLeave", listLeave);
	String userCode= (String)session.getAttribute("username");
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
	session.setAttribute("username",session.getAttribute("username"));
	 return pageMappingService.PageRequestMapping(reqPage,pageno);
	}

/**
 * Request Mapping save Leave Master
 * @param holiday
 * @param model
 * @param session
 * @return
 */
@PostMapping("/saveLeave")
	public String SaveLeave(@ModelAttribute("leave") Leave leave, Model model) {
		if (leave.getLev_code() != "") {
			leaveService.addLeave(leave);
			
			List<Leave> listLeave = leaveService.getAllLeaves();
			model.addAttribute("listLeave", listLeave);
		} 
		  return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}	

/**
 * Request Mapping fetching  Id Base edit Leave Data 
 * @param id
 * @param model
 * @param session
 * @return
 */
  @GetMapping(value = {"/editLeave/{id}"})
  public String editleave(@PathVariable("id")String id,  Model model,HttpSession session)
   { 
	  int editPageNo=12;
		String reqPageedit="/editLeave";
	   Leave leaveEdit = leaveService.findLeaveById(id);
	  model.addAttribute("leaveEdit", leaveEdit);

      session.setAttribute("username",session.getAttribute("username")); 
      return pageMappingService.PageRequestMapping(reqPageedit,editPageNo);
  }
  /**
	 * Request Mapping Update Leave data 
	 * @param 
	 * @param model
	 * @return
	 */
  @PostMapping("/updateLeave")
  public String updateLeave(@ModelAttribute("leaveupdate") Leave d, Model model) {
 
	  this.leaveService.updateLeave(d);
    	  
	  return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);

  }
  /**
	 * 
	 * @param  Request Mapping  Delete By Id Leave Data
	 * @param model
	 * @param session
	 * @return
	 */
  @GetMapping(value = {"/deleteLeave/{id}"})
  public String deleteleave(@PathVariable("id")String id,  Model model,HttpSession session)
   { 
	
	  
	  this.leaveService.removeLeave(id);

      session.setAttribute("username",session.getAttribute("username")); 
      return "redirect:/"+pageMappingService.PageRequestMapping(reqPage,pageno);

  }


}
