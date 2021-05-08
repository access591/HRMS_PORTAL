package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.MedicalReimbursement;
import com.hrms.model.MenuModule;
import com.hrms.service.MedicalReimbursementService;
import com.hrms.service.ModuleService;
@Controller
public class MediclaimApprovalController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	MedicalReimbursementService medicalReimbursementService;
	
	@GetMapping("/mediclaimApproval")
	public String mediclaimApproval(Model model, HttpSession session)
	{
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<MedicalReimbursement> listMedicalReimbursement =  medicalReimbursementService.getAllMedicalReimbursement();
		model.addAttribute("listMedicalReimbursement", listMedicalReimbursement);

		session.setAttribute("username", session.getAttribute("username"));
		
		
	return 	"mediclaimApproval";
	}
	

}
