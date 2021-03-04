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

import com.hrms.model.Register;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.RegisterService;
@Controller
public class RegisterController {
	
	int pageno=31;
	String reqPage="/registerMaster";
	
	@Autowired
	private ModuleService moduleService;
	@Autowired
	RegisterService registerService;
	@Autowired
	PageMappingService pageMappingService;
/**
 * Request Mapping Register Master
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/registerMaster")
	public String registerMaster(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Register> listRegister = registerService.getAllRegisters();
		model.addAttribute("listRegister", listRegister);

		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * 
 * @param  Save register Master 
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveRegister")
	public String saveRegister(@ModelAttribute("register") Register register, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		register.setIns_by(insertedBY);
		registerService.addRegister(register);
		List<Register> listRegister = registerService.getAllRegisters();
		model.addAttribute("listRegister", listRegister);

		session.setAttribute("username", session.getAttribute("username"));

		 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}
/**
 *  
 * @param id  find Record and Update
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/editRegister/{id}" })
	public String editRegister(@PathVariable("id") String id, Model model, HttpSession session) {

		int editPageNo = 32;
		String reqPageedit = "/editRegister";

		Register registerEdit = registerService.findRegisterById(id);
		model.addAttribute("registerEdit", registerEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
/**
 * Update Register Master 
 * @param r
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/updateRegister")
	public String updateRegister(@ModelAttribute("register") Register r, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		r.setUpd_by(updatedBY);
		this.registerService.updateRegister(r);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * Delete Register Master 
 * @param id
 * @param model
 * @param session
 * @return
 */
	
	@GetMapping(value = { "/deleteRegister/{id}" })
	public String deleteRegister(@PathVariable("id") String id, Model model, HttpSession session) {
		this.registerService.removeRegister(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

}
