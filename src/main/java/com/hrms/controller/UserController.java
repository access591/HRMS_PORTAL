package com.hrms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.model.Login;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule1;
import com.hrms.model.UserEntity;
import com.hrms.service.ModuleService;
import com.hrms.service.ReCaptchaValidationService;
import com.hrms.service.SubModuleService;
import com.hrms.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private SubModuleService subModuleService;

	@Autowired
	private ReCaptchaValidationService validator;

	@GetMapping("/")
	public String index(Model model) {
		return "sign-in";
	}

	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute("user") Login login, Model model,
			@RequestParam(name = "g-recaptcha-response") String captcha,HttpSession session) {
		boolean isUserExist = userService.checkUserExists(login);
		if (isUserExist /* && validator.validateCaptcha(captcha) */ ) {
			//moduleService.addModule(getModuleObject());
			//moduleService.update(getModuleObject());
			 //subModuleService.add(getSubModuleObject(),"1001");
			
			String id=login.getUserCode();
			UserEntity userRecord = userService.findDataById(id);
			session.setAttribute("uuuuu",userRecord.getUserName());
			session.setAttribute("user_desg",userRecord.getDesgName());
		session.setAttribute("username",login.getUserCode());
		String userCode= (String)session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesss(userCode);
		model.addAttribute("modules", modules);
			return "dashboard";
		} else {
			model.addAttribute("message", "Please Verify Captcha");
			return "sign-in";
		}
	}


	
	private SubModule1 getSubModuleObject() {

		Date date = new Date();

		SubModule1 subModule = new SubModule1();
		subModule.setAcitveSubModule("Y");
		subModule.setInsertedBySubModule("xyz");
		subModule.setInsertedDateSubModule(date);
		Module module = new Module();
		module.setModuleCode("1001");
		subModule.setModuleCode(module);
		subModule.setSeqNoSubModule(2);
		subModule.setSubModuleName("Test_Sub_module");
		subModule.setSubModuleCode("Test_Module");
		subModule.setSubModulePrograms(new ArrayList<Program>());
		subModule.setUpdateBySubModule("Surendra");
		subModule.setUpdatedDateSubModule(date);


		return subModule;
	}

}
