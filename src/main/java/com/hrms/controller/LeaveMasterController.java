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


@Controller
public class LeaveMasterController {

	@Autowired
	LeaveService leaveService;
	@Autowired
	ModuleService moduleService;
	
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
		return "LeaveMaster";
	}


@PostMapping("/saveLeave")
	public String SaveLeave(@ModelAttribute("leave") Leave leave, Model model) {
		if (leave.getLev_code() != "") {
			leaveService.addLeave(leave);
			
			List<Leave> listLeave = leaveService.getAllLeaves();
			model.addAttribute("listLeave", listLeave);
		} 
		return "redirect:/leaveMaster";

	}	


  @GetMapping(value = {"/editLeave/{id}"})
  public String editleave(@PathVariable("id")String id,  Model model,HttpSession session)
   { 
	   Leave leaveEdit = leaveService.findLeaveById(id);
	  model.addAttribute("leaveEdit", leaveEdit);

      session.setAttribute("username",session.getAttribute("username")); 
         return "/editLeave"; 
  }
  
  @PostMapping("/updateLeave")
  public String updateLeave(@ModelAttribute("leaveupdate") Leave d, Model model) {
 
	  this.leaveService.updateLeave(d);
    	  
	  return "redirect:/leaveMaster";
  }
  
  @GetMapping(value = {"/deleteLeave/{id}"})
  public String deleteleave(@PathVariable("id")String id,  Model model,HttpSession session)
   { 
	
	  
	  this.leaveService.removeLeave(id);

      session.setAttribute("username",session.getAttribute("username")); 
      return "redirect:/leaveMaster";
  }


}
