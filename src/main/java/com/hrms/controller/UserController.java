package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.model.Login;
import com.hrms.model.Module;
import com.hrms.service.ModuleService;
import com.hrms.service.ReCaptchaValidationService;
import com.hrms.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ReCaptchaValidationService validator;

	@GetMapping("/")
	public String index(Model model) {
		return "sign-in";
	}

	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute("user") Login login, Model model,
			@RequestParam(name = "g-recaptcha-response") String captcha) {
		boolean isUserExist = userService.checkUserExists(login);
		if (isUserExist /* && validator.validateCaptcha(captcha) */ ) {
			List<Module> modules = moduleService.getAllModules();
			model.addAttribute("modules", modules);
			return "dashboard";
		} else {
			model.addAttribute("message", "Please Verify Captcha");
			return "sign-in";
		}
	}

}
