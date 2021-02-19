package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Award;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.AwardService;
@Controller
public class AwardController {
	
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private  AwardService AwardService;
	@GetMapping("/awardMaster")
	public String awardMaster(Model model, HttpSession session) {
		List<Award> listAward = AwardService.getAllAwards();
		model.addAttribute("listAward", listAward);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		session.setAttribute("username", session.getAttribute("username"));

		return "awardMaster";
	}
	
	@PostMapping("/saveAward")
	public String saveAward(@ModelAttribute("award")Award award, Model model, HttpSession session) {

		AwardService.addAward(award);
		List<Award> listAward = AwardService.getAllAwards();
		model.addAttribute("listAward", listAward);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/awardMaster";

	}
	
}
