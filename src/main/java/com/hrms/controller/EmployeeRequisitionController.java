package com.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.hrms.model.EmployeeLeaveRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.EmployeeRequisitionCreationDto;
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
	String reqPage = "employeeRequisition";  //employeeRequisition.html.html
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

		String userCode = (String) session.getAttribute("username");  
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Department> departmentList = departmentService.getAllDepartments();
		if(departmentList != null) {
			model.addAttribute("departmentList", departmentList);
		}
		
		
		
		List<EmployeeRequisitionDetail> emp = new ArrayList<EmployeeRequisitionDetail>();
		EmployeeRequisition req = new EmployeeRequisition();
		
		EmployeeRequisitionDetail e = new EmployeeRequisitionDetail();
		for(int i=0;i<2;i++) {
			
			emp.add(e);
		}
		req.setEmployeRequisitionDetail(emp);
		System.out.println("lenths =====" + emp.size());
		model.addAttribute("req", req);
		model.addAttribute("length", emp.size());
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("userlogin", userCode);
		return pageMappingService.PageRequestMapping(reqPage, pageNo);
	}
	
	
	@PostMapping("saveEmployeeRequisition")
	public String saveEmployeeRequisition(@ModelAttribute("req") EmployeeRequisition employeeRequisition,
			HttpSession session,RedirectAttributes redirectAttributes) {
		
		String insertedBY = (String) session.getAttribute("userlogin");
		System.out.println("save employee requisition method  : "+
					employeeRequisition.getEmployeRequisitionDetail().get(0).getDesigCode());
		System.out.println("save employee requisition method  : "+
				employeeRequisition.getEmployeRequisitionDetail().get(1).getDesigCode());
		
		System.out.println("save employee requisition method  : " + employeeRequisition.getRemarks());
		//EmployeeRequisitionDetail e = new EmployeeRequisitionDetail();
		List<EmployeeRequisitionDetail> re = new ArrayList<EmployeeRequisitionDetail>();
		EmployeeRequisitionDetail e = new EmployeeRequisitionDetail();
		
		for(int i =0;i<employeeRequisition.getEmployeRequisitionDetail().size();i++) {
			
			e = employeeRequisition.getEmployeRequisitionDetail().get(i);
			
			e.setEmployeeRequisition(employeeRequisition);
			re.add(e);
			

		}
		employeeRequisitionService.addEmployeeRequisition(employeeRequisition);
		session.setAttribute("userlogin", insertedBY);

		return "redirect:employeeRequisition"; //+pageMappingService.PageRequestMapping(reqPage,pageNo)
	}
	
	
	
	
	
	@ResponseBody
	@GetMapping("/designationbydept/{selecteDepartmentValue}")
	public List<EmployeeLeaveRequest> getDesignationByDepartment(@PathVariable("selecteDepartmentValue") String deptCode) {
		
		System.out.println("department value is"+ deptCode);
		List<Employee> employeeList = employeeService.findByDepartmentCode(deptCode);
		List<EmployeeLeaveRequest> details = new ArrayList<EmployeeLeaveRequest>();
		EmployeeLeaveRequest empl = new EmployeeLeaveRequest();
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
	System.out.println("ïn designation method");
		return designationService.getAllDesignations().size();
	}
	
	
	
	
	
}