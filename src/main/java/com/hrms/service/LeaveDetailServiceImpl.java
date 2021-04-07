package com.hrms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hrms.model.LeaveDetail;
import com.hrms.repository.LeaveDetailDao;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class LeaveDetailServiceImpl  implements LeaveDetailService{
	@Autowired
	LeaveDetailDao leaveDetailDao;
	
	@Override
	public void addLeaveDetail(LeaveDetail leaveDetail) {
		leaveDetail.setLvCode(leaveDetailDao.getMaxId("LVC"));
		this.leaveDetailDao.saveOrUpdate(leaveDetail);
		
	}

	@Override
	public List<LeaveDetail> getAllLeaveDetails() {
	
		List<LeaveDetail> listLeaveDetail = leaveDetailDao.findAll();
		return listLeaveDetail;
	}

	@Override
	public LeaveDetail findLeaveDetailById(String id) {
	
		return this.leaveDetailDao.findById(id);
	}

	@Override
	public void updateLeaveDetail(LeaveDetail leaveDetail) {
	this.leaveDetailDao.saveOrUpdate(leaveDetail);
		
	}

	@Override
	public void removeLeaveDetail(String id) {
	this.leaveDetailDao.delete(id);
		
	}

	@Override
	public void leaveReportGenratepdf(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List dataList) {
		
	    String sourceFileName =request.getSession().getServletContext().getRealPath("resources/" + reportFileName + ".jrxml");
	   // String path = jasperFolder+"/resources/Reports" + reportFileName+ ".jrxml");
	    
	    
			try {
				System.out.println("Start compiling!!! ...");
				JasperCompileManager.compileReportToFile(sourceFileName);
				System.out.println("Done compiling!!! ...");
				sourceFileName = request.getSession().getServletContext().getRealPath("/resources/" + reportFileName + ".jasper");
				System.out.println("Jasper File Created!!! ...");
			
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("ItemDataSource", beanColDataSource);
				
				
				
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
