package com.hrms.controller;

import java.util.List;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.EncryptionUtil;
import com.hrms.ImageUtil;
import com.hrms.model.AttendenceRegister;
import com.hrms.model.Employee;
import com.hrms.model.InterviewMaster;
import com.hrms.model.Login;
import com.hrms.model.MenuModule;
import com.hrms.model.OrderIssueTracking;
import com.hrms.model.TrackallEnquiries;
import com.hrms.model.UserEntity;
import com.hrms.service.AttendenceRegisterService;
import com.hrms.service.EmployeeService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;
import com.hrms.service.OrderIssueTrackingService;
import com.hrms.service.ReCaptchaValidationService;
import com.hrms.service.TrackallEnquiriesService;
import com.hrms.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	  private final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private ModuleService moduleService;
	

	@Autowired 
	 EmployeeService employeeService;
	@Autowired InterviewMasterService interviewMasterService;
	@Autowired AttendenceRegisterService attendenceRegisterService;
	@Autowired OrderIssueTrackingService orderIssueTrackingService;
	@Autowired TrackallEnquiriesService trackallEnquiriesService; 

	@Autowired
	private ReCaptchaValidationService validator;
	@GetMapping("/")
	public String index(Model model) {
		return "sign-in";
	}

	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute("user") Login login, Model model,
			@RequestParam(name = "g-recaptcha-response") String captcha, HttpSession session) {
		

		 //boolean captchaVerifyMessage = validator.validateCaptcha(captcha);
		 //System.out.println("captchaVerifyMessage>>>>>>>>>>>"+captchaVerifyMessage);


		try {
			List<Employee> listEmployee = employeeService.getAllEmployees();
			model.addAttribute("employeeList", listEmployee.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService.findTodayAttendenceList();
			if(listAttendenceRegister != null) {
				model.addAttribute("listAttendenceRegister", listAttendenceRegister.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<AttendenceRegister> findTodayLeave = attendenceRegisterService.findTodayLeaveEmployee();
			if(findTodayLeave != null) {
				model.addAttribute("findTodayLeave", findTodayLeave.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<InterviewMaster> listInterviewMaster = interviewMasterService.getFinalSelection();
			if(listInterviewMaster != null) {
				model.addAttribute("finalSelection", listInterviewMaster.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<OrderIssueTracking> orderService = orderIssueTrackingService.getAllOrderIssueTracking();
			if(orderService != null) {
				model.addAttribute("orderTracking", orderService.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			List<TrackallEnquiries> trackList =  trackallEnquiriesService.getAllTrackallEnquiries();
			if(trackList != null) {
				model.addAttribute("trackList", trackList.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("chart", new Object[][] {
		    {"Airline",     "Price $"},
		    {"Delta",       100},
		    {"Southwest",   500},
		    {"Jet Blue",    300},
		    {"Canada Air",  300},
		    {"Average month price", 300}
		});
		
		
		
		
		
		
		
        
 
        
		boolean isUserExist = userService.checkUserExists(login);


		if (isUserExist) {

			String id = login.getUserCode();
			UserEntity userRecord = userService.findDataById(id);
			session.setAttribute("uuuuu", userRecord.getUserName());
			session.setAttribute("USER_NAME", userRecord.getUserName());

			session.setAttribute("User_Profile_Pic", userRecord.getEmpCode().getImageProfile());
			session.setAttribute("imgUtil", new ImageUtil());
			session.setAttribute("username", login.getUserCode());
			String userCode = (String) session.getAttribute("username");
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			model.addAttribute("modules", modules);
			
			logger.debug("loginUser()");
			logger.info("loginUser()");
			return "dashboard";
			
			
			
		}
		else {
			model.addAttribute("message", "Please Verify Captcha");
			return "sign-in";
		}
	}
	

	
	@GetMapping("/userMaster")
	public String userMaster(Model model, HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:" + "./";
		}
		session.setAttribute("imgUtil", new ImageUtil());
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		List<UserEntity> listUsers = userService.getAllUsers();
		model.addAttribute("users", listUsers);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", session.getAttribute("username"));
		return "list-users";
	}

	/**
	 * 
	 * @param Method to be Save userEntity
	 * @param model
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") UserEntity userEntity, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		boolean isUserExist = userService.checkUserExistsOrNot(userEntity);
		String pass=EncryptionUtil.encode(userEntity.getUserPass());
		
		if (isUserExist) {
			redirectAttributes.addFlashAttribute("message", "User Code Already exists !  ");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/userMaster";
		} else {
			String username= (String)session.getAttribute("uuuuu");
			userEntity.setUserPass(pass);
			userEntity.setInsBy(username);
			userService.addUser(userEntity);
			session.setAttribute("username", session.getAttribute("username"));
			return "redirect:/userMaster";

		}

	}
	
	@GetMapping(value = { "/editUser/{id}" })
	public String editUser(@PathVariable("id") String id, Model model, HttpSession session) {
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		List<Employee> lrt = employeeService.getAllEmployees();
		model.addAttribute("listEmployee", lrt);
		UserEntity userEdit = userService.findUserById(id);
		String pass=EncryptionUtil.decode(userEdit.getUserPass());
		userEdit.setUserPass(pass);
		model.addAttribute("userEdit", userEdit);

		session.setAttribute("username", session.getAttribute("username"));
		return "/editUser";
	}

	@PostMapping("/upadteUser")
	public String updateUser(@ModelAttribute("userUpdate") UserEntity u, Model model, HttpSession session) {
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		String pass=EncryptionUtil.encode(u.getUserPass());
		u.setUserPass(pass);
		String username= (String)session.getAttribute("USER_NAME");
			u.setUpdBy(username);
		  this.userService.updateUser(u);
	  	  
		  return "redirect:/userMaster";
	}
	/**
	 * 
	 * @param Method  to Delete by id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteUser/{id}" })
	public String deleteUser(@PathVariable("id") String id, Model model, HttpSession session) {
		this.userService.removeUser(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/userMaster";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		session.invalidate();
		 return "redirect:" + "./";
		
	}
	
	@GetMapping("/dashboard")
	public String dashBoardMethod(Model model,HttpSession session) {
		
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		try {
			List<Employee> listEmployee = employeeService.getAllEmployees();
			model.addAttribute("employeeList", listEmployee.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<AttendenceRegister> listAttendenceRegister = attendenceRegisterService
				.findTodayAttendenceList();
		if(listAttendenceRegister != null) {
			model.addAttribute("listAttendenceRegister", listAttendenceRegister.size());
		}
		
		List<AttendenceRegister> findTodayLeave = attendenceRegisterService.findTodayLeaveEmployee();
		if(findTodayLeave != null) {
			model.addAttribute("findTodayLeave", findTodayLeave.size());
		}
		
		List<InterviewMaster> listInterviewMaster = interviewMasterService.getFinalSelection();
		
		if(listInterviewMaster != null) {
			model.addAttribute("finalSelection", listInterviewMaster.size());
		}
		
		try {
			List<OrderIssueTracking> orderService = orderIssueTrackingService.getAllOrderIssueTracking();
			if(orderService != null) {
				model.addAttribute("orderTracking", orderService.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			List<TrackallEnquiries> trackList =  trackallEnquiriesService.getAllTrackallEnquiries();
			if(trackList != null) {
				model.addAttribute("trackList", trackList.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "dashboard";
	
	}
	
	
	
	
	
}
