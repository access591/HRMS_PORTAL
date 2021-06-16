package com.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.ImageUtil;
import com.hrms.model.City;
import com.hrms.model.MenuModule;
import com.hrms.service.CityService;
import com.hrms.service.ModuleService;
import com.hrms.service.PageMappingService;


@Controller
public class CityController {
	int pageno=25;
	String reqPage="/cityMaster";
	@Autowired
	private ModuleService moduleService;
	@Autowired
	CityService cityService;
	@Autowired
	PageMappingService pageMappingService;
/**
 * 
 * Request mapping City Master 
 * @param model
 * @param session
 * @return
 */
	@RequestMapping(value="/cityMaster")
    public String productsRedirect(HttpServletRequest request, Model model, HttpSession
    		  session){
		String userCode = (String) session.getAttribute("username"); 
		
		if (userCode!=null) {
			session.setAttribute("imgUtil", new ImageUtil());
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
			return "redirect:getAllCities";
		}
		else
		{
    return "redirect:" + "./";
		}
    }
  
	 @RequestMapping(value = {"getAllCities", "/", "/list"})
	    public ModelAndView getAllCities(@RequestParam(required = false) Integer page,HttpSession  session,Model model) {
		 String userCode = (String) session.getAttribute("username");
			List<MenuModule> modules = moduleService.getAllModulesList(userCode);
			if (modules != null) {
				model.addAttribute("modules", modules);
			}
	        List<City> userList =cityService.getAllCities();
	        ModelAndView modelAndView = new ModelAndView("cityMaster", "userList", userList);
	        PagedListHolder<City> pagedListHolder = new PagedListHolder<>(userList);
	        pagedListHolder.setPageSize(5);
	        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
	        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

	        modelAndView.addObject("page", page);
	        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
	            pagedListHolder.setPage(0);
	            modelAndView.addObject("userList", pagedListHolder.getPageList());
	        }
	        else if(page <= pagedListHolder.getPageCount()) {
	            pagedListHolder.setPage(page-1);
	            modelAndView.addObject("userList", pagedListHolder.getPageList());
	        }
	       
	    	int current = pagedListHolder.getPage() + 1;
	    	int begin = Math.max(1, current - 5);
	    	int end = Math.min(begin + 5, pagedListHolder.getPageCount());
	    	int totalPageCount = pagedListHolder.getPageCount();
	    	  modelAndView.addObject("beginIndex", begin);
	    	  modelAndView.addObject("endIndex", end);
	    	  modelAndView.addObject("currentIndex", current);
	    	  modelAndView.addObject("totalPageCount", totalPageCount);
	        return modelAndView;
	    }
	
	/**
	 * save Cities  request mapping city master 
	 * @param city
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") City city, Model model, HttpSession session) {
		
		String insertedBY = (String) session.getAttribute("userlogin");
		city.setInsBy(insertedBY);
		
		cityService.addCity(city);
		List<City> listCity = cityService.getAllCities();
		model.addAttribute("listCity", listCity);
		session.setAttribute("username", session.getAttribute("username"));

		return"redirect:/cityMaster";

	}

	/**
	 * Get Single Record And Edit Record 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/editCity/{id}" })
	public String editCity(@PathVariable("id") String id, Model model, HttpSession session) {
		
		City cityEdit = cityService.findCityById(id);
		model.addAttribute("cityEdit", cityEdit);
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("imgUtil", new ImageUtil());
		return "editCity";
	}
	/**
	 * Request Mapping  update City 
	 * @param c
	 * @param model
	 * @param session
	 * @return
	 */
	
	@PostMapping("/updateCity")
	public String updateCity(@ModelAttribute("city") City city, Model model, HttpSession session) {
		
		String updatedBY = (String) session.getAttribute("userlogin");
		city.setUpdBy(updatedBY);
		this.cityService.updateCity(city);

		return"redirect:/cityMaster";
	}
	
	/**
	 * Delete City record 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value = { "/deleteCity/{id}" })
	public String deleteCity(@PathVariable("id") String id, Model model, HttpSession session) {
		
		this.cityService.removeCity(id);
		session.setAttribute("username", session.getAttribute("username"));
		return "redirect:/" + pageMappingService.PageRequestMapping(reqPage, pageno);
	}
	
}
