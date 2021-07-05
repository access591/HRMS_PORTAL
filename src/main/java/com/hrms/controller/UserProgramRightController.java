package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.State;
import com.hrms.model.SubModule;
import com.hrms.model.UserEntity;
import com.hrms.model.UserRights;
import com.hrms.service.ModuleService;
import com.hrms.service.ProgramService;
import com.hrms.service.SubModuleService;
import com.hrms.service.UserProgramRightService;
import com.hrms.service.UserService;
@Controller
public class UserProgramRightController {
	
	@Autowired
	UserService userService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	SubModuleService subModuleService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	UserProgramRightService  userProgramRightService  ;
	
	
@GetMapping("/userProgramRights")
	public String userProgramRightsList(Model model,HttpSession session) {
	String userCode= (String)session.getAttribute("username");	
	
	
	if (userCode!=null) {
		List<UserRights> listUserRights = userProgramRightService.getAllUserRights();
		model.addAttribute("listUserRight", listUserRights);
		
		List<UserEntity> listUsers = userService.getAllUsers();
		model.addAttribute("listUsers", listUsers);
		
		List<Module> modulesList = moduleService.getActiveModules();
		model.addAttribute("modulesList", modulesList);
		
		List<SubModule> subModulesList = subModuleService.getActiveSubModules();
		model.addAttribute("subModulesList", subModulesList);
		
		List<Program> programsList = programService.getActivePrograms();
		model.addAttribute("programsList", programsList);
		
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "UserProgramRight";
	}
	else
	{
		 return "redirect:" + "./";	
	}
		}

@PostMapping("/saveUserRights")
public String userProgramRightSave(@ModelAttribute("UserRights") UserRights userRights, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	if(session.getAttribute("username")==null) {
		return "redirect:" + "./";
	}
	boolean isUserRightsExist = userProgramRightService.checkUserRightsExists(userRights);
	
	if (isUserRightsExist) {
	    redirectAttributes.addFlashAttribute("message", "Program  Already exists !  ");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	    return"redirect:/userProgramRights";
	
	}
    else {
	userProgramRightService.addUserProgramRight(userRights); 
	session.setAttribute("username",session.getAttribute("username"));
    }
return"redirect:/userProgramRights";

	}

@GetMapping(value = {"/editUserRights/{id}"})
public String editUserRights(@PathVariable("id")long id,  Model model,HttpSession session)
 { 
	if(session.getAttribute("username")==null) {
		return "redirect:" + "./";
	}
	List<UserEntity> listUsers = userService.getAllUsers();
	model.addAttribute("listUsers", listUsers);	
	
	List<Module> modulesList = moduleService.getActiveModules();
	model.addAttribute("modulesList", modulesList);
	session.setAttribute("imgUtil", new ImageUtil());
	List<SubModule>subModulesList=subModuleService.getActiveSubModules();
	model.addAttribute("subModulesList", subModulesList);
	
	List<Program> programsList = programService.getActivePrograms();
	model.addAttribute("programsList", programsList);
	
	
	  UserRights userRightsEdit = userProgramRightService.findUserRightById(id);
	  model.addAttribute("userRightsEdit", userRightsEdit);

    session.setAttribute("username",session.getAttribute("username")); 
       return "/editUserRights"; 
}

@PostMapping("/updateUserRights")
public String upadteUserRight(@ModelAttribute("UserRights") UserRights ur, Model model,HttpSession session)
{      if(session.getAttribute("username")==null) {
	      return "redirect:" + "./";
         }
	  this.userProgramRightService.updateUserRights(ur);
	  return"redirect:/userProgramRights";
	
}

@GetMapping(value = {"/deleteUserRights/{id}"})
public String deleteUserRights(@PathVariable("id")Long id,  Model model,HttpSession session)
 {  if(session.getAttribute("username")==null) {
     return "redirect:" + "./";
 }
	  this.userProgramRightService.removeUserProgramRight(id);
    session.setAttribute("username",session.getAttribute("username")); 
    return"redirect:/userProgramRights";
}

@ResponseBody
@GetMapping("/viewModuleBySubModule/{id}")
public List<SubModule> viewModuleBySubModule(@PathVariable(value = "id") String id, Model model, HttpSession session) {
	List<SubModule> e = subModuleService.findSubModuleByModuleCode(id);
	List<SubModule> lisSubModuleUtil = new ArrayList<>();
	for (int i = 0; i < e.size(); i++) {
		SubModule st = new SubModule();
		st.setSubModuleCode(e.get(i).getSubModuleCode());
		st.setSubModuleName(e.get(i).getSubModuleName());
		
		lisSubModuleUtil.add(st);
	}
	return lisSubModuleUtil;

}
@ResponseBody
@GetMapping("/viewSubModuleByProgramCode/{id}")
public List<Program> viewSubModuleByProgramCode(@PathVariable(value = "id") String id, Model model, HttpSession session) {
	List<Program> e = programService.findByProgramCodeSubModule(id);
	List<Program> lisSubModuleUtil = new ArrayList<>();
	for (int i = 0; i < e.size(); i++) {
		Program st = new Program();
		st.setProgramCode(e.get(i).getProgramCode());
		st.setProgramName(e.get(i).getProgramName());
		
	
		lisSubModuleUtil.add(st);
	}
	return lisSubModuleUtil;

}



}
