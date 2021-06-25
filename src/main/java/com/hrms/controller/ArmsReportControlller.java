package com.hrms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrms.model.ArmsLicenseDetails;
import com.hrms.reports.ArmsReport;
import com.hrms.service.ArmsLicenseService;

@Controller
public class ArmsReportControlller {
	
	@Autowired ArmsReport armsReport;
	@Autowired ArmsLicenseService armsLicenseService;
	
	@GetMapping("armsreport")
	public String armsLicensesReport(Model model ,HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		List<ArmsLicenseDetails> armsLicenseList = armsLicenseService.allArmsLicenseDetails();
		
		if(model != null) {
			model.addAttribute("armsLicenseList", armsLicenseList);
		}
		
		return "armsReport";
		
	}
	
	
	@GetMapping("createarmsreport")
	public String createArmsReport(@RequestParam("empCode") String empCode,
			HttpSession session,HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		System.out.println("Arms report : "+ empCode);
		if(session.getAttribute("username")==null) {
			return "redirect:" + "./";
		}
		
		
		if(empCode.equals("ALL")) {
			System.out.println("if block ");
			List<ArmsLicenseDetails> sourceData = armsLicenseService.allArmsLicenseDetails();
			
			System.out.println("source data : "+ sourceData.size());
			armsReport.createArmsLicensesReport(response, request, sourceData);
			
			
		}
		else {
			
			System.out.println("else block");
			List<ArmsLicenseDetails> sourceData = new ArrayList<>();
			ArmsLicenseDetails src =  armsLicenseService.findArmsLicenseById(empCode);
			sourceData.add(src);
			armsReport.createArmsLicensesReport(response, request, sourceData);
		}
		
		return "";
	}

}
