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
		session.setAttribute("imgUtil", new ImageUtil());
		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}
		List<ApplicantInfo> listApplicantInfo1 = applicantInfoService.getAllApplicantInfo();
		List<ApplicantInfo> listApplicantInfo = new ArrayList<ApplicantInfo>();
		for(int i=0;i<listApplicantInfo1.size();i++) {
			System.out.println("error handling inter status  : "+listApplicantInfo1.get(i).getInterStatus());
			if(listApplicantInfo1.get(i).getInterStatus() == null) {
				//ifs.setSelectionStatus("0");
				System.out.println("11111111111nulllll111111111");
				
				
			}
			
			else if(listApplicantInfo1.get(i).getInterStatus().length() == 0) {
				//ifs.setSelectionStatus("0");
				System.out.println("11111111111111111111");
				
			}
			else {
				
				if(listApplicantInfo1.get(i).getInterStatus().startsWith("F")) {
					listApplicantInfo.add(listApplicantInfo1.get(i));
				}
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
		
		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		model.addAttribute("applicantInfo", applicantInfo);
		
		session.setAttribute("username", userCode);
		return "viewApplicantInfo";
	}
	
	
	
	@ResponseBody
	@GetMapping("getApplicantDate/{applicantCode}")
	public Date getRequisitionDateByAdvtCode(@PathVariable("applicantCode")String applicantCode) {
		//get advt code and advt date from Advertisement
		
		System.out.println("applicant Code : " + applicantCode);
		ApplicantInfo applicantInfo = applicantInfoService.getApplicantInfoByApplicantCode(applicantCode);
		//String advtDate = reqAdvertisement.getAdvtDate();
		System.out.println("======>>"+ applicantInfo.getApplicantDate());
		//System.out.println("response is : "+ )
		return applicantInfo.getApplicantDate();
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
		
		
		
		try {
			for(int i=0;i<listApplicantInfo.size();i++) {
				
				System.out.println("checking inter status : " + listApplicantInfo.get(i).getInterStatus());
				if( listApplicantInfo.get(i).getInterStatus()==null || listApplicantInfo.get(i).getInterStatus().equals("") || listApplicantInfo.get(i).getInterStatus().startsWith("H"))
				{
					InterviewApprovalUtil interviewApprovalUtil = new InterviewApprovalUtil();
					
					Designation designation;
					try {
						designation = designationService.findDesignationById(listApplicantInfo.get(i).getDesigCode());
						interviewApprovalUtil.setDesignationCode(designation.getDesgCode());
						interviewApprovalUtil.setDesignationName(designation.getDesgName());
					}catch(Exception e) {
						System.out.println("error : find designation");
						e.printStackTrace();
					}
					
					
					
					interviewApprovalUtil.setApplicantCode(listApplicantInfo.get(i).getApplicantCode());
					interviewApprovalUtil.setApplicantDate(listApplicantInfo.get(i).getApplicantDate());
					interviewApprovalUtil.setApplicantName(listApplicantInfo.get(i).getApplicantName());
					interviewApprovalUtil.setApplicantSex(listApplicantInfo.get(i).getSex());
					
					listInterviewApprovalUtil.add(interviewApprovalUtil);
					System.out.println("hiii");
				}
				
				
			}
		}catch(Exception e) {
			System.out.println("for loop error ");
			e.printStackTrace();
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
	public String interviewFinalSelection(Model model,HttpSession session) throws Exception {
		
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
			
			//System.out.println("selection status : "+ interView.getSelectionStatus());
			
			if(interView.getSelectionStatus() == null) {
				ifs.setSelectionStatus("0");
				System.out.println("11111111111nulllll111111111");
				
				
			}
			
			else if(interView.getSelectionStatus().length() == 0) {
				ifs.setSelectionStatus("0");
				System.out.println("11111111111111111111");
				
			}
			else {
				
				ifs.setSelectionStatus(interView.getSelectionStatus());
				System.out.println("0000000000000");
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
