package com.hrms.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.StaffPostingDuties;
import com.hrms.repository.SaffPostingDutiesDao;
import com.hrms.util.SaffPostingDutiesUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class SaffPostingDutiesServiceImpl implements StaffPostingDutiesService {
	@Autowired
	SaffPostingDutiesDao saffPostingDutiesDao;

	@Override
	public void addStaffPostingDuties(StaffPostingDuties staffduties) {
		staffduties.setJobCode(saffPostingDutiesDao.getMaxId("SDC"));
		this.saffPostingDutiesDao.saveOrUpdate(staffduties);
	}

	@Override
	public void removestaffDuties(String id) {
		this.saffPostingDutiesDao.delete(id);

	}

	@Override
	public List<StaffPostingDuties> getAllStaffPostingDuties() {
		List<StaffPostingDuties> staffPostingDuties = saffPostingDutiesDao.findAll();
		return staffPostingDuties;
	}

	@Override
	public StaffPostingDuties StaffPostingDutieById(String id) {
		return saffPostingDutiesDao.findById(id);
	}

	@Override
	public void UpdateStaffPostingDuties(StaffPostingDuties staffduties) {
		this.saffPostingDutiesDao.saveOrUpdate(staffduties);
		
	}

	@Override
	public void staffPostingDutiesGenratepdf(HttpServletRequest request, HttpServletResponse response,
			String reportFileName, List<SaffPostingDutiesUtil> dataList) {
		  String sourceFileName =request.getSession().getServletContext().getRealPath("resources/" + reportFileName + ".jrxml");
		   // String path = jasperFolder+"/resources/Reports" + reportFileName+ ".jrxml");
		      String GeneratedBy ="";
	        String GeneratedDates ="";
	        GeneratedBy = request.getSession().getAttribute("USER_NAME").toString();
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	        LocalDateTime now = LocalDateTime.now();
	        GeneratedDates = dtf.format(now);
	        
		    
				try {
					System.out.println("Start compiling!!! ...");
					JasperCompileManager.compileReportToFile(sourceFileName);
					System.out.println("Done compiling!!! ...");
					sourceFileName = request.getSession().getServletContext().getRealPath("/resources/" + reportFileName + ".jasper");
					System.out.println("Jasper File Created!!! ...");
				
					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
					
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("ItemDataSource", beanColDataSource);
					parameters.put("GeneratedBy", GeneratedBy);
			        parameters.put("GeneratedDates", GeneratedDates);
					
					
					JasperReport report = (JasperReport) JRLoader
							.loadObjectFromFile(sourceFileName);
					JasperPrint jasperPrint = JasperFillManager.fillReport(report,
							parameters, new JREmptyDataSource());
					
					
					if (jasperPrint != null) {
						byte[] pdfReport = JasperExportManager
								.exportReportToPdf(jasperPrint);
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Cache-Control", "no-store");
						response.setHeader("Cache-Control", "private");
						response.setHeader("Pragma", "no-store");
						response.setContentLength(pdfReport.length);
						try {
							response.getOutputStream().write(pdfReport);
							response.getOutputStream().flush();
							response.getOutputStream().close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				} catch (JRException e) {
					e.printStackTrace();
				}
		
	}

}