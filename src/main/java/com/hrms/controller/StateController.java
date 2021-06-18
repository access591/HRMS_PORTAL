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

import com.hrms.ImageUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.StateService;

@Controller
public class StateController {
	
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	StateService stateService;
	@Autowired
	PageMappingService pageMappingService;
	/**
	 * Request mapping State Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/stateMaster")
	public String stateMaster(Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		if(userCode!=null) {
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);
		session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "stateMaster";
		}
		else
		{
			  return "redirect:" + "./";	
		}
	}

	/**
	 * 
	 * @param  Save state Master 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveState")
	public String saveState(@ModelAttribute("state") State state, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String insertedBY = (String) session.getAttribute("USER_NAME");
		state.setInsBy(insertedBY);
		stateService.addState(state);

		List<State> listState =stateService.getAllStates();
		model.addAttribute("listState", listState);
		
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/stateMaster";
		

	}
	/**
	 * 
	 * @param id find unique record edit 
	 * @param model
	 * @param session
	 * @return
	 */
	
	@GetMapping(value = { "/editState/{id}" })
	public String editState(@PathVariable("id") String id, Model model, HttpSession session) {
		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		session.setAttribute("imgUtil", new ImageUtil());
		State stateEdit = stateService.findStateById(id);
		model.addAttribute("stateEdit", stateEdit);
		session.setAttribute("username", session.getAttribute("username"));
		
		return "editState";
	}
	
	/**
	 * 
	 * @param upadte state master 
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateState")
	public String updateState(@ModelAttribute("city") State c, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		String updatedBY = (String) session.getAttribute("USER_NAME");
		c.setUpdBy(updatedBY);
		this.stateService.updateState(c);
		return"redirect:/stateMaster";
		
	}
	
	/**
	 * 
	 * @param id delete record base on id 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteState/{id}" })
	public String deleteState(@PathVariable("id") String id, Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		this.stateService.removeState(id);
		session.setAttribute("username", session.getAttribute("username"));
		return"redirect:/stateMaster";
	}
}

