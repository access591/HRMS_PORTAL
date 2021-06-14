	package com.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.util.AttendenceRegisterUtil;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class AttendanceRegisterController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	AttendenceRegisterService attendenceRegisterService;
	@GetMapping("/attendanceRegister")
	public String attendanceRegister(Model model, HttpSession session) {
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<AttendenceRegister> listAttendanceR = attendenceRegisterService.getAllAttendenceRegister();
		List<AttendenceRegisterUtil> listAttendenceRegisterUtil = new ArrayList<AttendenceRegisterUtil>();
		  for (int i = 0; i < listAttendanceR.size(); i++) {
			  String empCode = listAttendanceR.get(i).getEmployee().getEmpCode();
			  AttendenceRegisterUtil attreg=new AttendenceRegisterUtil();
			     Employee employee = employeeService.findEmployeeById(empCode);
			    Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
			    Designation designation = designationService.findDesignationById(employee.getDesignationCode());
			    
			    attreg.setId(listAttendanceR.get(i).getId());
			    attreg.setAttendenceDate(listAttendanceR.get(i).getAttendenceDate());
			    attreg.setaTimeIn(listAttendanceR.get(i).getaTimeIn());
			    attreg.setaTimeOut(listAttendanceR.get(i).getaTimeOut());
			    attreg.setEmpName(employee.getEmpName());
			    attreg.setDeptName(department.getDeptName());
			    attreg.setDesgName(designation.getDesgName());
			    
			    listAttendenceRegisterUtil.add(attreg);
			    
			    model.addAttribute("attendReg", listAttendenceRegisterUtil); 
			    
			    
		  }
		  
		  session.setAttribute("imgUtil", new ImageUtil());
		session.setAttribute("username", session.getAttribute("username"));
		return "attendanceRegister";
	}
	//@CrossOrigin
		@ResponseBody
	    @GetMapping("/viewEmployeeDetailsBydepartment/{id}")
	    public List<AttendenceRegisterUtil>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
			
			
			Department d=departmentService.findDepartmentById(id);
			List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
			  List<AttendenceRegisterUtil> listAttendenceRegisterUtil = new ArrayList<AttendenceRegisterUtil>();
			  for (int i = 0; i < e.size(); i++) 
			  {
				  String empCode = e.get(i).getEmpCode();
				  AttendenceRegisterUtil lc = new AttendenceRegisterUtil();
				  Employee employee = employeeService.findEmployeeById(empCode);
				   Department department = departmentService.findDepartmentById(employee.getDepartmentCode());
				    Designation designation = designationService.findDesignationById(employee.getDesignationCode());
				
				  lc.setEmpCode(employee.getEmpCode());
				  lc.setEmpName(employee.getEmpName());
				  lc.setEmployeePayeCode(employee.getEmployeePayeeCode());
				  lc.setDepartmentCode(department.getDepartmentCode());
				  lc.setDeptName(department.getDeptName());
				  lc.setDesgCode(designation.getDesgCode());
				  lc.setDesgName(designation.getDesgName());
				  listAttendenceRegisterUtil.add(lc);
				  
				
			  }
			  return listAttendenceRegisterUtil;
	       
	    }

		
		@PostMapping("/saveAttendenceRegister")
		public String saveAttendenceRegister(@ModelAttribute("attendenceRegister")AttendenceRegisterUtil u, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
			String insertedBY = (String) session.getAttribute("USER_NAME");	
			AttendenceRegister attn=new AttendenceRegister();
			 Employee e=new    Employee();		
				Department d=new Department();
				int flag = 0;
				int counter = 1;
		
		try {
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr"));
			System.out.println("counter::::::::::::::::::::" + counter);
			for (int i =0; i < counter; i++) 
			{
				System.out.println("counter::::::::::::::::::::" + i);
				if (request.getParameter("status" + i) != null) {
					attn.setStatus(request.getParameter("status" + i));
				} else {
					attn.setStatus("" + i);
				}

				if (request.getParameter("timeIn" + i) != null) {
					String timeIn = request.getParameter("timeIn" + i);
					attn.setTimeIn(timeIn);
				} else {
					attn.setTimeIn("" + i);
				}

				if (request.getParameter("timeOut" + i) != null) {
					String sDate1 = request.getParameter("timeOut" + i);
					attn.setaTimeOut(sDate1);
				} else {
					attn.setaTimeOut("" + i);

				}

				if (request.getParameter("status2" + i) != null) {
					attn.setStatus2(request.getParameter("status2" + i));
				} else {
					attn.setStatus2("" + i);
				}

				if (request.getParameter("aTimeIn" + i) != null) {
					String aTimeIn = request.getParameter("aTimeIn" + i);
					attn.setaTimeIn(aTimeIn);
				} else {
					attn.setaTimeIn(" " + i);

				}

				if (request.getParameter("aTimeOut" + i) != null) {
					String aTimeOut = request.getParameter("aTimeOut" + i);
					attn.setaTimeOut(aTimeOut);
				} else {
					attn.setaTimeOut(" " + i);
				}

				if (request.getParameter("deptCode" + i) != null) {
					d.setDepartmentCode(request.getParameter("deptCode" + i));
					attn.setDepartment(d);

				}

				if (request.getParameter("empCode" + i) != null) {
					e.setEmpCode(request.getParameter("empCode" + i));
					attn.setEmployee(e);

				}

				if (request.getParameter("statusTemp" + i) != null) {
					attn.setStatus(request.getParameter("statusTemp" + i));
				} else {
					attn.setStatus("" + i);
				}
				
				attn.setAttendenceDate(u.getAttendenceDate());
				attn.setInsBy(insertedBY);
			insertStatusMR= attendenceRegisterService.addAttendenceRegister(attn);
				
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

			
		} catch (Exception x) {
			// TODO: handle exception
		}

		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/attendanceRegister";
		}
		
		
		@GetMapping(value = { "/deleteAttendanceRegister/{id}" })
		public String deleteAttendanceRegister(@PathVariable("id")int id , Model model, HttpSession session) {

		attendenceRegisterService.removeAttendanceRegister(id);

			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/attendanceRegister";
		
}
}