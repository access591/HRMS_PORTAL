package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hrms.ImageUtil;
import com.hrms.model.Department;
import com.hrms.model.Employee;

import com.hrms.model.EmployeeTransfer;
import com.hrms.model.MenuModule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.EmployeeTransferService;
import com.hrms.service.ModuleService;

@Controller
public class EmployeeTransferController {
	@Autowired EmployeeService employeeService;
	@Autowired DepartmentService departmentService;
	@Autowired private ModuleService moduleService;
	@Autowired EmployeeTransferService  employeeTransferService;
	
	@GetMapping("/employeeTransfer")
	public String employeePromotion(Model model,HttpSession session)
	{
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		List<EmployeeTransfer> listEmployeeTransfer=employeeTransferService.getAllEmployeeTransfer();
	     model.addAttribute("listEmpTrns", listEmployeeTransfer);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		String userCode= (String)session.getAttribute("username");
		session.setAttribute("imgUtil", new ImageUtil());
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		
		return "employeeTransfer";
	}
		
	
	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeTrnsBydepartment/{id}")
    public List<Employee>  getLocalConvyenceById(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		Department d=departmentService.findDepartmentById(id);
		List<Employee> e = employeeService.findByDepartmentCode(d.getDepartmentCode());
		  List<Employee> lisEmpUtil = new ArrayList<>();
		  for (int i = 0; i < e.size(); i++) 
		  {
			  String empCode = e.get(i).getEmpCode();
			  Employee lc = new Employee();
			  Employee employee = employeeService.findEmployeeById(empCode);
			  lc.setEmpCode(employee.getEmpCode());
			  lc.setEmpName(employee.getEmpName());
			  lisEmpUtil.add(lc);
		  }
		  return lisEmpUtil;
       
    }
	
	@ResponseBody
    @GetMapping("/viewEmployeeTrnsByEmployee/{id}")
    public Employee  viewOverTimeRegisterByEmployee(@PathVariable(value = "id") String id,Model model,HttpSession session) {
		
		Employee employee = employeeService.findEmployeeById(id);
		Employee listEmp = new Employee();
			  
			 
			 
			  listEmp.setEmpCode(employee.getEmpCode());
			  listEmp.setEmpName(employee.getEmpName());
			  
		
		  return listEmp;
       
    }
	
	@PostMapping("/saveEmployeeTransfer")
	public String saveEmployeeTransfer(@ModelAttribute("employeeTrans")EmployeeTransfer employeeTrns , Model model, HttpSession session, MultipartFile file,HttpServletRequest request)
	{
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		
		Employee emp=new Employee();
		
		 int flag = 0;
	   		int counter = 1;
			try {
				UUID uuid=UUID.randomUUID();
			
				
				
				boolean insertStatusMR = false;
				counter = Integer.parseInt(request.getParameter("_cr"));
				System.out.println("counter::::::::::::::::::::" + counter);
				for (int i =0; i < counter; i++) 
				{
					
			try {
			            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			            List<MultipartFile> multipartFileList = multipartRequest.getFiles("empTransferDoc" + i);
			
			            if (null != multipartFileList && !multipartFileList.isEmpty()) {
			
			                for (MultipartFile fileS : multipartFileList) {
			                  //  String fileName = fileS.getOriginalFilename().trim();
			                    if (fileS.getBytes().length > 0) {
			                    	
			                    	employeeTrns.setEmpTransferDoc(uuid.toString().substring(0, 12)+"_"+fileS.getOriginalFilename());
			                    	String folderPath = "\\src\\main\\resources\\static\\employeedoc\\";
			            			String uploadDir = System.getProperty("user.dir") + folderPath;
			            			String path = Paths.get(uploadDir + employeeTrns.getEmpTransferDoc()).toString();
			            			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			            			stream.write(fileS.getBytes());
			            			stream.close();
			                    	
			                    
			                    }
			
			                }
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
					
			
					if (request.getParameter("transferDate" + i) != null) {
						String sDate1 = request.getParameter("transferDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						employeeTrns.setTransferDate(date1);
					}
					
					if (request.getParameter("fromDate" + i) != null) {
						String sDate1 = request.getParameter("fromDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						employeeTrns.setFromDate(date1);
					}
					
					if (request.getParameter("toDate" + i) != null) {
						String sDate1 = request.getParameter("toDate" + i);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						employeeTrns.setToDate(date1);
					}
					
					 
					if(request.getParameter("empCode2" + i) != null) {
						emp.setEmpCode(request.getParameter("empCode2" + i));
						employeeTrns.setEmpCode(emp);
					} 
					
					
					insertStatusMR= employeeTransferService.addEmployeeTransfer(employeeTrns);	
					
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
			session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/employeeTransfer";
		
		
	}
	
}
