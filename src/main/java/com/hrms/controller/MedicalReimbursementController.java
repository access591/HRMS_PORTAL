package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Employee;
import com.hrms.model.MedicalReimbursement;
import com.hrms.model.MedicalReimbursementDetail;
import com.hrms.model.MedicalReimbursementUtil;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.MedicalReimbursementDetailsService;
import com.hrms.service.MedicalReimbursementService;
import com.hrms.service.ModuleService;

@Controller
public class MedicalReimbursementController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	MedicalReimbursementService medicalReimbursementService;
	@Autowired
	MedicalReimbursementDetailsService medicalReimbursementDetailsService;
	@GetMapping("/medicalReimbursement")
	public String medicalReimbursement(Model model, HttpSession session) {

		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		return "medicalReimbursement";
	}
	
	@PostMapping("/saveMedicalReimbursement")
	String saveLeaveDetail(@ModelAttribute("medicalReimbursement") MedicalReimbursementUtil medicalReimbursement, Model model,
			HttpSession session,HttpServletRequest request){
		
		 MedicalReimbursement m2=new MedicalReimbursement();
		 MedicalReimbursementDetail m4=new MedicalReimbursementDetail();
		 Employee emp=new Employee();
		 emp.setEmpCode(medicalReimbursement.getEmpCode());
		 m2.setEmpCode(emp);
		 m2.setDateOfSlip(medicalReimbursement.getDateOfSlip());
		 m2.setNameOfPerson(medicalReimbursement.getNameOfPerson());
		 m2.setEmpRelation(medicalReimbursement.getEmpRelation());
		 m2.setMedIndOut(medicalReimbursement.getMedIndOut());
		 m2.setTreatmentType(medicalReimbursement.getTreatmentType());
		 m2.setTreatDescription(medicalReimbursement.getTreatDescription());
		 m2.setClaimingDate(medicalReimbursement.getClaimingDate());
		 
		 medicalReimbursementService.addMedicalReimbursement(m2);
		
           String slipNo=m2.getSlipNo();
           session.setAttribute("slipNo",slipNo);
         
           
           System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+slipNo);
        
		
        int flag = 0;
   		int counter = 1;
		try {
			
			
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr"));
			System.out.println("counter::::::::::::::::::::" + counter);
			
		
			for (int i =0; i < counter; i++) 
			{
				System.out.println("for loop run time>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				if (request.getParameter("medStDr" + i) != null) {
					m4.setMedStDr(request.getParameter("medStDr" + i));
				} else {
					m4.setMedStDr("" + i);
				}
				
				if(request.getParameter("caseMemoNo" + i) != null) {
					m4.setCaseMemoNo(request.getParameter("caseMemoNo" + i));
				} else {
					m4.setCaseMemoNo("" + i);
				}
				
			
				if(request.getParameter("caseMemoDate" + i) != null) {
					m4.setCaseMemoDate(request.getParameter("caseMemoDate" + i));
				} else {
					m4.setCaseMemoDate("" + i);
				}
					
				if(request.getParameter("calmlAmount" + i) != null) {
					m4.setCalmlAmount(request.getParameter("calmlAmount" + i));
				} else {
					m4.setCalmlAmount("" + i);
				}
				
				if(request.getParameter("passedAmount" + i) != null) {
					m4.setPassedAmount(request.getParameter("passedAmount" + i));
				} else {
					m4.setPassedAmount("" + i);
				}
				
				if(request.getParameter("govtexemptionAmount" + i) != null) {
					m4.setGovtexemptionAmount(request.getParameter("govtexemptionAmount" + i));
				} else {
					m4.setGovtexemptionAmount("" + i);
				}
				
				if(request.getParameter("remark" + i) != null) {
					m4.setRemark(request.getParameter("remark" + i));
				} else {
					m4.setRemark("" + i);
				}
				
				
			
		       m2.setSlipNo(slipNo);
				m4.setSlipNo(m2);
				insertStatusMR= medicalReimbursementDetailsService.addMedicalReimbursementDetails(m4);
				
				if (insertStatusMR) {
					System.out.println("Counter" + flag);
					flag++;

				}
				
			}
			
			
			if (flag > 0) {
				session.setAttribute("Message", "Data added successfully.");
				
			} else {
				System.out.println("Enter into  failure part :");
				
			}

		
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
	
		return "redirect:/medicalReimbursement";

		
	}
	@GetMapping("/viewMedicalReimbursement")
	String viewMedicalReimbursement(Model model, HttpSession session) {
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<MedicalReimbursement>listMedicalReimbursement=medicalReimbursementService.getAllMedicalReimbursement();
		model.addAttribute("listMedicalReimbursement", listMedicalReimbursement);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		return "viewMedicalReimbursement";
		
	}

	 @CrossOrigin
	    @GetMapping("/medicalReimbursementViewDetails/{id}")
	    public ResponseEntity<MedicalReimbursementUtil> getMedicalReimbursementById(@PathVariable(value = "id") String id) {
		
		 MedicalReimbursementUtil medicalReimbursement=new MedicalReimbursementUtil();
		 MedicalReimbursement m1 = medicalReimbursementService.findByIdMedicalReimbursementMaster(id);
		 medicalReimbursement.setDateOfSlip(m1.getDateOfSlip());
		 medicalReimbursement.setNameOfPerson(m1.getNameOfPerson());

		System.out.println("XXXXXXXXXXXXXXXXXXX>>>>>>>>>>>>>>>>>>>>>>>>"+m1.getDateOfSlip());
		 MedicalReimbursementDetail m4=new MedicalReimbursementDetail();
		 
		 if(medicalReimbursement == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(medicalReimbursement);
	    }
	
	}


