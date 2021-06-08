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

import com.hrms.model.Category;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.model.Loan;
import com.hrms.model.LtaRequest;
import com.hrms.model.MenuModule;
import com.hrms.model.TrackallEnquiries;
import com.hrms.service.CategoryService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.TrackallEnquiriesService;

@Controller
public class TrackallEnquiriesController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DesignationService designationService;
	@Autowired CategoryService categoryService;
	@Autowired TrackallEnquiriesService trackallEnquiriesService;
	
	
	@InitBinder("trackallEnquiries")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
                binder.registerCustomEditor(Date.class, "dob",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "deputationDate", new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "underRule8Period",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "underRule8Date",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "vigilanceInqPeriod",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "vigilanceInqDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "suspentionFilePeriod",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "suspentionFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "promtionFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "acpFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "arpFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "acrFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "trainingFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "ltcFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "leaveAccFileDate",new CustomDateEditor(dateFormatter, true));
				binder.registerCustomEditor(Date.class, "awardDate",new CustomDateEditor(dateFormatter, true));
			
       
    }
	@GetMapping("/trackallEnquiries")
	public String trackallEnquiries(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory" ,listCategory);
		}
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		List<TrackallEnquiries>listTrackallEnquiries=trackallEnquiriesService.getAllTrackallEnquiries();
		model.addAttribute("listTrackallEnquiries", listTrackallEnquiries);
		
		return "trackallEnquiries";
		
	}
	
	@PostMapping("/saveTrackallEnquiries")
	public String saveTrackallEnquiries(@ModelAttribute("trackallEnquiries") TrackallEnquiries trackallEnquiries, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("USER_NAME");
		trackallEnquiries.setInsBy(insertedBY);
		trackallEnquiriesService.addTrackallEnquiries(trackallEnquiries);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/trackallEnquiries";

	}
	
	
	@GetMapping(value = {"/editTrackallEnquiries/{id}"})
	public String editLtaRequest(@PathVariable("id")long id,  Model model,HttpSession session)
	 { 
		
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", listEmployee);
		List<Category> listCategory = categoryService.getAllCategory();
		if(listCategory != null) {
			model.addAttribute("listCategory" ,listCategory);
		}
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		TrackallEnquiries trackallEnquiriesEdit =trackallEnquiriesService.findByIdTrackallEnq(id);
		  model.addAttribute("trackallEnquiriesEdit", trackallEnquiriesEdit);
	   
	    return "editTrackallEnquiries";
	}

	@PostMapping("/updateTrackallEnquiries")
	public String updateTrackallEnquiries(@ModelAttribute("trackallEnquiries")  TrackallEnquiries trackallEnquiries, HttpSession session,Model model) {
		String updatedBY = (String) session.getAttribute("USER_NAME");
		trackallEnquiries.setUpdBy(updatedBY);
		this.trackallEnquiriesService.updateTrackallEnquiries(trackallEnquiries);

		return "redirect:/trackallEnquiries";

	}

	
	@GetMapping(value = {"/deleteTrackallEnquiries/{id}"})
	public String deleteTrackallEnquiries(@PathVariable("id")Long id,  Model model,HttpSession session)
	 { 
		  this.trackallEnquiriesService.removeTrackallEnquiries(id);
			return "redirect:/trackallEnquiries";

	}
	
}
