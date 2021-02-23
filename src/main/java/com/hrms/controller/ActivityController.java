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

import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.ActivityService;
import com.hrms.model.Activities;
@Controller
public class ActivityController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ActivityService activityService;
	@GetMapping("/actMaster")
	public String actMaster(Model model, HttpSession session) {
		List<Activities> listActivities = activityService.getAllActivitys();
		model.addAttribute("listActivities", listActivities);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "actMaster";
	}
	
	@PostMapping("/saveActivity")
	public String saveActivity(@ModelAttribute("activity")Activities act, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		act.setIns_by(insertedBY);
		activityService.addActivity(act);
		List<Activities> listActivities = activityService.getAllActivitys();
		model.addAttribute("listActivities", listActivities);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/actMaster";

	}
	
	@GetMapping(value = { "/editActivity/{id}" })
	public String editActivity(@PathVariable("id") String id, Model model, HttpSession session) {

		Activities activityEdit = activityService.findActivityById(id);
		model.addAttribute("activityEdit", activityEdit);
		session.setAttribute("username", session.getAttribute("username"));
		
		return "/editActivity";
	}
	
	@PostMapping("/updateActivity")
	public String updateActivity(@ModelAttribute("activity")Activities a, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		a.setUpd_by(updatedBY);
		this.activityService.updateActivity(a);
		return "redirect:/actMaster";
	}
	
	@GetMapping(value = { "/deleteActivity/{id}" })
	public String deleteActivity(@PathVariable("id") String id, Model model, HttpSession session) {
		this.activityService.removeActivity(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/actMaster";
	}

}
