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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.ImageUtil;
import com.hrms.helper.Message1;
import com.hrms.model.CommonUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.EmployeeRequisitionDetail;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeRequisitionService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class EmployeeRequisitionController {
	int  pageNo= 71;
	String reqPage = "employeeRequisition";  
	@Autowired ModuleService moduleService;
	@Autowired PageMappingService pageMappingService;
	@Autowired DepartmentService departmentService;
	@Autowired EmployeeService employeeService;
	@Autowired DesignationService designationService;
	@Autowired EmployeeRequisitionService employeeRequisitionService;
	
	
	@InitBinder("req")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "approveDate",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "reqDate",
                new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "reqTill",
                new CustomDateEditor(dateFormatter, true));

       
    }
	
	
	
	@GetMapping("employeeRequisition")  
	public String employeeRequisition(Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		//session.setAttribute("imgUtil", new ImageUtil());
		String userCode = (String) session.getAttribute("username");  
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Department> departmentList = departmentService.getAllDepartments();
		if(departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		
	
		
		
		List<EmployeeRequisitionDetail> emp = new ArrayList<>();
		EmployeeRequisition eReq = new EmployeeRequisition();
		
		List<EmployeeRequisition> requisition = employeeRequisitionService.getAllEmployeeRequisition();
		if(requisition != null) {
			model.addAttribute("requisition", requisition);
		}
		
		
		
		eReq.setEmployeRequisitionDetail(emp);
		
		model.addAttribute("req", eReq);
		
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("userlogin", userCode);
		return "employeeRequisition";
	}
	
	
	@PostMapping("saveEmployeeRequisition")
	public String saveEmployeeRequisition(@ModelAttribute("req") EmployeeRequisition employeeRequisition,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String insertedBY = (String) session.getAttribute("userlogin");
		
		try {
			
			List<EmployeeRequisitionDetail> re = new ArrayList<>();
			
			for(int i=0;i<employeeRequisition.getEmployeRequisitionDetail().size();i++) {
				EmployeeRequisitionDetail e = new EmployeeRequisitionDetail();
				e = employeeRequisition.getEmployeRequisitionDetail().get(i);
				e.setReqDate(employeeRequisition.getReqDate());
				e.setEmployeeRequisition(employeeRequisition);
				re.add(e);	
				
			}
			
			employeeRequisition.setEmployeRequisitionDetail(re);
			employeeRequisitionService.addEmployeeRequisition(employeeRequisition);
			
			session.setAttribute("message",new Message1("Data has been Successfully added","alert-primary"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message1("Something went Wrong !!","alert-info"));
			
		}
		
		session.setAttribute("userlogin", insertedBY);

		return "redirect:employeeRequisition"; 
	}
	
	
	@GetMapping(value = {"editRequisition/{id}"})
	public String editRequisition(@PathVariable("id") String reqCode,@ModelAttribute("req") EmployeeRequisition employeeRequisition,
			Model model,HttpSession session) {
	
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		EmployeeRequisition requisition = employeeRequisitionService.findEmployeeRequisitiondById(reqCode);
		
		

		
		if(requisition != null) {
			model.addAttribute("req", requisition);
		}
		
		return "editEmployeeRequisition";
	}
	
	@PostMapping(value = {"updateRequisition"})
	public String updateRequisition(@ModelAttribute("req") EmployeeRequisition employeeRequisition,
			Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		  
		try {
			ArrayList<EmployeeRequisitionDetail> employeeRequisitionDetail = new ArrayList<>();
			
			for(EmployeeRequisitionDetail eDetail : employeeRequisition.getEmployeRequisitionDetail()) {
				eDetail.setEmployeeRequisition(employeeRequisition);
				employeeRequisitionDetail.add(eDetail);
			}
			
			this.employeeRequisitionService.updateEmployeeRequisition(employeeRequisition);
			session.setAttribute("message",new Message1("Data has been Successfully Updated","alert-primary"));
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message",new Message1("Something went Wrong","alert-primary"));
		}
		
		return "redirect:employeeRequisition";
	}
	
	
	@GetMapping(value = {"deleteRequisition/{id}"})
	public String deleteRequisition(@PathVariable("id") String reqCode, Model model, HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		try {
			
			employeeRequisitionService.removeEmployeeRequisition(reqCode);
			session.setAttribute("message",new Message1("Record Deleted","alert-primary"));
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message",new Message1("Something went Wrong","alert-primary"));
		}
		
		
		
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/employeeRequisition";
	}
	
	
	@GetMapping(value = {"viewRequisition/{id}"})
	public String viewRequisition(@PathVariable("id") String reqCode,@ModelAttribute("req") EmployeeRequisition employeeRequisition,
			Model model,HttpSession session) {
	
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		EmployeeRequisition requisition = employeeRequisitionService.findEmployeeRequisitiondById(reqCode);
		
		if(requisition != null) {
			model.addAttribute("req", requisition);
		}
		
		return "viewEmployeeRequisition";
	}
	
	
	
	
	
	
	@ResponseBody
	@GetMapping("/designationbydept/{selecteDepartmentValue}")
	public List<CommonUtil> getDesignationByDepartment(@PathVariable("selecteDepartmentValue") String deptCode) {
		
		
		List<Employee> employeeList = employeeService.findByDepartmentCode(deptCode);
		List<CommonUtil> details = new ArrayList<>();
		CommonUtil empl = new CommonUtil();
		for(int i =0;i<employeeList.size();i++) {
			Designation designation = designationService.findDesignationById(employeeList.get(i).getDesignationCode());
			
			empl.setDesgName(designation.getDesgName());
			empl.setDesigCode(designation.getDesgCode());
			details.add(empl);
		}
		return details;
	}
	
	
	
	@ResponseBody
	@GetMapping("designationsize")
	public int getDesignationSize() {
	
		return designationService.getAllDesignations().size();
	}
	
}
	
	
	
	
	