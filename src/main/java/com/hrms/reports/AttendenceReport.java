package com.hrms.reports;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class AttendenceReport {
	
	public List<?> attendenceMontlyReport(HttpServletResponse response, HttpServletRequest request, List<?> sourceData) {

		String reportFileName = "AttendanceRegMonthly"; // Parameter1

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			//map.put("Parameter1", beanColDataSource);
			
			//map.put("createdby ", "rahul");
			


			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

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
		return null;

	}
	
	
	public List<?> createAttendenceReportDatewise(HttpServletResponse response, HttpServletRequest request, List<?> sourceData) {

		String reportFileName = "AttendenceReportDatewise"; // Parameter1
		
		String mainYear = "2020-21";
		String totalDate = "From  : 20/05/2021    To  : 20/06/2021";

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("Parameter1", beanColDataSource);
			
			parameters.put("mainYear", mainYear);
			parameters.put("totalDate", totalDate);
			


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

		return null;

	}
	
	
	
	public List<?> createOvertimeRegDateReport(HttpServletResponse response, HttpServletRequest request, List<?> sourceData) {

		String reportFileName = "AttendanceRegMonthly"; // Parameter1

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			//map.put("Parameter1", beanColDataSource);
			
			//map.put("createdby ", "rahul");
			


			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

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
		return null;

	}
	
	
	public List<?> overtimeRegisterMonthly(HttpServletResponse response, HttpServletRequest request, List<?> sourceData) {

		String reportFileName = "AttendanceRegMonthly"; // Parameter1

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			//map.put("Parameter1", beanColDataSource);
			
			//map.put("createdby ", "rahul");
			


			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

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
		return null;

	}


}
