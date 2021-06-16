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

import com.hrms.ImageUtil;
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
	@Autowired
	ApplicantInfoService applicantInfoService;
	@Autowired
	InterviewMasterService interviewMasterService;
	@Autowired
	DesignationService designationService;

	@InitBinder("form")
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");//
		dateFormatter.setLenient(false);
		binder.registerCustomEditor(Date.class, "interviewDate", new CustomDateEditor(dateFormatter, true));
		binder.registerCustomEditor(Date.class, "applicantDate", new CustomDateEditor(dateFormatter, true));

	}
	
	@ModelAttribute
	public void commonData(Model model,HttpSession session) {
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		session.setAttribute("username", userCode);
	}

	// INTERVIEW DETAILS

	@GetMapping("interviewDetails")
	public String interviewDetails(Model model, HttpSession session) {
		session.setAttribute("imgUtil", new ImageUtil());
		
		List<ApplicantInfo> listApplicantInfo1 = applicantInfoService.getAllApplicantInfo();
		List<ApplicantInfo> listApplicantInfo = new ArrayList<>();
		for (int i = 0; i < listApplicantInfo1.size(); i++) {
			
			if (listApplicantInfo1.get(i).getInterStatus() == null) {
				
				
			}

			else if (listApplicantInfo1.get(i).getInterStatus().length() == 0) {
				
				

			} else {

				if (listApplicantInfo1.get(i).getInterStatus().startsWith("F")) {
					listApplicantInfo.add(listApplicantInfo1.get(i));
				}
			}

		}
		if (listApplicantInfo != null) {
			model.addAttribute("listApplicantInfo", listApplicantInfo);
		}
		List<InterviewMaster> im = new ArrayList<>();
		InterviewForm form = new InterviewForm();
		form.setInterviewForm(im);
		model.addAttribute("interviewMaster", form);

		
		return "interviewDetails"; 

	}

	@PostMapping("addInterviewDetail")
	public String addInterviewDetails(@ModelAttribute("interviewMaster") InterviewForm form, HttpSession session) {

		
		if (form.getInterviewForm() != null) {
			for (int i = 0; i < form.getInterviewForm().size(); i++) {

				InterviewMaster interviewMaster = form.getInterviewForm().get(i);
				interviewMasterService.addInterviewMaster(interviewMaster);
			}

		} else {
			System.out.println("else block ");
		}
		return "redirect:interviewDetails";
	}

	@ResponseBody
	@GetMapping("getApplicantDate/{applicantCode}")
	public Date getRequisitionDateByAdvtCode(@PathVariable("applicantCode") String applicantCode) {
		
		
		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		
		return applicantInfo.getApplicantDate();
	}

	// INTERVIEW APPROVAL DETAILS

	@GetMapping("interviewApproval")
	public String interviewApprovalPage(Model model, HttpSession session) {


		List<ApplicantInfo> listApplicantInfo = applicantInfoService.findApplicantInfoStatusHoldAndPending();

		model.addAttribute("listInterviewApprovalUtil", listApplicantInfo);

		
		return "interviewApproval";
	}

	@GetMapping("approveInterview/{applicantCode}/{interviewStatus}")
	public String approveInterview(@PathVariable("interviewStatus") String interviewStatus,
			@PathVariable("applicantCode") String applicantCode) {

		

		
		applicantInfoService.updateApplicantInfoInterviewStatus(applicantCode, interviewStatus);

		return "redirect:/interviewApproval";
	}

	// INTERVIEW FINAL SELECTION

	@GetMapping("interviewFinalSelection")
	public String interviewFinalSelection(Model model, HttpSession session) throws Exception {

		List<InterviewMaster> listInterviewMaster = interviewMasterService.getAllInterviewMaster();

		model.addAttribute("interviewFinalSelection", listInterviewMaster);
		
		return "interviewFinalSelection"; // interviewFinalSelection.html
	}

	@GetMapping("interviewFileApproval/{applicantCode}/{interviewCode}/{finalApprovalStatus}")
	public String interviewFinalApproval(@PathVariable("finalApprovalStatus") String finalApprovalStatus,
			@PathVariable("applicantCode") String applicantCode, @PathVariable("interviewCode") String interviewCode,
			HttpSession session) {


		interviewMasterService.interviewFinalapproval(applicantCode, interviewCode, finalApprovalStatus);
		return "redirect:/interviewFinalSelection";
	}

	@GetMapping("viewInterviewDetail/{id}")
	public String viewInterviewDetail(@ModelAttribute("interviewMaster") InterviewMaster interviewMaster,
			@PathVariable("id") String inteviewCode, Model model) {

		
		InterviewMaster interviewMaster1 = interviewMasterService.findinterviewMasterById(inteviewCode);

		if (interviewMaster1 != null) {
			model.addAttribute("interviewMaster", interviewMaster1);
		}

		return "viewInterviewDetail";
	}

}
