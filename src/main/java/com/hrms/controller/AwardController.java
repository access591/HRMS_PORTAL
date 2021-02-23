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
		String insertedBY = (String) session.getAttribute("userlogin");
		award.setIns_by(insertedBY);
		AwardService.addAward(award);
		List<Award> listAward = AwardService.getAllAwards();
		model.addAttribute("listAward", listAward);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/awardMaster";

	}
	
	@GetMapping(value = { "/editAward/{id}" })
	public String editAward(@PathVariable("id") long id, Model model, HttpSession session) {

		Award awardEdit = AwardService.findAwardById(id);
		model.addAttribute("awardEdit", awardEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return "/editAward";
	}

	@PostMapping("/updateAward")
	public String updateAward(@ModelAttribute("city") Award a, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		a.setUpd_by(updatedBY);
		this.AwardService.updateAward(a);

		return "redirect:/awardMaster";
	}

	@GetMapping(value = { "/deleteAward/{id}" })
	public String deleteAward(@PathVariable("id") long id, Model model, HttpSession session) {
		this.AwardService.removeAward(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/awardMaster";
	}

}
