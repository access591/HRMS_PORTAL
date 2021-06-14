package com.hrms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

	 @Autowired DepartmentService departmentService;
	 
	 @Autowired EmployeeService employeeService;
	 @Autowired DesignationService designationService;
	 @Autowired CategoryService categoryService;
	 
	 public ByteArrayInputStream generateExcel(List<Employee> listEmployee) {
	
	 
	 String[] columns = {"Sr.No","Officer Code","Officer Name" , "Category Name" ,"Department Name" , "Designation" , 
			 "Ips No", " Batch Year","Payee Code","Home District","Court or Department", 
			 "Date Of Birth", "Present Posting",
			 "Date of Posting",
			 "Date of Joining","Date of Retirement","Gender",
			 "Qualification",
	 		"Aadhar No","Mobile No","Office Phone","Email","Under Rule7","Under Rule 8","Vigilance Inquiry"
	 		,"Suspenction","Promotion","ACP","APR","ACR","Trainig","LTC","Leave Acount","Awards","Deputation",
	 		"Previous Postings","OnDeputation","Add Charge","OnAdditionalCharge","Remarks","VRS","Expired"};
		
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
				
				
				Employee e = employeeService.findEmployeeById(emp.getEmpCode());
				Department d = departmentService.findDepartmentById(e.getDepartmentCode());
				//Department d = departmentService.findDepartmentByEmpCode(emp.getDepartmentCode()).get(0);
			
				Designation desig = designationService.findDesignationById(emp.getDesignationCode());
			
				Category category = categoryService.findCategoryByCatId(emp.getCategoryCode());
				
				Row row = sheet.createRow(rowIndex++);
				
				row.createCell(0).setCellValue(rowIndex); //
				row.createCell(1).setCellValue(emp.getEmpCode());
				row.createCell(2).setCellValue(emp.getEmpName());
				row.createCell(3).setCellValue(category.getCategoryName());
				row.createCell(4).setCellValue(d.getDeptName());
				row.createCell(5).setCellValue(desig.getDesgName());
				row.createCell(6).setCellValue("");
				row.createCell(7).setCellValue(emp.getBatchYear());
				row.createCell(8).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(9).setCellValue(emp.getEmployeePayeeCode());
				row.createCell(10).setCellValue(emp.getTypeCourtDepartment());
				row.createCell(11).setCellValue(emp.getDateOfPosting().toString()); //birth
				
				row.createCell(12).setCellValue(emp.getPresentPosting());
				row.createCell(13).setCellValue(emp.getDateOfPosting().toString());
				row.createCell(14).setCellValue(emp.getDateOfJoining().toString());
				row.createCell(15).setCellValue(emp.getDateOfRetirement().toString());
				row.createCell(16).setCellValue(emp.getGender());
				row.createCell(17).setCellValue(emp.getQualification());
				row.createCell(18).setCellValue(emp.getAadharNo());
				row.createCell(19).setCellValue(emp.getMobileNumber1());
				row.createCell(20).setCellValue(emp.getMobileNumber1()); //office phone
				row.createCell(21).setCellValue(emp.getEmail());
				
				row.createCell(22).setCellValue(emp.getUnderRule7()); //add column
				row.createCell(23).setCellValue(emp.getUnderRule8());
				row.createCell(24).setCellValue(emp.getVigilanceQuery());
				row.createCell(25).setCellValue(emp.getSuspention());
				row.createCell(26).setCellValue(emp.getPromotion());
				row.createCell(27).setCellValue(emp.getAcp());
				row.createCell(28).setCellValue(emp.getApr());
				row.createCell(29).setCellValue(emp.getAcr());
				row.createCell(30).setCellValue(emp.getTraining());
				row.createCell(31).setCellValue(emp.getLtc());
				row.createCell(32).setCellValue(emp.getLeaveAccount());
				row.createCell(33).setCellValue(emp.getEmpAwards());
				row.createCell(34).setCellValue(emp.getEmpDeputation());
				row.createCell(35).setCellValue(emp.getPresentPosting());
				row.createCell(36).setCellValue(emp.getOnDeputation());
				row.createCell(36).setCellValue(emp.getAddCharge());
				row.createCell(36).setCellValue(emp.getOnAdditionalCharge());
				row.createCell(36).setCellValue("remarks");
				row.createCell(36).setCellValue(emp.getVrs());
				row.createCell(36).setCellValue(emp.getExpired());
				
				
				
				
			}
			workBook.write(out);
			workBook.close();
			return new ByteArrayInputStream(out.toByteArray());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
		
		
	}

	 

}
