package com.hrms.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.model.City;
import com.hrms.model.Department;

import com.hrms.model.Employee;
import com.hrms.model.MenuModule;
import com.hrms.model.TrainingRegister;
import com.hrms.model.TrainingSchedule;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TrainingRegisterDetailsService;
import com.hrms.service.TrainingRegisterService;
import com.hrms.service.TrainingScheduleService;

import com.hrms.util.TrainingRegisterUtil;
@Controller
public class TrainingRegisterController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	TrainingRegisterDetailsService trainingRegisterDetailsService;
	@Autowired
	TrainingRegisterService trainingRegisterService;
	@Autowired
	TrainingScheduleService trainingScheduleService;
	@Autowired
	EmployeeService employeeService;

	@InitBinder("trainingRegister")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "trSchDate", new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "trRegDate", new CustomDateEditor(dateFormatter, true));
  
       
    }
	
	@GetMapping("/trainingRegister")
	public String trainingRegister(@ModelAttribute("trainingRegister")TrainingRegister trainingRegister, Model model, HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<TrainingSchedule> listTrainingSchedule = trainingScheduleService.getAllTrainingSchedule();
		if (listTrainingSchedule != null) {
			model.addAttribute("listTrSch", listTrainingSchedule);
		}
		
		List<TrainingRegister> listTrainingRegister=trainingRegisterService.getAllTrainingRegisters();
	    model.addAttribute("listTrainingRegister",listTrainingRegister);
	    
	   System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx"+listTrainingRegister);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		
		
		return "trainingRegister";

	}
	
	
	
	
	  //@CrossOrigin
	  
	  @ResponseBody
	  
	  @GetMapping("/viewTrSchudle/{id}")
	  public TrainingRegisterUtil geTrRegById(@PathVariable(value = "id") String id) { 

	  TrainingSchedule trainingSchedule1 =trainingScheduleService.findTrainingScheduleById(id);
	  
	  TrainingRegisterUtil l=new TrainingRegisterUtil();
	
	  l.setTrSchDate(trainingSchedule1.getTrScheduleDate());
	  l.setTopicSrlNo(trainingSchedule1.getTopicSerialNo());
	  l.setTrainer(trainingSchedule1.getTrainer());
	 
	  
	  return l; }
	  
	  
	  
		@PostMapping("/saveTrainingRegister")
		public String saveTrainingRegister(@ModelAttribute("trainingRegister")TrainingRegister trainingRegister, Model model, HttpSession session) {
			trainingRegisterService.addTrainingRegister(trainingRegister);
			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/trainingRegister";

		} 
		
		@GetMapping(value = { "/editTrainingRegister/{id}" })
		public String editCity(@PathVariable("id") String id, Model model, HttpSession session) {
			List<TrainingSchedule> listTrainingSchedule = trainingScheduleService.getAllTrainingSchedule();
			if (listTrainingSchedule != null) {
				model.addAttribute("listTrSch", listTrainingSchedule);
			}
			
			List<Department> listDepartment = departmentService.getAllDepartments();
			model.addAttribute("listDepartment", listDepartment);
			List<Employee> listEmployee = employeeService.getAllEmployees();
			model.addAttribute("listEmployee", listEmployee);
			TrainingRegister TrainingRegisterEdit = trainingRegisterService.findTrainingRegisterById(id);
			model.addAttribute("trainingRegister", TrainingRegisterEdit);
			

			return"editTrainingRegister";
		}
		
		
		
		@PostMapping(value = {"updateTrainingRegister"})
		public String updateTrainingRegister(@ModelAttribute("trainingRegister")TrainingRegister trainingRegister,
				Model model,@RequestParam(name="trRegCode",required=false) String trRegCode) {
System.out.println( "xx>>>>>>>>>>>>>>>>>>>>>"+trainingRegister.getTrRegCode());
			trainingRegisterService.updateTrainingRegister(trainingRegister);
			return "redirect:/trainingRegister";
		}
		
		
		@GetMapping(value = { "/deleteTrainingRegister/{id}" })
		public String deleteTrainingRegister(@PathVariable("id") String id, Model model,HttpSession session) {
			try {
				
				trainingRegisterService.removeTrainingRegister(id);
				session.setAttribute("username", session.getAttribute("username"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/trainingRegister";
		}
	  
	 
}
