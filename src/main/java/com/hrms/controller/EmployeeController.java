package com.hrms.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.ImageUtil;
import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.Category;
import com.hrms.model.City;
import com.hrms.model.Country;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.util.EmployeeUtil;
import com.hrms.model.MenuModule;
import com.hrms.model.State;
import com.hrms.service.ArmsLicenseService;
import com.hrms.service.CategoryService;
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
	DepartmentService departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired
	PageMappingService pageMappingService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ArmsLicenseService armsLicenseService;
	@Autowired
	CityService cityService;
	@Autowired
	StateService stateService;
	@Autowired
	CountryService countryService;
	
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
/**
 * get All employee Details page 
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/employeeMaster")
	public ModelAndView employeeMaster(Model model, HttpSession session) {
   
		  List<City>CityList =cityService.getAllCities();
		  model.addAttribute("CityList", CityList);
		  List<State> listState = stateService.getAllStates();
		  model.addAttribute("listState", listState);
		  List<Country> listCountry = countryService.getAllCountrys();
		 model.addAttribute("listCountry", listCountry);
		
		 List<ArmsLicenseDetails>listArmsLicense=armsLicenseService.getAllArmsLicenses();
		model.addAttribute("listArmsLicense", listArmsLicense);
		
		List<EmployeeUtil> listEmployeeUtil= new ArrayList<EmployeeUtil>();
		EmployeeUtil empl;
		List<EmployeeUtil>listEmployee22= employeeService.getAllEmployeesAndArms();
		
		List<Employee>listEmployee2= employeeService.getAllEmployees();
		
		for(int i=0;i<listEmployee2.size();i++)
		{
		
			
			Department d=departmentService.findDepartmentById(listEmployee2.get(i).getDepartmentCode());
			Designation desig = designationService.findDesignationById(listEmployee2.get(i).getDesignationCode());
			ArmsLicenseDetails arms= armsLicenseService.findArmsByEmpEmpCode(listEmployee2.get(i).getEmpCode());
			Category cat=categoryService.findCategoryByCatId(listEmployee2.get(i).getCategoryCode());
	
			
			
			          empl = new EmployeeUtil(listEmployee2.get(i).getEmpCode(),
					                arms.getArmsCode(),
					  				listEmployee2.get(i).getEmpName(),
					  				cat.getCategoryName(),
					  				d.getDeptName(),
					  				desig.getDesgName(),
					  				listEmployee2.get(i).getImageProfile());
			                        listEmployeeUtil.add(empl);
			                       
			                   
			 
		}
		
		
		
		
		
		model.addAttribute("listEmployeeUtil", listEmployeeUtil);
		
		  List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory" ,listCategory);
		
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		ModelAndView modelAndView = new ModelAndView(pageMappingService.PageRequestMapping(reqPage, pageno));
	   
		modelAndView.addObject("imgUtil", new ImageUtil());
	 modelAndView.addObject("listEmployee", listEmployee);
		
	    return modelAndView;
	
	}
/**
 * Save Employee Page 
 * @param employee
 * @param model
 * @param session
 * @param multipartFile
 * @return
 */
	@PostMapping("/saveEmployee")
	public String employeeMasterSave(@ModelAttribute("employees")EmployeeUtil eutility , Model model, HttpSession session,@RequestParam("image") MultipartFile file,HttpServletRequest request)
	{
		ArmsLicenseDetails armsLicense =new ArmsLicenseDetails();
		Employee employee=new Employee();
		employee.setEmpName(eutility.getEmpName());
		
		employee.setDepartmentCode(eutility.getDepartmentCode());
		employee.setCategoryCode(eutility.getCategoryCode());
		employee.setDesignationCode(eutility.getDesignationCode());
		
		
		employee.setBatchYear(eutility.getBatchYear());
		employee.setDateOfJoining(eutility.getDateOfJoining());
		employee.setDateOfPosting(eutility.getDateOfPosting());
		employee.setDateOfRetirement(eutility.getDateOfRetirement());
		
		employee.setEmployeePayeeCode(eutility.getEmployeePayeeCode());
		
		employee.setPresentPosting(eutility.getPresentPosting());
		employee.setSuspention(eutility.getSuspention());
		
		employee.setTypeCourtDepartment(eutility.getTypeCourtDepartment());
		employee.setVigilanceQuery(eutility.getVigilanceQuery());
		employee.setVrs(eutility.getVrs());
		//step 1 complete==================================
		employee.setAadharNo(eutility.getAadharNo());
		employee.setAddCharge(eutility.getAddCharge());
		employee.setEmail(eutility.getEmail());
		
		employee.setGender(eutility.getGender());
		employee.setMartialStatus(eutility.getMartialStatus());
		employee.setTelephone(eutility.getTelephone());
		
		employee.setOnAdditionalCharge(eutility.getOnAdditionalCharge());
		employee.setOrderDate(eutility.getOrderDate());
		employee.setOrderNo(eutility.getOrderNo());
		
		employee.setPanNo(eutility.getPanNo());
		employee.setPinCode(eutility.getPinCode());
		employee.setQualification(eutility.getQualification());
		
		employee.setUan(eutility.getUan());
		employee.setUnderRule7(eutility.getUnderRule7());
		employee.setUnderRule8(eutility.getUnderRule8());
		
		employee.setCityCode(eutility.getCityCode());
		employee.setStateCode(eutility.getStateCode());
		employee.setCountryCode(eutility.getCountryCode());
		
		employee.setMobileNumber1(eutility.getMobileNumber1());
		employee.setMobileNumber2(eutility.getMobileNumber2());
		employee.setAddress1(eutility.getAddress1());
		employee.setAddress2(eutility.getAddress2());
		employee.setTransfer(eutility.getTransfer());
		//step 2 complete=new Requirement=================================

		employee.setPromotion(eutility.getPromotion());
		employee.setAcp(eutility.getAcp());
		employee.setApr(eutility.getApr());
		
		employee.setAcr(eutility.getAcr());
		employee.setTraining(eutility.getTraining());
		employee.setLtc(eutility.getLtc());
		
		employee.setLeaveAccount(eutility.getLeaveAccount());
		employee.setEmpAwards(eutility.getEmpAwards());
		employee.setOnDeputation(eutility.getOnDeputation());
		
		employee.setEmpDeputation(eutility.getEmpDeputation());
		employee.setPreviousPostings(eutility.getPreviousPostings());
		employee.setExpired(eutility.getExpired());
		
		employee.setEmpDob(eutility.getEmpDob());
		
		
		//step 2 complete==================================
		armsLicense.setName(eutility.getName());
		armsLicense.setFatherName(eutility.getFatherName());
		armsLicense.setAddressArms(eutility.getAddressArms());
		
		armsLicense.setDistrict(eutility.getDistrict());
		armsLicense.setState(eutility.getState());
		armsLicense.setArmsArea(eutility.getArmsArea());
		
		armsLicense.setDoi(eutility.getDoi());
		armsLicense.setDov(eutility.getDov());
		armsLicense.setToa(eutility.getToa());
		
		armsLicense.setTop(eutility.getTop());
		armsLicense.setArmsNol(eutility.getArmsNol());
		armsLicense.setLcd(eutility.getLcd());
		armsLicense.setDealerDetails(eutility.getDealerDetails());
		
		//step 3  complete==================================
	
		
		try {
			byte[] imageData = file.getBytes();
			employee.setImageProfile(imageData);
			employeeService.addEmployee(employee);
			String empCode= employee.getEmpCode();
			armsLicense.setEmpCode(empCode);
			armsLicenseService.addArmsLicenseDetails(armsLicense);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			session.setAttribute("username", session.getAttribute("username"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		model.addAttribute("imgUtil", new ImageUtil());
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}

	/**
	 * Edit Employee 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = {"/editEmployee/{id}/{id2}" })
	public String editEmployee(@PathVariable("id") String id,@PathVariable("id2") String id2, Model model, HttpSession session) {
		int editPageNo = 44;
		String reqPageedit = "/editEmployee";
		
		  List<City>CityList =cityService.getAllCities();
		  model.addAttribute("CityList", CityList);
		  List<State> listState = stateService.getAllStates();
		  model.addAttribute("listState", listState);
		  List<Country> listCountry = countryService.getAllCountrys();
		  model.addAttribute("listCountry", listCountry);
		
		
		List<Category> listCategory = categoryService.getAllCategory();
		model.addAttribute("listCategory" ,listCategory);
		List<Department> listDepartment = departmentService.getAllDepartments();
		model.addAttribute("listDepartment", listDepartment);
		List<Designation> listDesignation = designationService.getAllDesignations();
		model.addAttribute("listDesignation", listDesignation);
		
		Employee employeeEdit = employeeService.findEmployeeById(id);
		model.addAttribute("employeeEdit", employeeEdit);
		ArmsLicenseDetails armsLicenseEdit =armsLicenseService.findArmsLicenseById(id2);
		model.addAttribute("armsLicenseEdit", armsLicenseEdit);
		model.addAttribute("imgUtil", new ImageUtil());
		session.setAttribute("imgUtilSession",employeeEdit.getImageProfile());
		session.setAttribute("username", session.getAttribute("username"));
		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
/**
 * update employee
 * @param e
 * @param model
 * @param multipartFile
 * @return
 */
	@PostMapping("/updateEmployee")
	public String updatePageUrl(@ModelAttribute("employees") EmployeeUtil eutility, HttpSession session, Model model,@RequestParam("file") MultipartFile multipartFile) {
		byte[] imgUtilSession = (byte[]) session.getAttribute("imgUtilSession");
		ArmsLicenseDetails armsLicense =new ArmsLicenseDetails();
		Employee employee=new Employee();
		String empCode=eutility.getEmpCode();
		String[] empArray = empCode.split(",");
		String uniqueid = empArray [0];
		employee.setEmpCode(uniqueid);
		employee.setEmpCode(uniqueid);
         employee.setEmpName(eutility.getEmpName());
		
		employee.setDepartmentCode(eutility.getDepartmentCode());
		employee.setCategoryCode(eutility.getCategoryCode());
		employee.setDesignationCode(eutility.getDesignationCode());
		
		
		employee.setBatchYear(eutility.getBatchYear());
		employee.setDateOfJoining(eutility.getDateOfJoining());
		employee.setDateOfPosting(eutility.getDateOfPosting());
		employee.setDateOfRetirement(eutility.getDateOfRetirement());
		
		employee.setEmployeePayeeCode(eutility.getEmployeePayeeCode());
		
		employee.setPresentPosting(eutility.getPresentPosting());
		employee.setSuspention(eutility.getSuspention());
		
		employee.setTypeCourtDepartment(eutility.getTypeCourtDepartment());
		employee.setVigilanceQuery(eutility.getVigilanceQuery());
		employee.setVrs(eutility.getVrs());
		//step 1 complete==================================
		employee.setAadharNo(eutility.getAadharNo());
		employee.setAddCharge(eutility.getAddCharge());
		employee.setEmail(eutility.getEmail());
		
		employee.setGender(eutility.getGender());
		employee.setMartialStatus(eutility.getMartialStatus());
		employee.setTelephone(eutility.getTelephone());
		
		employee.setOnAdditionalCharge(eutility.getOnAdditionalCharge());
		employee.setOrderDate(eutility.getOrderDate());
		employee.setOrderNo(eutility.getOrderNo());
		
		employee.setPanNo(eutility.getPanNo());
		employee.setPinCode(eutility.getPinCode());
		employee.setQualification(eutility.getQualification());
		
		employee.setUan(eutility.getUan());
		employee.setUnderRule7(eutility.getUnderRule7());
		employee.setUnderRule8(eutility.getUnderRule8());
		
		employee.setCityCode(eutility.getCityCode());
		employee.setStateCode(eutility.getStateCode());
		employee.setCountryCode(eutility.getCountryCode());
		
		employee.setMobileNumber1(eutility.getMobileNumber1());
		employee.setMobileNumber2(eutility.getMobileNumber2());
		employee.setAddress1(eutility.getAddress1());
		employee.setAddress2(eutility.getAddress2());
		employee.setTransfer(eutility.getTransfer());
		
		//step 2 complete=new Requirement=================================

		employee.setPromotion(eutility.getPromotion());
		employee.setAcp(eutility.getAcp());
		employee.setApr(eutility.getApr());
		
		employee.setAcr(eutility.getAcr());
		employee.setTraining(eutility.getTraining());
		employee.setLtc(eutility.getLtc());
		
		employee.setLeaveAccount(eutility.getLeaveAccount());
		employee.setEmpAwards(eutility.getEmpAwards());
		employee.setOnDeputation(eutility.getOnDeputation());
		
		employee.setEmpDeputation(eutility.getEmpDeputation());
		employee.setPreviousPostings(eutility.getPreviousPostings());
		employee.setExpired(eutility.getExpired());
		
		employee.setEmpDob(eutility.getEmpDob());
		
		
		//step 2 complete==================================
		armsLicense.setArmsCode(eutility.getArmsCode());
		//armsLicense.setEmpCode(eutility.getEmpCode());
		armsLicense.setName(eutility.getName());
		armsLicense.setFatherName(eutility.getFatherName());
		armsLicense.setAddressArms(eutility.getAddressArms());
		
		armsLicense.setDistrict(eutility.getDistrict());
		armsLicense.setState(eutility.getState());
		armsLicense.setArmsArea(eutility.getArmsArea());
		
		armsLicense.setDoi(eutility.getDoi());
		armsLicense.setDov(eutility.getDov());
		armsLicense.setToa(eutility.getToa());
		
		armsLicense.setTop(eutility.getTop());
		armsLicense.setArmsNol(eutility.getArmsNol());
		armsLicense.setLcd(eutility.getLcd());
		armsLicense.setDealerDetails(eutility.getDealerDetails());
		
		
		
		
		try {

			byte[] imageData = multipartFile.getBytes();
			if(imageData.length==0)
			{
				employee.setImageProfile(imgUtilSession);
			}
			else
			{
			employee.setImageProfile(imageData);
			}
			armsLicenseService.updateArmsLicenseService(armsLicense);
			employeeService.updateEmployee(employee);
			

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		armsLicenseService.updateArmsLicenseService(armsLicense);
		employeeService.updateEmployee(employee);
		
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	/**
	 * 
	 * @param id delete employee 
	 * @param Emp_Img
	 * @param model
	 * @param session
	 * @return
	 */

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

}

