package com.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrms.model.CommonUtil;
import com.hrms.model.Department;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeRequisitionApprovalController {

	@Autowired ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	@Autowired DepartmentService departmentService;
	
	
	@GetMapping("employeeRequisitionApproval")
	public String employeeRequisitionApproval(@ModelAttribute("commonUtil")CommonUtil commonUtil ,Model model, HttpSession session) {
		
		//model.addAttribute("listAward", listAward);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		//CommonUtil(String deptName, String reqCode, Date reqDate, String reqPriority, String reqApprover,
		//		String remarks, String insBy, Date insDate, Date reqTill, Date approveDate, String status)
		
		
		List<CommonUtil> listCommonUtil = new ArrayList<CommonUtil>();
		List<EmployeeRequisition> listEmployeeReq = employeeRequisitionService.findEmployeeReqByStatusN();
		for(int i=0;i<listEmployeeReq.size();i++) {
			Department department = departmentService.findDepartmentById(listEmployeeReq.get(i).getDeptCode());
			EmployeeRequisition em = listEmployeeReq.get(i);
			CommonUtil commonUtill = new CommonUtil(department.getDeptName(),em.getReqCode(),em.getReqDate(),
					em.getReqPriority(),em.getReqApprover(),em.getRemarks(),em.getInsBy(),em.getInsDate(),
					em.getReqTill(),em.getApproveDate(),em.getStatus());
			listCommonUtil.add(commonUtill);
		}//editEmployeeRequisition.html
		
		if(listCommonUtil != null) {  
			model.addAttribute("listCommonUtil", listCommonUtil);
		}
		
		List<EmployeeRequisition> approvalReq = employeeRequisitionService.findEmployeeReqByStatusY();
		if(approvalReq != null) {
			model.addAttribute("approved", approvalReq);
		}
		session.setAttribute("username", session.getAttribute("username"));
		   
		return "employeeRequisitionApproval";
		
		//return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	@GetMapping("approveRequisition/{id}")
	public String approveRequisition(@PathVariable("id") String reqCode) {
		
		System.out.println("hiiiiiii" + reqCode);
		//System.out.println("hiiiiiii" + name);
		//employeeRequisitionService.approvedByReqCode(reqCode);
		return "redirect:/employeeRequisitionApproval";
	}
	
	
	
}
