package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.model.Leave;
import com.hrms.model.LeaveDetail;
import com.hrms.model.MenuModule;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;

@Controller
public class LeaveDetailController {
	int pageno = 45;
	String reqPage = "/leaveDetailMaster";
	@Autowired
	LeaveService leaveService;
	@Autowired
	PageMappingService pageMappingService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	LeaveDetailService leaveDetailService;

	/**
	 * Get All Leave Details list
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/leaveDetailMaster")
	public String leaveDetailMaster(Model model, HttpSession session) {
		List<LeaveDetail> listLeaveDetail = leaveDetailService.getAllLeaveDetails();
		model.addAttribute("listLeaveDetail", listLeaveDetail);
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	/**
	 * save leave Details
	 * 
	 * @param leaveDetail
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveLeaveDetail")
	public String saveLeaveDetail(@ModelAttribute("leaveDetail") LeaveDetail leaveDetail, Model model,
			HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		leaveDetail.setInsBy(insertedBY);
		leaveDetailService.addLeaveDetail(leaveDetail);

		session.setAttribute("username", session.getAttribute("username"));

		 return "redirect:"+pageMappingService.PageRequestMapping(reqPage,pageno);

	}

	/**
	 * Find Record And Set update form leave page
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editLeaveDetail/{id}" })
	public String editLeaveDetail(@PathVariable("id") String id, Model model, HttpSession session) {
		int editPageNo = 46;
		String reqPageedit = "/editLeaveDetail";
		List<Leave> listLeave = leaveService.getAllLeaves();
		model.addAttribute("listLeave", listLeave);
		LeaveDetail leaveDetailEdit = leaveDetailService.findLeaveDetailById(id);
		model.addAttribute("leaveDetailEdit", leaveDetailEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}

	/**
	 * 
	 * @param update  leave details
	 * @param model
	 * @param session
	 * @return
	 */

	@PostMapping("/updateLeaveDetail")
	public String updateLeaveDetail(@ModelAttribute("leaveDetail") LeaveDetail c, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		c.setUpdBy(updatedBY);
		this.leaveDetailService.updateLeaveDetail(c);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

	/**
	 * delete leave Details
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteLeaveDetail/{id}" })
	public String deleteLeaveDetail(@PathVariable("id") String id, Model model, HttpSession session) {
		this.leaveDetailService.removeLeaveDetail(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	

	
}
