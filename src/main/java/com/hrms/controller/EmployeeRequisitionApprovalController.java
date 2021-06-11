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

import com.hrms.ImageUtil;
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
		
		
		List<CommonUtil> listCommonUtil = new ArrayList<CommonUtil>();
		List<EmployeeRequisition> listEmployeeReq = employeeRequisitionService.getAllPendingEmployeeRequisition();
		
		for(int i=0;i<listEmployeeReq.size();i++) {
			Department department;
			try {
				//department = departmentService.findDepartmentById(listEmployeeReq.get(i).getDeptCode());
				EmployeeRequisition em = listEmployeeReq.get(i);
				CommonUtil commonUtill = new CommonUtil(em.getDepartmet().getDeptName() ,em.getReqCode(),em.getReqDate(),
						em.getReqPriority(),em.getReqApprover(),em.getRemarks(),em.getInsBy(),em.getInsDate(),
						em.getReqTill(),em.getApproveDate(),em.getStatus());
				commonUtill.setStatus(em.getStatus());
				listCommonUtil.add(commonUtill);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		}//editEmployeeRequisition.html
		
		
		model.addAttribute("listCommonUtil", listEmployeeReq);
	
		
		List<EmployeeRequisition> approvalReq = employeeRequisitionService.findEmployeeReqByStatusY();
		if(approvalReq != null) {
			model.addAttribute("approved", approvalReq);
		}
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "EmployeeRequisitionApproval"; //EmployeeRequisitionApproval.html
		
		//return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
	
	@GetMapping("approveRequisition/{id}/{status}")
	public String approveRequisition(@PathVariable("id") String reqCode,@PathVariable("status") String approvalStatus) {
		
		System.out.println("hiiiiiii" + reqCode);
		//System.out.println("hiiiiiii" + name);
		
		
		employeeRequisitionService.approvedByReqCodeAndStatus(reqCode,approvalStatus);
		return "redirect:/employeeRequisitionApproval";
	}
	
	
	
}
