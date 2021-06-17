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
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.ImageUtil;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.EmployeeRequisitionDetail;
import com.hrms.model.MenuModule;
import com.hrms.model.TrainingRequisition;
import com.hrms.model.TrainingSchedule;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TrainingRequistionService;
import com.hrms.service.TrainingScheduleService;

@Controller
public class TrainingScheduleController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	TrainingRequistionService trainingRequistionService;
	@Autowired
	TrainingScheduleService trainingScheduleService;
	@Autowired EmployeeService employeeService;

	@InitBinder("trainingSchedule")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "trScheduleDate", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "dateTo", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "dateFrom", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "trReqDate", new CustomDateEditor(dateFormatter, true));

	}

	@GetMapping("/trainingSchedule")
	public String trainingSchedule(@ModelAttribute("trainingSchedule") TrainingSchedule trainingSchedule, Model model,
			HttpSession session) {

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<TrainingRequisition> listTrainingRequistion = trainingRequistionService.findTrainingRequisitionByStatusY();
		if (listTrainingRequistion != null) {
			model.addAttribute("listTrainingRequistion", listTrainingRequistion);
		}
		List<TrainingSchedule> listTrainingSchedule = trainingScheduleService.getAllTrainingSchedule();
		if (listTrainingSchedule != null) {
			model.addAttribute("listTrainingSchedule", listTrainingSchedule);
		}
		session.setAttribute("imgUtil", new ImageUtil());
		List<Employee> listEmployee = employeeService.getAllEmployees();
		if (listEmployee != null) {
			model.addAttribute("listEmployee", listEmployee);
		}
		
		
		return "trainingSchedule";

	}

	@PostMapping("saveTrainerSchedule")
	public String saveTrainerSchedule(@ModelAttribute("trainingSchedule") TrainingSchedule trainingSchedule,
			Model model, HttpSession session) {

		trainingScheduleService.saveTrainingSchedule(trainingSchedule);
		return "redirect:trainingSchedule";

	}

	@GetMapping("edittrainingSchedule/{trScheduleCode}")
	public String editTrainingSchedule(@PathVariable("trScheduleCode") String trScheduleCode,
			@ModelAttribute("trainingSchedule") TrainingSchedule trainingSchedule, Model model,HttpSession session) {

		TrainingSchedule trainingSchedule1 = trainingScheduleService.findTrainingScheduleById(trScheduleCode);
		if (trainingSchedule1 != null) {
			model.addAttribute("trainingSchedule", trainingSchedule1);
			System.out.println("=====>training id " + trainingSchedule1.getTrScheduleCode());
		}
		session.setAttribute("imgUtil", new ImageUtil());
		List<TrainingRequisition> listTrainingRequistion = trainingRequistionService.findTrainingRequisitionByStatusY();
		if (listTrainingRequistion != null) {
			model.addAttribute("listTrainingRequistion", listTrainingRequistion);
		}

		return "editTrainingSchedule";
	}

	@PostMapping(value = {"updateTrainerSchedule"})
	public String updateTrainingSchedule(@ModelAttribute("trainingSchedule") TrainingSchedule trainingSchedule,
			Model model,@RequestParam(name="trScheduleCode",required=false) String trScheduleCode) {

		 System.out.println("=====================>update employee Requisition "+
		 trScheduleCode);
		System.out.println("=====================>update employee Requisition " + trainingSchedule.getTrainer());

		trainingScheduleService.updateTrainingSchedule(trainingSchedule);
		return "redirect:trainingSchedule";
	}

	@GetMapping(value = { "deleteTrainingSchedule/{id}" })
	public String deleteTrainingSchedule(@PathVariable("id") String trScheduleCode, Model model, HttpSession session) {
		System.out.println("=====================>");

		trainingScheduleService.removeTrainingSchedule(trScheduleCode);

		// System.out.println("employee requisition id : "+ reqCode);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/trainingSchedule";
	}
}
