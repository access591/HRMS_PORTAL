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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrms.helper.InterviewApprovalUtil;
import com.hrms.helper.InterviewForm;
import com.hrms.model.ApplicantInfo;
import com.hrms.model.InterviewMaster;
import com.hrms.model.MenuModule;
import com.hrms.service.ApplicantInfoService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;

@Controller
public class InterviewController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired ApplicantInfoService applicantInfoService;
	@Autowired InterviewMasterService interviewMasterService;
	
	@InitBinder("form")
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");//
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "interviewDate",
                                    new CustomDateEditor(dateFormatter, true));
        binder.registerCustomEditor(Date.class, "applicantDate",
                new CustomDateEditor(dateFormatter, true));
//        binder.registerCustomEditor(Date.class, "reqTill",
//                new CustomDateEditor(dateFormatter, true));
//        binder.registerCustomEditor(Date.class, "reqDate",
//                new CustomDateEditor(dateFormatter, true));
       
    }
	
	//INTERVIEW DETAILS 
	
	@GetMapping("interviewDetails")
	public String interviewDetails(Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<ApplicantInfo> listApplicantInfo = applicantInfoService.getAllApplicantInfo();
		if(listApplicantInfo != null) {
			model.addAttribute("listApplicantInfo", listApplicantInfo);
		}
		List<InterviewMaster> im = new ArrayList<InterviewMaster>();
		InterviewForm form = new InterviewForm();
		form.setInterviewForm(im);
		model.addAttribute("interviewMaster", form);
		

		return "interviewDetails"; //interviewDetails.html
		
		
	}
	
	
	@PostMapping("addInterviewDetail")
	public String addInterviewDetails(@ModelAttribute("interviewMaster") InterviewForm form,HttpSession session) {
		
		System.out.println("===============>");
		System.out.println(form.getInterviewForm() != null ? form.getInterviewForm().size() : "null list");
		//InterviewMaster interMaster = form.getApplicantCode();
		if(form.getInterviewForm() != null ) {
			for(int i =0 ;i<form.getInterviewForm().size();i++) {
				
				InterviewMaster interviewMaster = form.getInterviewForm().get(i);
				interviewMasterService.addInterviewMaster(interviewMaster);
			}
			
		}
		else {
			System.out.println("else block ");
		}
		return "redirect:interviewDetails";
	}
	
	
	
	@GetMapping("interviewFinalSelection")
	public String interviewFinalSelection() {
		
		return "interviewFinalSelection";  //interviewFinalSelection.html
	}
	
	
	@ResponseBody
	@GetMapping("getApplicantDate/{id}")
	public ApplicantInfo getRequisitionDateByAdvtCode(@PathVariable("id")String advtCode) {
		//get advt code and advt date from Advertisement
		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(advtCode);
		//String advtDate = reqAdvertisement.getAdvtDate();
		System.out.println("======>>"+ applicantInfo.getApplicantDate());
		return applicantInfo;
	}
	
	
	//	INTERVIEW APPROVAL DETAILS
	
	
	@GetMapping("interviewApproval")
	public String interviewApprovalPage(Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		
		List<ApplicantInfo> listApplicantInfo = applicantInfoService.getAllApplicantInfo();
		List<InterviewApprovalUtil> listInterviewApprovalUtil = new ArrayList<InterviewApprovalUtil>();
		
		
		for(int i=0;i<listApplicantInfo.size();i++) {
			InterviewApprovalUtil interviewApprovalUtil = new InterviewApprovalUtil();
			
			interviewApprovalUtil.setApplicantCode(listApplicantInfo.get(i).getApplicantCode());
			interviewApprovalUtil.setApplicantDate(listApplicantInfo.get(i).getApplicantDate());
			interviewApprovalUtil.setApplicantName(listApplicantInfo.get(i).getApplicantName());
			interviewApprovalUtil.setApplicantSex(listApplicantInfo.get(i).getSex());
			
			listInterviewApprovalUtil.add(interviewApprovalUtil);
		}
		
		model.addAttribute("listInterviewApprovalUtil",listInterviewApprovalUtil);
		
		session.setAttribute("username", userCode);
		return "interviewApproval";
	}
	
	
	@GetMapping("approveInterview/{applicantCode}/{interviewStatus}")
	public String approveInterview(@PathVariable("interviewStatus") String interviewStatus,@PathVariable("applicantCode")
									String applicantCode) {
		
		System.out.println("applicant code : "+ applicantCode);
		
		System.out.println("applicant code : "+ interviewStatus);
		
		
		return "redirect:/interviewApproval";
	}
	

}
