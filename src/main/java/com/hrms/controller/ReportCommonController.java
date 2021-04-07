package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrms.model.LeaveDetail;
import com.hrms.service.LeaveDetailService;

@Controller
public class ReportCommonController {
	@Autowired
	LeaveDetailService leaveDetailService;
	
	@GetMapping(value = { "/reportLeaveDetail" })
	public String reportLeaveDetail(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		List<LeaveDetail> dataList = leaveDetailService.getAllLeaveDetails();

		String reportFileName = "";

		String val = null;
		if (request.getParameter("_ex") != null) {
			val = request.getParameter("_ex");
		}
		if (val.equals("P")) {
			System.out.println("heloo0000000000" + val);

			reportFileName = "leavedetail_pdf";
			leaveDetailService.leaveReportGenratepdf(request, response, reportFileName, dataList);
		} else if (val.equals("E")) {
			reportFileName = "bankwisereport_XLS";
			String filename = "bankwisereport";

		}
		session.setAttribute("username", session.getAttribute("username"));
		return null;
	}

}
