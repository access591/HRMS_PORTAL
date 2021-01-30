package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrms.model.MenuModule;
import com.hrms.model.UrlDetail;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
@Controller
public class PageController {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	PageMappingService pageMappingService;
	
	
@GetMapping("/pageMaster")
public String PageMater(Model model,HttpSession session)
	{
	List<UrlDetail> listUrlDetail = pageMappingService.getAllPages();
	model.addAttribute("listUrlDetail", listUrlDetail);
	String userCode= (String)session.getAttribute("username");
	List<MenuModule> modules = moduleService.getAllModulesList(userCode);
	if (modules != null) {
		model.addAttribute("modules", modules);
	}
		return "pageMaster";
	}



@PostMapping("/savePages")
public String savePages(@ModelAttribute("Pages") UrlDetail urlDetail, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
boolean isUrlDetailExist = pageMappingService.checkUrlDetailExists(urlDetail);

if (isUrlDetailExist) {
redirectAttributes.addFlashAttribute("message", "Url id Already exists !  ");
redirectAttributes.addFlashAttribute("alertClass", "alert-success");
return"redirect:/pageMaster";

}
else {
	pageMappingService.addPage(urlDetail); 
	List<UrlDetail> listUrlDetail = pageMappingService.getAllPages();
model.addAttribute("listUrlDetail", listUrlDetail);
	
	session.setAttribute("username",session.getAttribute("username"));

}
return"redirect:/pageMaster";

}

}
