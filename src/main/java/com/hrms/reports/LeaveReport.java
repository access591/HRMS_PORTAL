package com.hrms.reports;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.LeaveGrant;
import com.hrms.service.LeaveGrantRegisterService;

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
public class LeaveReport {

	@Autowired
	LeaveGrantRegisterService leaveGrantService;
	
	
//leave management report / leave request report

	public List<?> leaveRequestReport(HttpServletResponse response, HttpServletRequest request, String reportFileName,
			List<?> listLeave, String empCode) {

		List<LeaveGrant> listLeaveGrant = leaveGrantService.findLeaveGrantByEmployeeName(empCode);

		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			// JRBeanCollectionDataSource beanColDataSource = new
			// JRBeanCollectionDataSource(sourceData);

			// JRBeanCollectionDataSource leaveGrant = new
			// JRBeanCollectionDataSource(listLeaveGrant);
			JRBeanCollectionDataSource leaveRequest = new JRBeanCollectionDataSource(listLeave);

			HashMap<String, Object> map = new HashMap<String, Object>();

			// map.put("ItemDataSource", leaveGrant);
			map.put("leaveRequest", leaveRequest);

			map.put("empName", empCode);
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
	
	
	//LEAVE TRASACTION PDF REPORT
		public void leaveTransactionPdfReportByEmp(HttpServletRequest request, HttpServletResponse response, 
				List<?> sourceData,String activeUser) {
			String reportFileName = "LeaveTransaction";
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
				map.put("activeUser", "Run By : "+activeUser);
				map.put("runDate", "Run Date : "+new Date());

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

}
