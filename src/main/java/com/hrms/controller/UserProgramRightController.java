package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
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
	public String UserProgramRightsList(Model model,HttpSession session) {
		
	List<UserRights> listUserRights = userProgramRightService.getAllUserRights();
	model.addAttribute("listUserRights", listUserRights);
	List<UserEntity> listUsers = userService.getAllUsers();
	model.addAttribute("listUsers", listUsers);	
	
	List<Module> modulesList = moduleService.getActiveModules();
	model.addAttribute("modulesList", modulesList);
	
	List<SubModule>subModulesList=subModuleService.getActiveSubModules();
	model.addAttribute("subModulesList", subModulesList);
	
	List<Program> programsList = programService.getActivePrograms();
	model.addAttribute("programsList", programsList);
	
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username",session.getAttribute("username"));
			return "UserProgramRight";
		}


}
