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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.EmployeeRequisition;
import com.hrms.model.EmployeeRequisitionDetail;
import com.hrms.model.MenuModule;
import com.hrms.model.ReqAdvertisement;
import com.hrms.model.ReqAdvertisementDetail;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.ReqAdvertisementDetailService;
import com.hrms.service.RequisitionAdvertisementService;

@Controller
public class RequisitionAdvertisementController {
	
	@Autowired ModuleService moduleService;
	@Autowired DepartmentService departmentService;
	@Autowired EmployeeService employeeService;
	@Autowired DesignationService designationService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	@Autowired RequisitionAdvertisementService reqAdvertisementService;
	
	
	@InitBinder("reqAdvertisement")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
//        binder.registerCustomEditor(Date.class, "advtDate",
//                                    new CustomDateEditor(dateFormatter, true));
        
    }
	
	
	
	@GetMapping("/advertisment")
	public String advertismentPage(
			Model model, HttpSession httpSession) {
		
		String userCode = (String) httpSession.getAttribute("username");
		
//		if(userCode.equals("") || userCode.equals(null)) {
//			System.out.println("user is not verifyied");
//			return "Advertisment";
//		}
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<EmployeeRequisition> listEmployeeRequisition = employeeRequisitionService.findEmployeeReqByStatusN();
		if(listEmployeeRequisition != null) {
			model.addAttribute("listEmployeeRequisition", listEmployeeRequisition);
			
		}
		
		List<ReqAdvertisementDetail> listReqAdvertisementDetail = new ArrayList<ReqAdvertisementDetail>();
		ReqAdvertisement reqAdvertiesment = new ReqAdvertisement();
		reqAdvertiesment.setListReqAdvertisementDetail(listReqAdvertisementDetail);
		model.addAttribute("reqAdvertisement", reqAdvertiesment);
		
		List<ReqAdvertisement> listReqAdvertisement = reqAdvertisementService.getAllReqAdvertisement();
		if(listReqAdvertisement != null) {
			model.addAttribute("listReqAdvertisement", listReqAdvertisement);
		}
		
		return "Advertisment";
	}
	
	
	@PostMapping("saveAdvertisement")
	public String saveAdvertisement(@ModelAttribute("reqAdvertisement")ReqAdvertisement reqAdvertisement,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		System.out.println("save Advertisement module==>>" + 
							reqAdvertisement.getListReqAdvertisementDetail().get(0).getReqCode());;
		List<ReqAdvertisementDetail> listReqAdvertisementDetail = new ArrayList<ReqAdvertisementDetail>();
		ReqAdvertisementDetail reqAdvtDetail = new ReqAdvertisementDetail();
		
		
		for(ReqAdvertisementDetail eDetail : reqAdvertisement.getListReqAdvertisementDetail()) {
			eDetail.setReqAdvertisement(reqAdvertisement); 
		}
		
		reqAdvertisementService.addActivity(reqAdvertisement);
		System.out.println("working===========");
		return "redirect:advertisment"; //Advertisment.html   
	}
	
	@GetMapping("deleteAdvertisement/{id}")
	public String deleteAdvertisement(@PathVariable("id") String advtCode,RedirectAttributes redirectAttributes,
			Model model, HttpSession session) {
	
		this.reqAdvertisementService.removeReqAdvertisement(advtCode);
		return "redirect:/advertisment";
	}
	
	@GetMapping(value = {"editAdvertisement/{id}"})
	public String editAdvertisement(@PathVariable("id") String reqCode,
			Model model) {
		
		ReqAdvertisement req = reqAdvertisementService.findReqAdvertisementById(reqCode);
		if(req != null) {
			model.addAttribute("reqAdvt", req);
		}
		System.out.println("employee requisition id : "+ reqCode);
		return "editAdvertisement";  //editAdvertisement.html
	}
	
	@PostMapping(value = {"updateAdvertisement"})
	public String updateAdvertisement(@ModelAttribute("reqAdvt")ReqAdvertisement reqAdvertisement,
			Model model) {
		
		  System.out.println("=====================>");

		  for(ReqAdvertisementDetail re : reqAdvertisement.getListReqAdvertisementDetail()) {
			  re.setReqAdvertisement(reqAdvertisement);
		  }
		 
		
		  reqAdvertisementService.updateReqAdvertisement(reqAdvertisement);
		
		return "redirect:advertisment";
	}

}
