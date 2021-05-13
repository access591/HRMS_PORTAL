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
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.EmployeeRequisition;
import com.hrms.model.MenuModule;
import com.hrms.model.ReqAdvertisement;
import com.hrms.model.ReqAdvertisementDetail;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class RequisitionAdvertisementController {
	
	@Autowired ModuleService moduleService;
	@Autowired DepartmentService departmentService;
	@Autowired EmployeeService employeeService;
	@Autowired DesignationService designationService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	
	
	@InitBinder("reqAdvertisement")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "advtDate",
                                    new CustomDateEditor(dateFormatter, true));
        
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
		
		return "Advertisment";
	}
	
	
	@PostMapping("saveAdvertisement")
	public String saveAdvertisement(@ModelAttribute("reqAdvertisement")ReqAdvertisement reqAdvertisement,
			HttpSession session) {
		
		System.out.println("hiii");
		
		System.out.println("save advertisement detail : "+ reqAdvertisement.getRemarks());
		
		System.out.println("save advertisement detail : "+ reqAdvertisement.getAdvtCode());
		System.out.println("save advertisement detail : "+ reqAdvertisement.getAdvtDate());
		
		List<ReqAdvertisementDetail> listReqAdvertisementDetail = new ArrayList<ReqAdvertisementDetail>();
		ReqAdvertisementDetail reqAdvtDetail = new ReqAdvertisementDetail();
		
		for(int i =0;i<reqAdvertisement.getListReqAdvertisementDetail().size();i++) {
			reqAdvtDetail = reqAdvertisement.getListReqAdvertisementDetail().get(i);
			System.out.println("nested class value : "+ reqAdvtDetail.getAdvtAmount());
		}
		return null;
	}

}
