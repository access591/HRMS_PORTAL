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

import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;

@Component
public class EmployeeGradationExcel {
	
	 private XSSFWorkbook workbook;
	 private XSSFSheet sheet;
	 private List<Employee> listEmployee;
	 private DepartmentService departmentService;
	 
	 @Autowired EmployeeService employeeService;
	 @Autowired DesignationService designationService;
	 
	 public ByteArrayInputStream generateExcel() {
		 
		List<Employee> listEmployee = employeeService.getAllEmployees();
		
		System.out.println(" list employee : " + listEmployee.get(0).getEmpCode());
		
		
		
		
		
	 
	 String[] columns = {"Employee Code","Employee Name" , "Category Name" ,"Department Name" , "Designation" , 
			 "Ips No", " Batch Year","Payee Code","Home District","Court or Department",
			 "Date of Posting",
			 "Present Posting","Date Of Birth",
			 "Date of Joining","Date of Retirement","Gender",
			 "Qualification",
	 		"Aadhar No","Mobile No","Email"};
		
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
			System.out.println("reow inde x value ");
			int rowIndex = 1;
			for(Employee emp : listEmployee) {
				
				int i =0;
				
				//Department d = departmentService.findDepartmentById(emp.getDepartmentCode());
				//System.out.println("department : " + d.getDeptName());
				Designation desig = designationService.findDesignationById(emp.getDesignationCode());
				System.out.println("department : " + desig.getDesgName());
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(emp.getEmpCode());
				row.createCell(1).setCellValue(emp.getEmpName());
				row.createCell(2).setCellValue("Cate-101");
				row.createCell(3).setCellValue(desig.getDesgName());
				row.createCell(4).setCellValue(desig.getDesgName());
				row.createCell(5).setCellValue("");
				row.createCell(6).setCellValue("Ips-001");
				row.createCell(7).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(8).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(9).setCellValue("");
				row.createCell(10).setCellValue("");
				row.createCell(11).setCellValue(emp.getOrderDate());
				row.createCell(12).setCellValue(emp.getOrderDate());
				row.createCell(13).setCellValue(emp.getOrderDate());
				row.createCell(14).setCellValue("");
				row.createCell(15).setCellValue(emp.getGender());
				row.createCell(16).setCellValue(emp.getQualification());
				row.createCell(17).setCellValue(emp.getAadharNo());
				row.createCell(17).setCellValue(emp.getMobileNumber());
				row.createCell(7).setCellValue(emp.getMobileNumber());
				row.createCell(7).setCellValue(emp.getEmail());
				
				i++;
			}
			workBook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	 

}
