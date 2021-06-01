package com.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.hrms.model.ConveyanceExpenses;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;

import com.hrms.model.MenuModule;
import com.hrms.model.StayingExpenses;
import com.hrms.model.TourClaim;
import com.hrms.model.TourClaimUtil;
import com.hrms.model.TourPlan;
import com.hrms.model.TravelingExpenses;
import com.hrms.service.ConveyanceExpensesService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;
import com.hrms.service.ModuleService;
import com.hrms.service.StayingExpensesService;
import com.hrms.service.TourClaimService;
import com.hrms.service.TourPlanService;
import com.hrms.service.TravelingExpensesService;

@Controller
public class TourClaimController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	TourClaimService tourClaimService; 
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService  departmentService;
	@Autowired
	DesignationService designationService;
	@Autowired 
	TourPlanService tourPlanService;
	@Autowired
	TravelingExpensesService travelingExpensesService;
	@Autowired
	ConveyanceExpensesService conveyanceExpensesService;
	@Autowired
	StayingExpensesService stayingExpensesService;
	
	@GetMapping("/tourClaim")
	public String tourClaim(Model model, HttpSession session) {

	
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		 List<Employee> listEmployee = employeeService.getAllEmployees();
		  model.addAttribute("listEmployee", listEmployee);
		  List<TourPlan> ListTourPlan=tourPlanService.getAllTourPlan();
			model.addAttribute("ListTourPlan", ListTourPlan);
			
		session.setAttribute("username", session.getAttribute("username"));
		 return "tourClaim";
	}
	

	@PostMapping("/saveTourClaim")
	public String saveTourClaim(@ModelAttribute("tourClaim")TourClaimUtil tourClaimUtil, Model model, HttpSession session,HttpServletRequest request) throws ParseException {
		String insertedBY = (String) session.getAttribute("USER_NAME");
		TourClaim tourClaim= new TourClaim();
		TravelingExpenses travExp=new TravelingExpenses();
		ConveyanceExpenses convExp=new ConveyanceExpenses();
		StayingExpenses  stayingExpenses=new StayingExpenses();
		Employee emp = new Employee();
		emp.setEmpCode(tourClaimUtil.getEmpCode());
		tourClaim.setEmpCode(emp);
		
		TourPlan tourPl=new TourPlan();
		tourPl.setTourPlanId(tourClaimUtil.getTourPlanId());
		tourClaim.setTourPlanId(tourPl);
		
		tourClaim.setInsBy(insertedBY);
		tourClaim.setApprovalStatus("N");
		tourClaim.setTourClaimDate(tourClaimUtil.getTourClaimDate());
		tourClaim.setTourPlanDate(tourClaimUtil.getTourPlanDate());
		tourClaim.setStartPlace(tourClaimUtil.getStartPlace());
		tourClaim.setVisitPlace(tourClaimUtil.getVisitPlace());
		tourClaim.setAdvancePaid(tourClaimUtil.getAdvancePaid());
		tourClaim.setVisitPurpose(tourClaimUtil.getVisitPurpose());
		tourClaim.setTotalTravel(tourClaimUtil.getTotalTravel());
		this.tourClaimService.AddTourClaim(tourClaim);
		 int flag = 0;
	   		int counter = 1;
	   		
	   		String tourcId=tourClaim.getTourClaimId();
			Date tourCdate=tourClaimUtil.getTourClaimDate();
		try {
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr"));
			System.out.println("counter::::::::::::::::::::" + counter);
			for (int i =0; i < counter; i++) 
			{
			
				
				if (request.getParameter("startPlace" + i) != null) {
					travExp.setStartPlace(request.getParameter("startPlace" + i));
				} else {
					travExp.setStartPlace("" + i);
				}

				if (request.getParameter("visitPlace" + i) != null) {
					travExp.setVisitPlace(request.getParameter("visitPlace" + i));
				} else {
					travExp.setVisitPlace("" + i);
				}

				if (request.getParameter("fromDate" + i) != null) {
					String sDate1 = request.getParameter("fromDate" + i);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					travExp.setFromDate(date1);
				}

				if (request.getParameter("toDate" + i) != null) {
					String sDate1 = request.getParameter("toDate" + i);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					travExp.setToDate(date1);
				}

				if (request.getParameter("modeOfTravel" + i) != null) {
					travExp.setModeOfTravel(request.getParameter("modeOfTravel" + i));
				} else {
					travExp.setModeOfTravel("" + i);
				}
					
				
				if(request.getParameter("ticketNo" + i) != null) {
					travExp.setTicketNo(Integer.parseInt(request.getParameter("ticketNo" + i)));
				} else {
					travExp.setTicketNo(0 + i);
				}
				
				if(request.getParameter("paidCompany" + i) != null) {
					travExp.setPaidCompany(Integer.parseInt(request.getParameter("paidCompany" + i)));
				} else {
					travExp.setPaidCompany(0 + i);
				}
				
				
				if(request.getParameter("paidSelf" + i) != null) {
					travExp.setPaidSelf(Integer.parseInt(request.getParameter("paidSelf" + i)));
				} else {
					travExp.setPaidSelf(0 + i);
				}
				
				tourClaim.setTourClaimId(tourcId);
				tourClaim.setTourClaimDate(tourCdate);
				travExp.setTourClaimId(tourClaim);
				travExp.setTourClaimDate(tourClaim);
				travExp.setEmpCode(emp);
				
				
				insertStatusMR= travelingExpensesService.addTravelingExpenses(travExp);
				
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
			// TODO: handle exception
		}
		// second Counter Start Here >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		try {
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr2"));
			System.out.println("counter::::::::::::::::::::" + counter);
			for (int i =0; i < counter; i++) 
			{
			
				
				if (request.getParameter("startPlace" + i) != null) {
					convExp.setStartPlace(request.getParameter("startPlace" + i));
				} else {
					convExp.setStartPlace("" + i);
				}

				if (request.getParameter("visitPlace" + i) != null) {
					convExp.setVisitPlace(request.getParameter("visitPlace" + i));
				} else {
					convExp.setVisitPlace("" + i);
				}

				if (request.getParameter("fromDate" + i) != null) {
					String sDate1 = request.getParameter("fromDate" + i);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					convExp.setFromDate(date1);
				}

				if (request.getParameter("toDate" + i) != null) {
					String sDate1 = request.getParameter("toDate" + i);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					convExp.setToDate(date1);
				}

				if (request.getParameter("modeOfTravel" + i) != null) {
					convExp.setModeOfTravel(request.getParameter("modeOfTravel" + i));
				} else {
					convExp.setModeOfTravel("" + i);
				}
					
				
				if(request.getParameter("convExpAmount" + i) != null) {
					convExp.setConvExpAmount(Integer.parseInt(request.getParameter("convExpAmount" + i)));
				} else {
					convExp.setConvExpAmount(0 + i);
				}
				
				
				
				tourClaim.setTourClaimId(tourcId);
				tourClaim.setTourClaimDate(tourCdate);
				convExp.setTourClaimId(tourClaim);
				convExp.setTourClaimDate(tourClaim);
				convExp.setEmpCode(emp);
				
				
				insertStatusMR= conveyanceExpensesService.addConveyanceExpenses(convExp);
				
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
			// TODO: handle exception
		}
		//third counter
		
		try {
			boolean insertStatusMR = false;
			counter = Integer.parseInt(request.getParameter("_cr3"));
			System.out.println("counter::::::::::::::::::::" + counter);
			for (int i =0; i < counter; i++) 
			{
			
				
				if (request.getParameter("noDays" + i) != null) {
					stayingExpenses.setNoDays(request.getParameter("noDays" + i));
				} else {
					stayingExpenses.setNoDays("" + i);
				}

				if (request.getParameter("stayDate" + i) != null) {
					String sDate1 = request.getParameter("stayDate" + i);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					stayingExpenses.setStayDate(date1);
				}
				
				if(request.getParameter("ataAmt" + i) != null) {
					stayingExpenses.setAtaAmt(request.getParameter("ataAmt" + i));
				} else {
					stayingExpenses.setAtaAmt("" + i);
				}
				
				tourClaim.setTourClaimId(tourcId);
				tourClaim.setTourClaimDate(tourCdate);
				stayingExpenses.setTourClaimId(tourClaim);
				stayingExpenses.setTourClaimDate(tourClaim);
				stayingExpenses.setEmpCode(emp);
				
				
				insertStatusMR= stayingExpensesService.addStayingExpenses(stayingExpenses);
				
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
			// TODO: handle exception
		}
		
		
		
		
		//end 3 counter
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:/tourClaim";
		
		
		
	}


	//@CrossOrigin
	@ResponseBody
    @GetMapping("/viewEmployeeTourClaim/{id}")
    public TourClaimUtil  getempById(@PathVariable(value = "id") String id) {
		Employee e = employeeService.findEmployeeById(id);
		

		
		Department d = departmentService.findDepartmentById(e.getDepartmentCode());
		Designation des=designationService.findDesignationById(e.getDesignationCode());
		TourClaimUtil l=new TourClaimUtil(e.getEmpName(),d.getDeptName(),
				des.getDesgName());
		
        return l;
    }

}
