package com.hrms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.EmployeeLeaveRequest;
import com.hrms.model.LeaveDetail;
import com.hrms.model.LeaveRequest;
import com.hrms.service.LeaveDetailService;
import com.hrms.service.LeaveRequestService;

import net.sf.jasperreports.engine.JRDataSource;
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
public class ReportUtil {

	@Autowired
	LeaveRequestService leaveRequestService;

	// leave management report

	public List<?> leaveRequestReport(HttpServletResponse response, HttpServletRequest request, String reportFileName,
			List<?> sourceData) {

		List<LeaveRequest> listLeave = leaveRequestService.findAllByEmpCode("EMP-0003");
		
		System.out.println("list leave size : "+listLeave.size());
		
		List<LeaveRequest> leaveDataSource = new ArrayList<LeaveRequest>();
		LeaveRequest lv = listLeave.get(0);
		leaveDataSource.add(lv);
		// System.out.println("leave request : "+leaveRequest);

		String sourceFileName = request.getSession().getServletContext()  
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			JRBeanCollectionDataSource leaveRequest = new JRBeanCollectionDataSource(leaveDataSource);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("ItemDataSource", beanColDataSource);
			map.put("leaveRequest", leaveRequest);

			map.put("empName", "Rahul Tiwari");
			map.put("deptName", "Software");
			map.put("desig", "Devloper");

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

	@Autowired
	LeaveDetailService leaveDetailService;

	// leave detail reports
	public void leaveRegisterReport(HttpServletRequest request, HttpServletResponse response) {

		List<LeaveDetail> listLeveDetail = leaveDetailService.getAllLeaveDetails();

		String reportFileName = "LeaveDetailManagement";
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listLeveDetail);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("Parameter1", beanColDataSource);
			map.put("Parameter2", "Rahul");
			map.put("empname", "Rahul");   
			
			

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
		// return null;

	}

	// Employee gradation information pdf  report

	public void allEmployeeReport(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<?> sourceData) {
		System.out.println("employee report...");
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		System.out.println("resources : = "+sourceFileName);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("ItemDataSource", beanColDataSource);

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
		// return null;

	}
	
	
	//LEAVE TRASACTION PDF REPORT
	public void leaveTransactionPdfReportByEmp(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<?> sourceData) {
		System.out.println("leave transaction report...");
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		System.out.println("resources : = "+sourceFileName);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("Parameter", beanColDataSource);//Parameter

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
		// return null;

	}
	
	//	BIRT AND ANNIVERASARY REPORT
	public void birthAnniversaryReport(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<EmployeeLeaveRequest> sourceData) {
		System.out.println("birth anniversary  report...");
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		System.out.println("resources : = "+sourceFileName);

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("Parameter1", beanColDataSource);//Parameter

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
		// return null;

	}
	
	
	
	
	
//EMPLOYEE JOINING LETTTER
	
	public void employeeJoiningLetter(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<EmployeeLeaveRequest> sourceData) {
		System.out.println("Employee Joining report...");
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");
		System.out.println("resources : = "+sourceFileName);

		try {

			JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
			JRDataSource dataSource = new JREmptyDataSource();
			Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("name",sourceData.get(0).getEmpName());
			parameter.put("dept", sourceData.get(0).getDeptName());
			parameter.put("desig", sourceData.get(0).getDesgName());
			//parameter.put("wef", sourceData.get(0).getDesgName());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint,sourceFileName);

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
		// return null;

	}
	
	
	

}
