package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.model.Employee;
import com.hrms.model.MedicalReimbursement;
import com.hrms.model.MedicalReimbursementDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.MedicalReimbursementDetailsService;
import com.hrms.service.MedicalReimbursementService;
import com.hrms.service.ModuleService;
@Controller
public class MediclaimApprovalController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	MedicalReimbursementService medicalReimbursementService;
	@Autowired
	MedicalReimbursementDetailsService medicalReimbursementDetailsService;
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
		
		 List<MedicalReimbursementDetail> listMedicalR=new ArrayList<MedicalReimbursementDetail>();
		
		try {
			for(int i=0;i<listMedicalReimbursement.size();i++) {
				listMedicalR= medicalReimbursementDetailsService.getAllMedicalReimbursementDetailBYslipNO(listMedicalReimbursement.get(i).getSlipNo());
				 
				 model.addAttribute("listMedicalReimbursementDetail", listMedicalR);
				 session.setAttribute("username", session.getAttribute("username"));
				 
			}
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return 	"mediclaimApproval";
		
		
		
	
	}
	
	
	
	@GetMapping(value = {"/viewMedicalReimbursement/{id}"})
	public String viewMedicalReimbursement(@PathVariable("id")String id,  Model model,HttpSession session)
	 { 
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		MedicalReimbursement medicalReimbursementEdit =	medicalReimbursementService.findByIdMedicalReimbursementMaster(id);
		  model.addAttribute("medicalReimbursementEdit", medicalReimbursementEdit);

	   
	    return "viewMedicalReimbursement";
	}
	
	@GetMapping("approveMedicalReimbursement/{id}")
	public String approveRequisition(@PathVariable("id") String slipNo) {
		
		
		medicalReimbursementService.approvedByMrId(slipNo);
		return "redirect:/mediclaimApproval";
	}
	

}
