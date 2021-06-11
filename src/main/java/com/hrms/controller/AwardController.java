package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrms.ImageUtil;
import com.hrms.model.Award;
import com.hrms.model.MenuModule;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;
import com.hrms.service.AwardService;
@Controller
public class AwardController {
	int pageno = 33;
	String reqPage = "/awardMaster";
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private AwardService awardService;
	@Autowired
	PageMappingService pageMappingService;
/**
 * Request Mapping  Award Master 
 * @param model
 * @param session
 * @return
 */
	@GetMapping("/awardMaster")
	public String awardMaster(Model model, HttpSession session) {
		List<Award> listAward = awardService.getAllAwards();
		model.addAttribute("listAward", listAward);

		String userCode = (String) session.getAttribute("username");
		List<MenuModule> modules = moduleService.getAllModulesList(userCode);
		if (modules != null) {
			model.addAttribute("modules", modules);
		}

		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * 
 * @param  Save award master 
 * @param model
 * @param session
 * @return
 */
	@PostMapping("/saveAward")
	public String saveAward(@ModelAttribute("award") Award award, Model model, HttpSession session) {
		String insertedBY = (String) session.getAttribute("userlogin");
		award.setInsBy(insertedBY);
		awardService.addAward(award);
		List<Award> listAward = awardService.getAllAwards();
		model.addAttribute("listAward", listAward);
		session.setAttribute("username", session.getAttribute("username"));

		return "redirect:" + pageMappingService.PageRequestMapping(reqPage, pageno);

	}
/**
 *  find Record base on id and set to update Form
 * @param id
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/editAward/{id}" })
	public String editAward(@PathVariable("id") long id, Model model, HttpSession session) {

		int editPageNo = 34;
		String reqPageedit = "/editAward";
		Award awardEdit = awardService.findAwardById(id);
		model.addAttribute("awardEdit", awardEdit);
		session.setAttribute("username", session.getAttribute("username"));

		return pageMappingService.PageRequestMapping(reqPageedit, editPageNo);
	}
	
	/**
	 * 
	 * @param update award master values 
	 * @param model
	 * @param session
	 * @return
	 */

	@PostMapping("/updateAward")
	public String updateAward(@ModelAttribute("award") Award award, Model model, HttpSession session) {
		String updatedBY = (String) session.getAttribute("userlogin");
		award.setUpdBy(updatedBY);
		this.awardService.updateAward(award);

		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
/**
 * Delete award master record 
 * @param id
 * @param model
 * @param session
 * @return
 */
	@GetMapping(value = { "/deleteAward/{id}" })
	public String deleteAward(@PathVariable("id") long id, Model model, HttpSession session) {
		this.awardService.removeAward(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}

}
