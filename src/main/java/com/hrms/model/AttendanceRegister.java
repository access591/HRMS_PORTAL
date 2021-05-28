package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "MEGASTAR_ATTENDANCE_REGISTER")
public class AttendanceRegister implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4192487735866570520L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_CODE",updatable = false)
	private Employee empCode;
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE",updatable = false)
	private Department deptCode;
	@ManyToOne
	@JoinColumn(name = "SHIFT_CODE",updatable = false)
	private Shift shiftCode;
	
	@Column(name ="ATT_DATE")
	private  Date  attDate;
	
	@Column(name ="TIME_IN")
	private  Date  timeIn;
	
	@Column(name ="TIME_OUT")
	private  Date  timeOut;
	
	@Column(name ="A_TIME_IN")
	private  Date  aTimeIn;
	
	@Column(name ="A_TIME_OUT")
	private  Date  aTimeOut;
	
	@Column(name ="OVERTIME")
	private  int  overTime;
	
	@Column(name = "ACTIVE_YN1")
	private String activeOne;
	
	@Column(name = "ACTIVE_YN2")
	private String activeTwo;
	
	@Column(name = "OVERTIME_STATUS")
	private String overTimeStatus;
	
	@Column(name = "REG_CODE")
	private String regCode;
	
	@Column(name ="OVERFLOW_HRS")
	private  int  OverFlowHrs;
	
	@Column(name ="EMPLOYEE_PAYEE_CODE")
	private String   employeePayeeCode;
	
	
	  
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public Department getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Department deptCode) {
		this.deptCode = deptCode;
	}

	public Shift getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(Shift shiftCode) {
		this.shiftCode = shiftCode;
	}

	public Date getAttDate() {
		return attDate;
	}

	public void setAttDate(Date attDate) {
		this.attDate = attDate;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public Date getaTimeIn() {
		return aTimeIn;
	}

	public void setaTimeIn(Date aTimeIn) {
		this.aTimeIn = aTimeIn;
	}

	public Date getaTimeOut() {
		return aTimeOut;
	}

	public void setaTimeOut(Date aTimeOut) {
		this.aTimeOut = aTimeOut;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public String getActiveOne() {
		return activeOne;
	}

	public void setActiveOne(String activeOne) {
		this.activeOne = activeOne;
	}

	public String getActiveTwo() {
		return activeTwo;
	}

	public void setActiveTwo(String activeTwo) {
		this.activeTwo = activeTwo;
	}

	public String getOverTimeStatus() {
		return overTimeStatus;
	}

	public void setOverTimeStatus(String overTimeStatus) {
		this.overTimeStatus = overTimeStatus;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public int getOverFlowHrs() {
		return OverFlowHrs;
	}

	public void setOverFlowHrs(int overFlowHrs) {
		OverFlowHrs = overFlowHrs;
	}

	public String getInsBy() {
		return insBy;
	}

	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}

	public Date getInsDate() {
		return insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	public String getEmployeePayeeCode() {
		return employeePayeeCode;
	}

	public void setEmployeePayeeCode(String employeePayeeCode) {
		this.employeePayeeCode = employeePayeeCode;
	}
		
}
