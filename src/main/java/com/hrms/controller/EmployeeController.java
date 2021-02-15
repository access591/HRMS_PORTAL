package com.hrms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;

@Controller
public class EmployeeController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employeeMaster")
	public String employeeMaster(Model model, HttpSession session) {

		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "empInfoMaster";
	}

	@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employees") Employee employee, Model model, HttpSession session,
			@RequestParam("file") MultipartFile multipartFile) {

		try {
			UUID uuid=UUID.randomUUID();
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			employee.setEmp_Img(uuid.toString().substring(0, 12)+"_"+fileName);

			String folderPath = "\\src\\main\\resources\\static\\img\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			//String path = Paths.get(uploadDir + multipartFile.getOriginalFilename()).toString();
			String path = Paths.get(uploadDir + employee.getEmp_Img()).toString();

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			stream.write(multipartFile.getBytes());
			stream.close();

			employeeService.addEmployee(employee);
			session.setAttribute("username", session.getAttribute("username"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "redirect:/employeeMaster";

	}

	@GetMapping(value = { "/deleteEmployee/{id}/{Emp_Img}" })
	public String deleteUserRights(@PathVariable("id") String id, @PathVariable("Emp_Img") String Emp_Img, Model model,
			HttpSession session) {
		try {

			this.employeeService.removeEmployeet(id);
			String folderPath = "\\src\\main\\resources\\static\\img\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			File file = new File(uploadDir + Emp_Img);

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!"); //
			} else {
				System.out.println("Delete operation is failed.");

			}

			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/employeeMaster";
	}

	@GetMapping(value = { "/editEmployee/{id}" })
	public String editEmployee(@PathVariable("id") String id, Model model, HttpSession session) {

		Employee employeeEdit = employeeService.findEmployeeById(id);
		model.addAttribute("employeeEdit", employeeEdit);

		session.setAttribute("username", session.getAttribute("username"));
		return "/editEmployee";
	}

	@PostMapping("/updateEmployee")
	public String updatePageUrl(@ModelAttribute("employees") Employee e, Model model,@RequestParam("file") MultipartFile multipartFile) {
		String Emp_Img = e.getEmp_Img().toString();

		try {

			UUID uuid = UUID.randomUUID();
			String folderPath = "\\src\\main\\resources\\static\\img\\";
			String uploadDir = System.getProperty("user.dir") + folderPath;
			File file = new File(uploadDir + Emp_Img);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

			if (fileName.trim().length() > 0) {
				file.delete();
				e.setEmp_Img(uuid.toString().substring(0, 12) + "_" + fileName);
				String path = Paths.get(uploadDir + e.getEmp_Img()).toString();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				stream.write(multipartFile.getBytes());
				stream.close();
				this.employeeService.updateEmployee(e);

			} else if (Emp_Img != null) {
				e.setEmp_Img(Emp_Img);
				this.employeeService.updateEmployee(e);
			}

			this.employeeService.updateEmployee(e);

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.employeeService.updateEmployee(e);
		return "redirect:/employeeMaster";
	}
}

