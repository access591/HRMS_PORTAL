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
import com.hrms.model.Shift;
import com.hrms.service.ModuleService;
import com.hrms.service.ShiftService;

@Controller
public class ShiftController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	ShiftService shiftService;

	@GetMapping("/shiftMaster")
	public String shiftMaster(Model model, HttpSession session) {
		List<Shift> listShift = shiftService.getAllShifts();
		model.addAttribute("listShift", listShift);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "shiftMaster";
	}
	
	@PostMapping("/saveShift")
	public String saveShift(@ModelAttribute("shift") Shift shift, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		shift.setInsBy(insertedBY);
		shiftService.addShift(shift);
		List<Shift> listShift = shiftService.getAllShifts();
		model.addAttribute("listShift", listShift);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/shiftMaster";

	}
	
	@GetMapping(value = { "/editShift/{id}" })
	public String editShift(@PathVariable("id") String id, Model model, HttpSession session) {

		Shift shiftEdit = shiftService.findShiftById(id);
		model.addAttribute("shiftEdit", shiftEdit);
		session.setAttribute("username", session.getAttribute("username"));
		
		return "/editShift";
	}
	
	
	@PostMapping("/updateShift")
	public String updateShift(@ModelAttribute("shift") Shift s, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		s.setUpdBy(updatedBY);
		this.shiftService.updateShift(s);

		return "redirect:/shiftMaster";
	}
	@GetMapping(value = { "/deleteShift/{id}" })
	public String deleteShift(@PathVariable("id") String id, Model model, HttpSession session) {
		this.shiftService.removeShift(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/shiftMaster";
	}

}
