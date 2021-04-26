package com.hrms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;

@Component
public class EmployeeGradationExcel {
	
	 private XSSFWorkbook workbook;
	 private XSSFSheet sheet;
	 private List<Employee> listEmployee;
	 
	 @Autowired EmployeeService employeeService;
	 
	 public ByteArrayInputStream generateExcel() {
		 
		List<Employee> listEmployee = employeeService.getAllEmployees();
	 
	 String[] columns = {"Employee Code","Employee Name" , "Date of Birth","Category Name","Gender",
			 "Department Name","Designation","Present Posting","Date of Posting","Date Of Joining",
			 "Date of Retirement","Qualification","Home District","Court or Department","Ips No",
	 		" Batch Year","Payee Code","Aadhar No","Mobile No","Email"};
		
		try {
			Workbook workBook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Sheet sheet = workBook.createSheet("employee");
			
			Font headerFont = workBook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			
			CellStyle headerCellStyle = workBook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			
			Row headerRow = sheet.createRow(0);
			
			for(int col=0;col<columns.length;col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
				
			}
			
			int rowIndex = 1;
			for(Employee emp : listEmployee) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(emp.getEmpCode());
				row.createCell(1).setCellValue(emp.getEmpName());
				row.createCell(2).setCellValue(emp.getOrderDate());
				row.createCell(3).setCellValue("");
				row.createCell(4).setCellValue(emp.getGender());
				row.createCell(5).setCellValue(emp.getDepartmentCode());
				row.createCell(6).setCellValue(emp.getDesignationCode());
				row.createCell(7).setCellValue(emp.getDateOfPosting());
				row.createCell(8).setCellValue(emp.getDateOfPosting());
				row.createCell(9).setCellValue(emp.getDateOfPosting());
				row.createCell(10).setCellValue(emp.getDateOfPosting());
				row.createCell(11).setCellValue(emp.getQualification());
				row.createCell(12).setCellValue("Home / District ");
				row.createCell(13).setCellValue("Court or Department ");
				row.createCell(14).setCellValue("IPS nO");
				row.createCell(15).setCellValue(emp.getBatchYear());
				row.createCell(16).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(17).setCellValue(emp.getAadharNo());
				row.createCell(7).setCellValue(emp.getMobileNumber());
				row.createCell(7).setCellValue(emp.getEmail());
				
			}
			workBook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	 

}
