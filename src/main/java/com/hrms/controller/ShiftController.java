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
import com.hrms.service.PageMappingService;
import com.hrms.service.ShiftService;

@Controller
public class ShiftController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	ShiftService shiftService;
	int pageno = 39;
	String reqPage = "/shiftMaster";
	@Autowired
	PageMappingService pageMappingService;

	/**
	 * Request Mapping Shift Master
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
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

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	/**
	 * save Record Shift Master Request Mapping
	 * 
	 * @param shift
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveShift")
	public String saveShift(@ModelAttribute("shift") Shift shift, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		shift.setInsBy(insertedBY);
		shiftService.addShift(shift);
		List<Shift> listShift = shiftService.getAllShifts();
		model.addAttribute("listShift", listShift);
		session.setAttribute("username", session.getAttribute("username"));

		 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}

	/**
	 * Find Record And View Value on Update Shift master Page
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editShift/{id}" })
	public String editShift(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo = 40;
		String reqPageedit = "/editShift";
		Shift shiftEdit = shiftService.findShiftById(id);
		model.addAttribute("shiftEdit", shiftEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}

	/**
	 * Update Record Shift Master
	 * 
	 * @param s
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/updateShift")
	public String updateShift(@ModelAttribute("shift") Shift s, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		s.setUpdBy(updatedBY);
		this.shiftService.updateShift(s);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	/**
	 * delete Shift Master Record
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteShift/{id}" })
	public String deleteShift(@PathVariable("id") String id, Model model, HttpSession session) {
		this.shiftService.removeShift(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

}
