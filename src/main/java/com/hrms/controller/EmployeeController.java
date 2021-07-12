package com.hrms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.helper.FileService;
import com.hrms.model.ArmsLicenses;
import com.hrms.model.ArmsLicensesDetail;
import com.hrms.model.City;
import com.hrms.model.Country;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.util.EmployeeDto;
import com.hrms.util.EmployeeUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.repository.ArmsLicenseDao;
import com.hrms.service.ArmsLicenseService;
import com.hrms.service.CityService;
import com.hrms.service.CountryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.StateService;

@Controller
public class EmployeeController {

	int pageno = 43;
	String reqPage = "/employeeMaster";

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DesignationService designationService;
	@Autowired
	private PageMappingService pageMappingService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ArmsLicenseService armsLicenseService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private ArmsLicenseDao armsLicenseDao;

	@GetMapping("/employeeMaster")
	public String employeeMaster(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model,
			HttpSession session) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		List<Designation> listDesignation = designationService.getAllDesignations();
		if (listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);

		List<City> cityList = cityService.getAllCities();
		model.addAttribute("CityList", cityList);

		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);

		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);

		model.addAttribute("employeeDto", employeeDto);

		List<ArmsLicenses> listArmsLicenses = armsLicenseService.getAllArmsLicenses();
		model.addAttribute("listArmsLicenses", listArmsLicenses);

		return "employeeMaster";
	}

	@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model,
			HttpSession session,@RequestParam("profileImage") MultipartFile file) {

		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}

		System.out.println("hiii");

		try {

			Employee employee = employeeDto.getEmployee();
			
			if(file.isEmpty()) {
				employee.setProfilePic("default.png");
			}else {
				
				employee.setProfilePic(file.getOriginalFilename()+new Date());
				
				File saveFileFolder = new ClassPathResource("static/img/").getFile();
				String uploadDir = saveFileFolder.getAbsolutePath();
				FileService.saveFile(uploadDir, file.getOriginalFilename()+new Date(), file);
				
				Employee empl = employeeService.addEmployee(employee);
				System.out.println("========employee tsting saving or not ===========" + empl.getEmpCode());

				ArmsLicenses armsLicenses = employeeDto.getArmsLicenses();

				armsLicenses.setArmsCode(armsLicenseDao.getMaxId("ARM"));
				armsLicenses.setEmployee(empl);

				List<ArmsLicensesDetail> armsLicensesDetail = employeeDto.getArmsLicensesDetail();

				for (ArmsLicensesDetail licensesDetail : armsLicensesDetail) {

					licensesDetail.setArmsLicenses(armsLicenses);
				}
				armsLicenses.setArmsLicensesDetail(armsLicensesDetail);

				employee.setArmLicense(armsLicenses);

				armsLicenseService.addArmsLicenseDetails(armsLicenses);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:employeeMaster";

	}

	@GetMapping(value = { "/editEmployee/{id}" })
	public String editEmployee(@PathVariable("id") String id,
			@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model, HttpSession session) {

		
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		List<City> cityList = cityService.getAllCities();
		model.addAttribute("CityList", cityList);

		List<State> listState = stateService.getAllStates();
		model.addAttribute("listState", listState);

		List<Country> listCountry = countryService.getAllCountrys();
		model.addAttribute("listCountry", listCountry);
		
		List<Designation> listDesignation = designationService.getAllDesignations();
		if (listDesignation != null) {
			model.addAttribute("listDesignation", listDesignation);
		}
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		

		ArmsLicenses armLicenses = armsLicenseService.findArmsLicensesByEmployee(id);

		employeeDto.setEmployee(armLicenses.getEmployee());
		employeeDto.setArmsLicenses(armLicenses);

		employeeDto.setArmsLicensesDetail(armLicenses.getArmsLicensesDetail());
		model.addAttribute("employeeDto", employeeDto);

		return "editEmployee";
	}

	@PostMapping("/updateEmployee")
	public String updatePageUrl(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto, Model model,
			HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
//		System.out.println("arms license : id "+);
		ArmsLicenses armsLicenses = employeeDto.getArmsLicenses();

		// armsLicenses.setArmsCode(armsLicenseDao.getMaxId("ARM"));
		armsLicenses.setEmployee(employeeDto.getEmployee());

		List<ArmsLicensesDetail> armsLicensesDetail = employeeDto.getArmsLicensesDetail();

		for (ArmsLicensesDetail licensesDetail : armsLicensesDetail) {

			licensesDetail.setArmsLicenses(armsLicenses);
		}
		armsLicenses.setArmsLicensesDetail(armsLicensesDetail);
		armsLicenseService.updateArmsLicenseService(armsLicenses);
		

		return "redirect:employeeMaster";
	}

	@GetMapping(value = { "/deleteEmployee/{id}/{id2}" })
	public String deleteEmployee(@PathVariable("id") String id, @PathVariable("id2") String id2, Model model,
			HttpSession session) {
		try {

			armsLicenseService.removeArmsLicenseService(id2);
			session.setAttribute("username", session.getAttribute("username"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		employeeService.removeEmployeet(id);
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	@ResponseBody
	@GetMapping("/viewStateByCountryCode/{id}")
	public List<State> getStateCountryById(@PathVariable(value = "id") String id, Model model, HttpSession session) {
		List<State> e = stateService.findStateByCountry(id);
		List<State> lisStateUtil = new ArrayList<>();
		for (int i = 0; i < e.size(); i++) {
			State st = new State();
			st.setStateCode(e.get(i).getStateCode());
			st.setStateName(e.get(i).getStateName());
			lisStateUtil.add(st);
		}
		return lisStateUtil;

	}

	@ResponseBody
	@GetMapping("/viewCityBySatateCode/{id}")
	public List<City> viewCityBySatateCode(@PathVariable(value = "id") String stateCode, Model model,
			HttpSession session) {
		List<City> y = cityService.findCityByState(stateCode);
		List<City> lisCityUtil = new ArrayList<>();
		for (int i = 0; i < y.size(); i++) {
			City ct = new City();
			ct.setCityCode(y.get(i).getCityCode());
			ct.setCityName(y.get(i).getCityName());
			lisCityUtil.add(ct);
		}
		return lisCityUtil;
	}

}
