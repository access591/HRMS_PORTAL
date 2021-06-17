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
import com.hrms.service.PageMappingService;
import com.hrms.service.ActivityService;
import com.hrms.ImageUtil;
import com.hrms.model.Activities;
@Controller
public class ActivityController {
	int pageno = 37;
	String reqPage = "/actMaster";
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * Request Mapping Activity Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/actMaster")
	public String actMaster(Model model, HttpSession session) {
		List<Activities> listActivities = activityService.getAllActivitys();
		model.addAttribute("listActivities", listActivities);
		
		String userCode = (String) session.getAttribute("username");
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	/**
	 * 
	 * @param  Save  Activity
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveActivity")
	public String saveActivity(@ModelAttribute("activity")Activities activities, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		activities.setInsBy(insertedBY);
		activityService.addActivity(activities);
		List<Activities> listActivities = activityService.getAllActivitys();
		model.addAttribute("listActivities", listActivities);


		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}
	/**
	 * Edit Find Single Record And Set Form of update Activity Form
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editActivity/{id}" })
	public String editActivity(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo =38;
		String reqPageedit ="/editActivity";
		Activities activityEdit = activityService.findActivityById(id);
		model.addAttribute("activityEdit", activityEdit);

		
		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
	/**
	 * 
	 * @param Update Activity 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateActivity")
	public String updateActivity(@ModelAttribute("activity")Activities activities, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		activities.setUpdBy(updatedBY);
		this.activityService.updateActivity(activities);
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	/**
	 * 
	 * @param id  delete base on id Activity
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteActivity/{id}" })
	public String deleteActivity(@PathVariable("id") String id, Model model, HttpSession session) {
		this.activityService.removeActivity(id);
		return "redirect:/"+ pageMappingService.PageRequestMapping(reqPage, pageno);
	}

}
