package com.hrms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;
import com.hrms.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		return "sign-in";
	}

	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute("user") Login login, Model model) {
		boolean isUserExist = userService.checkUserExists(login);
		if (isUserExist) {
			List<UserEntity> list = userService.getAllUsers();
			model.addAttribute("users", list);
			return "list-users";
		} else {
			return "sign-up";
		}
	}

}
