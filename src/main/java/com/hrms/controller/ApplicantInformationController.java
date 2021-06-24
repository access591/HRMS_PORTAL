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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.helper.Message1;
import com.hrms.model.ApplicantExpDetail;
import com.hrms.model.ApplicantInfo;
import com.hrms.model.City;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeRequisitionDetail;
import com.hrms.model.MenuModule;
import com.hrms.model.ReqAdvertisement;
import com.hrms.service.ApplicantInfoService;
import com.hrms.service.CityService;
import com.hrms.service.EmployeeRequisitionDetailService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.RequisitionAdvertisementService;

@Controller
public class ApplicantInformationController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private RequisitionAdvertisementService reqAdvertisementService;
	@Autowired
	private ApplicantInfoService applicantInfoService;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRequisitionDetailService employeeRequisitionDetailService;
	@Autowired
	private CityService cityService;

	@ModelAttribute
	public void commonData(Model model, HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		session.setAttribute("username", userCode);

	}

	@GetMapping("applicantInformation")
	public String applicantInformationPage(@ModelAttribute("applicantInfo") ApplicantInfo applicantInfo, Model model,
			HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		String userCode = (String) session.getAttribute("username");

		try {
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<ReqAdvertisement> listReqAdvertisement = reqAdvertisementService.getAllReqAdvertisement();
			if (listReqAdvertisement != null) {
				model.addAttribute("listReqAdvertisement", listReqAdvertisement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<EmployeeRequisitionDetail> listEmployeeRequisition1 = employeeRequisitionDetailService
				.findUniqueDesignation();
		if (listEmployeeRequisition1 != null) {

			model.addAttribute("listEmployeeRequisition", listEmployeeRequisition1);

		}

		try {
			List<City> cityList = cityService.getAllCities();
			System.out.println("city list size : " + cityList.size());
			if (cityList != null) {
				model.addAttribute("cityList", cityList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<Employee> listEmployee = employeeService.getAllEmployees();
			if (listEmployee != null) {
				model.addAttribute("listEmployee", listEmployee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("username", session.getAttribute("username"));
		return "applicantInformation"; // applicantInformation.html
	}

	@PostMapping("/saveApplicantInfo")
	public String saveApplicantInfo(@ModelAttribute("applicantInfo") ApplicantInfo applicantInfo, HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		try {
			if (applicantInfo.getApplicantExpDetail() != null) {
				for (ApplicantExpDetail aDetail : applicantInfo.getApplicantExpDetail()) {
					aDetail.setApplicantDate(applicantInfo.getApplicantDate());
					aDetail.setApplicantInfo(applicantInfo);
				}
			} else {
				ApplicantExpDetail e = new ApplicantExpDetail();
				e.setApplicantInfo(applicantInfo);
			}
			session.setAttribute("message",new Message1("Data has been saved Successfully","alert-primary"));
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message1("Something went Wrong !!","alert-info"));
		}
		

		applicantInfoService.addApplicantInfo(applicantInfo);
		return "redirect:applicantInformation";
	}

	@GetMapping("viewApplicantInfo/{applicantCode}")
	public String viewApplicantInfo(@PathVariable("applicantCode") String applicantCode, Model model,
			HttpSession session) throws Exception {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		List<ReqAdvertisement> listReqAdvertisement = reqAdvertisementService.getAllReqAdvertisement();
		if (listReqAdvertisement != null) {
			model.addAttribute("listReqAdvertisement", listReqAdvertisement);
		}

		List<EmployeeRequisitionDetail> listEmployeeRequisition1 = employeeRequisitionDetailService
				.findUniqueDesignation();
		if (listEmployeeRequisition1 != null) {
			System.out.println("designation name : " + listEmployeeRequisition1.get(0).getDesignation().getDesgName());
			model.addAttribute("listEmployeeRequisition", listEmployeeRequisition1);

		}

		System.out.println("execute code ");
		try {
			List<City> cityList = cityService.getAllCities();
			System.out.println("city list size : " + cityList.size());
			if (cityList != null) {
				model.addAttribute("cityList", cityList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<Employee> listEmployee = employeeService.getAllEmployees();
			if (listEmployee != null) {
				System.out.println("city list size : " + listEmployee.size());
				model.addAttribute("listEmployee", listEmployee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		model.addAttribute("applicantInfo", applicantInfo);

		return "viewApplicantInfo";
	}

	@ResponseBody
	@GetMapping("getAdvtDate/{id}")
	public String getRequisitionDateByAdvtCode(@PathVariable("id") String advtCode) {

		ReqAdvertisement reqAdvertisement = reqAdvertisementService.findReqAdvertisementById(advtCode);
		Date advtDate = reqAdvertisement.getAdvtDate();

		return advtDate.toString();
	}

	@InitBinder("applicantInfo")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "advtDate", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "applicantDate", new CustomDateEditor(dateFormatter, true));

	}

}
