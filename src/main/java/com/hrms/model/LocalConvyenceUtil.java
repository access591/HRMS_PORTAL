package com.hrms.model;

public class LocalConvyenceUtil {
	private String  empCode;
	private String  empName;
	private String departmentCode;
	private String  deptName;
	private String desgCode;
	private String desgName;

	private String  empPayCode;
	private String  fatherHusband;
	private String localConvId;
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getEmpPayCode() {
		return empPayCode;
	}
	public void setEmpPayCode(String empPayCode) {
		this.empPayCode = empPayCode;
	}
	public String getFatherHusband() {
		return fatherHusband;
	}
	public void setFatherHusband(String fatherHusband) {
		this.fatherHusband = fatherHusband;
	}
	public String getLocalConvId() {
		return localConvId;
	}
	public void setLocalConvId(String localConvId) {
		this.localConvId = localConvId;
	}
	public LocalConvyenceUtil(String empCode, String empName, String departmentCode, String deptName, String desgCode,
			String desgName, String empPayCode, String fatherHusband, String localConvId) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.departmentCode = departmentCode;
		this.deptName = deptName;
		this.desgCode = desgCode;
		this.desgName = desgName;
		this.empPayCode = empPayCode;
	
		this.localConvId = localConvId;
	}
	public LocalConvyenceUtil() {
		super();
	}
	
	
	public LocalConvyenceUtil(String empName2, String deptName2, String desgName2, String employeePayeeCode) {
		this.empName = empName2;
		this.deptName = deptName2;
		this.desgName = desgName2;
		this.empPayCode = employeePayeeCode;
	}
	
	
	
	
}
