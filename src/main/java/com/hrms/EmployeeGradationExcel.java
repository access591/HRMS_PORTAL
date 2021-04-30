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

import com.hrms.model.Category;
import com.hrms.model.Department;
import com.hrms.model.Designation;
import com.hrms.model.Employee;
import com.hrms.service.CategoryService;
import com.hrms.service.DepartmentService;
import com.hrms.service.DesignationService;
import com.hrms.service.EmployeeService;

@Component
public class EmployeeGradationExcel {
	
	 private XSSFWorkbook workbook;
	 private XSSFSheet sheet;
	 private List<Employee> listEmployee;
	 @Autowired DepartmentService departmentService;
	 
	 @Autowired EmployeeService employeeService;
	 @Autowired DesignationService designationService;
	 @Autowired CategoryService categoryService;
	 
	 public ByteArrayInputStream generateExcel(List<Employee> listEmployee) {
		 
		 
		
		 System.out.println("generated excel reports ");
		 System.out.println("generaed list source = "+listEmployee.size());
		
		
		
		
		
		
		
	 
	 String[] columns = {"Employee Code","Employee Name" , "Category Name" ,"Department Name" , "Designation" , 
			 "Ips No", " Batch Year","Payee Code","Home District","Court or Department", 
			 "Date Of Birth","Present Posting",
			 "Date of Posting",
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
				
				System.out.println("single employee : =" +emp.getDepartmentCode());
				System.out.println("single employee : =" +emp.getDateOfJoining());
				
				Employee e = employeeService.findEmployeeById(emp.getEmpCode());
				Department d = departmentService.findDepartmentById(e.getDepartmentCode());
				//Department d = departmentService.findDepartmentByEmpCode(emp.getDepartmentCode()).get(0);
				System.out.println( "department detail : =" +d.getDeptName());
				Designation desig = designationService.findDesignationById(emp.getDesignationCode());
				System.out.println( "designation detail : =" +desig.getDesgName());
				Category category = categoryService.findCategoryByCatId(emp.getCategoryCode());
				System.out.println( "department detail : =" +category.getCategoryName());
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(emp.getEmpCode());
				row.createCell(1).setCellValue(emp.getEmpName());
				row.createCell(2).setCellValue(category.getCategoryName());
				row.createCell(3).setCellValue(d.getDeptName());
				row.createCell(4).setCellValue(desig.getDesgName());
				row.createCell(5).setCellValue("");
				row.createCell(6).setCellValue(emp.getBatchYear());
				row.createCell(7).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(8).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(9).setCellValue(emp.getTypeCourtDepartment());
				row.createCell(10).setCellValue(emp.getDateOfPosting().toString()); //birth
				row.createCell(11).setCellValue(emp.getPresentPosting());
				row.createCell(12).setCellValue(emp.getDateOfPosting().toString());
				row.createCell(13).setCellValue(emp.getDateOfJoining().toString());
				row.createCell(14).setCellValue(emp.getDateOfRetirement().toString());
				row.createCell(15).setCellValue(emp.getGender());
				row.createCell(16).setCellValue(emp.getQualification());
				row.createCell(17).setCellValue(emp.getAadharNo());
				row.createCell(18).setCellValue(emp.getMobileNumber1());
				row.createCell(19).setCellValue(emp.getEmail());
				
				
				
			}
			workBook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
		
	}

	 

}
