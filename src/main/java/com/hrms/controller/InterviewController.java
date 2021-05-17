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

import com.hrms.model.ApplicantInfo;
import com.hrms.model.Designation;
import com.hrms.model.InterviewMaster;
import com.hrms.model.MenuModule;
import com.hrms.service.ApplicantInfoService;
import com.hrms.service.DesignationService;
import com.hrms.service.InterviewMasterService;
import com.hrms.service.ModuleService;
import com.hrms.util.InterviewApprovalUtil;
import com.hrms.util.InterviewFinalSelectionUtil;
import com.hrms.util.InterviewForm;

@Controller
public class InterviewController {
	
	@Autowired
	private ModuleService moduleService;
	@Autowired ApplicantInfoService applicantInfoService;
	@Autowired InterviewMasterService interviewMasterService;
	@Autowired DesignationService designationService;
	
	
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
		List<ApplicantInfo> listApplicantInfo1 = applicantInfoService.getAllApplicantInfo();
		List<ApplicantInfo> listApplicantInfo = new ArrayList<ApplicantInfo>();
		for(int i=0;i<listApplicantInfo1.size();i++) {
			
			if(listApplicantInfo1.get(i).getInterStatus().startsWith("F")) {
				listApplicantInfo.add(listApplicantInfo1.get(i));
			}
			
		}
		if(listApplicantInfo != null) {
			model.addAttribute("listApplicantInfo", listApplicantInfo);
		}
		List<InterviewMaster> im = new ArrayList<InterviewMaster>();
		InterviewForm form = new InterviewForm();
		form.setInterviewForm(im);
		model.addAttribute("interviewMaster", form);
		

		session.setAttribute("username", userCode);
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
	
	
	@GetMapping("viewApplicantInfo/{applicantCode}")
	public String viewApplicantInfo(@PathVariable("applicantCode") String applicantCode , Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		model.addAttribute("applicantInfo", applicantInfo);
		
		return "viewApplicantInfo";
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
			
			if(listApplicantInfo.get(i).getInterStatus().equals("") || listApplicantInfo.get(i).getInterStatus().startsWith("H"))
			{
				Designation designation = designationService.findDesignationById(listApplicantInfo.get(i).getDesigCode());
				
				InterviewApprovalUtil interviewApprovalUtil = new InterviewApprovalUtil();
				
				interviewApprovalUtil.setApplicantCode(listApplicantInfo.get(i).getApplicantCode());
				interviewApprovalUtil.setApplicantDate(listApplicantInfo.get(i).getApplicantDate());
				interviewApprovalUtil.setApplicantName(listApplicantInfo.get(i).getApplicantName());
				interviewApprovalUtil.setApplicantSex(listApplicantInfo.get(i).getSex());
				interviewApprovalUtil.setDesignationCode(designation.getDesgCode());
				interviewApprovalUtil.setDesignationName(designation.getDesgName());
				listInterviewApprovalUtil.add(interviewApprovalUtil);
			}
			
			
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
		
		
		//ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		applicantInfoService.updateApplicantInfoInterviewStatus(applicantCode, interviewStatus);
		
		
		
		return "redirect:/interviewApproval";
	}
	
	
	//	INTERVIEW FINAL SELECTION
	
	@GetMapping("interviewFinalSelection")
	public String interviewFinalSelection(Model model,HttpSession session) {
		
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		
		List<InterviewMaster> listInterviewmaster =  interviewMasterService.getAllInterviewMaster();
		
		List<InterviewFinalSelectionUtil> interviewFinalSelection = new ArrayList<InterviewFinalSelectionUtil>();
		for(InterviewMaster interView : listInterviewmaster) {
			
			
			InterviewFinalSelectionUtil ifs = new InterviewFinalSelectionUtil();
			ifs.setInterviewCode(interView.getInterviewCode());
			ifs.setInterviewDate(interView.getInterviewDate());
			ifs.setApplicantCode(interView.getApplicantCode());
			ifs.setApplicantDate(interView.getApplicantDate());
			ifs.setOvarAllRating(interView.getOverAllRating());
			
			if(interView.getSelectionStatus() != null || interView.getSelectionStatus() != "") {
				ifs.setSelectionStatus(interView.getSelectionStatus());
			}
			
			
			ApplicantInfo applicantinfo = applicantInfoService.getApplicantInfoByApplicantCode(interView.getApplicantCode());
			ifs.setApplicantName(applicantinfo.getApplicantName());
			ifs.setCurrentCtc(applicantinfo.getCurrentCtc());
			ifs.setExpectedCtc(applicantinfo.getExpectedCtc());
			
			
			interviewFinalSelection.add(ifs);
		}
		
		
		
		model.addAttribute("interviewFinalSelection", interviewFinalSelection);
		session.setAttribute("username", userCode);
		return "interviewFinalSelection";  //interviewFinalSelection.html
	}
	
	
	@GetMapping("interviewFileApproval/{applicantCode}/{interviewCode}/{finalApprovalStatus}")
	public String interviewFinalApproval(@PathVariable("finalApprovalStatus") String finalApprovalStatus,@PathVariable("applicantCode") String applicantCode,
										@PathVariable("interviewCode") String interviewCode,HttpSession session) {
		
		
		interviewMasterService.interviewFinalapproval(applicantCode, interviewCode, finalApprovalStatus);
		return "redirect:/interviewFinalSelection";
	}
	

}
