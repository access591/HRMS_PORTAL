package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.model.ApplicantExpDetail;
import com.hrms.model.ApplicantInfo;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.MenuModule;
import com.hrms.model.ReqAdvertisement;
import com.hrms.model.ReqAdvertisementDetail;
import com.hrms.service.ApplicantInfoService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.ReqAdvertisementDetailService;
import com.hrms.service.RequisitionAdvertisementService;

@Controller
public class ApplicantInformationController {

	@Autowired
	private ModuleService moduleService;
	@Autowired RequisitionAdvertisementService reqAdvertisementService;
	@Autowired ApplicantInfoService applicantInfoService;
	@Autowired DesignationService designationService;
	@Autowired EmployeeService employeeService;
	@Autowired ReqAdvertisementDetailService reqAdvertisementDetailService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	
	
	
	
	@GetMapping("applicantInformation")
	public String applicantInformationPage(@ModelAttribute("applicantInfo")ApplicantInfo applicantInfo ,Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<ReqAdvertisement> listReqAdvertisement = reqAdvertisementService.getAllReqAdvertisement();
		
		if(listReqAdvertisement != null) {
			model.addAttribute("listReqAdvertisement", listReqAdvertisement);
		}
			

		List<EmployeeRequisition> listEmployeeRequisition1 = employeeRequisitionService.findEmployeeReqByStatusY();
		if(listEmployeeRequisition1 != null) {
			
			List<EmployeeRequisition> listEmployeeRequisition = new ArrayList<EmployeeRequisition>(); 
			for(EmployeeRequisition e : listEmployeeRequisition1) {
				if(!listEmployeeRequisition.contains(e.getDepartmet().getDeptName())) {
					listEmployeeRequisition.add(e);
				}
			}
			//System.out.println("list employee requisition department : "+ listEmployeeRequisition.get(0).getDepartmet().getDeptName());
			model.addAttribute("listEmployeeRequisition", listEmployeeRequisition);
		}
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if(listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}

		session.setAttribute("username", session.getAttribute("username"));
		return "applicantInformation";  //applicantInformation.html
	}
	
	
	@PostMapping("/saveApplicantInfo")
	public String saveApplicantInfo(@ModelAttribute("applicantInfo")ApplicantInfo applicantInfo,HttpSession session) {
	
		System.out.println("applicantInfo : "+ applicantInfo.getApplicantCode());
		
		System.out.println("applicantInfo : "+ applicantInfo.getApplicantDate());
		
		if(applicantInfo.getApplicantExpDetail() != null) {
			for(ApplicantExpDetail aDetail : applicantInfo.getApplicantExpDetail()) {
				aDetail.setApplicantDate(applicantInfo.getApplicantDate());
				aDetail.setApplicantInfo(applicantInfo);
			}
		}else {
			ApplicantExpDetail e = new ApplicantExpDetail();
			e.setApplicantInfo(applicantInfo);
		}
		
		applicantInfoService.addApplicantInfo(applicantInfo);
		return "redirect:applicantInformation";
	}
	
	
	@ResponseBody
	@GetMapping("getAdvtDate/{id}")
	public String getRequisitionDateByAdvtCode(@PathVariable("id")String advtCode) {
		//get advt code and advt date from Advertisement
		ReqAdvertisement reqAdvertisement = reqAdvertisementService.findReqAdvertisementById(advtCode);
		Date advtDate = reqAdvertisement.getAdvtDate();
		System.out.println("======>>"+ advtDate);
		return advtDate.toString();
	}
	
	@InitBinder("applicantInfo")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "advtDate",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "applicantDate",
                new CustomDateEditor(dateFormatter, true));
//        binder.registerCustomEditor(Date.class, "reqTill",
//                new CustomDateEditor(dateFormatter, true));
//        binder.registerCustomEditor(Date.class, "reqDate",
//                new CustomDateEditor(dateFormatter, true));
       
    }
	
	
	
	
}
