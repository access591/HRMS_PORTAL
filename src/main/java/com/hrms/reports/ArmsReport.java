package com.hrms.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.ArmsLicenseDetails;
import com.hrms.service.ArmsLicenseService;

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
public class ArmsReport {

	@Autowired
	ArmsLicenseService armsLicenseService;

	public List<ArmsLicenseDetails> createArmsLicensesReport(HttpServletResponse response, HttpServletRequest request,
			List<ArmsLicenseDetails> sourceData) throws IOException {

		String reportFileName = "ArmsLicenses"; // Parameter1
		String sourceFileName = request.getSession().getServletContext()
				.getRealPath("resources/" + reportFileName + ".jrxml");

		System.out.println("arms licenses report : ");
		try {

			JasperCompileManager.compileReportToFile(sourceFileName);
			sourceFileName = request.getSession().getServletContext()
					.getRealPath("/resources/" + reportFileName + ".jasper");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(sourceData);

			Map<String, Object> parameters = new HashMap<>();

			parameters.put("Parameter1", beanColDataSource);

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

				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();

			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		return Collections.<ArmsLicenseDetails>emptyList();

	}

	public void armsReportDataSource(String empCode, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		List<ArmsLicenseDetails> arms = new ArrayList<>();
		ArmsLicenseDetails armsLicenseDetail = armsLicenseService.findArmsByEmpEmpCode(empCode);
		if (armsLicenseDetail != null)
			arms.add(armsLicenseDetail);
		createArmsLicensesReport(response, request, arms);
	}

}
