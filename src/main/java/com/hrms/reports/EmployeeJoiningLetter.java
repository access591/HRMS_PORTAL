package com.hrms.reports;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.hrms.model.ApplicantInfo;
import com.hrms.model.CommonUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;



@Component
public class EmployeeJoiningLetter {

	public void employeeJoiningLetter(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			ApplicantInfo applicantInfo) {
		System.out.println("Employee Joining report...");

		String joiningLetter = "I , Mr/Ms "+applicantInfo.getApplicantName()+" have Joined HRMS  as "+applicantInfo.getDesigCode().getDesgName()+" Department w.e.f Date 10/03/21 "
				+ "in accordance with the condition of your LOI Dated.";

		
		
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		

		try {
			System.out.println("Start compiling!!! ...");
			JasperCompileManager.compileReportToFile(sourceFileName);
			System.out.println("Done compiling!!! ...");
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			System.out.println("Jasper File Created!!! ...");

			//JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put("joiningLetter",joiningLetter);
			parameters.put("name",applicantInfo.getApplicantName());

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
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
					
					e.printStackTrace();
				}

			}
		} catch (JRException e) {
			e.printStackTrace();
		}


	}

}
