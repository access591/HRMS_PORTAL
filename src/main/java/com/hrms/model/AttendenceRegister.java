package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.exolab.castor.types.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ATTENDENCE_REGISTER")
public class AttendenceRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "ATTENDENCEDATE")
	private Date attendenceDate;

	
	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private Department department;

	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Employee employee;

	@Column(name = "EMP_WORKER_TYPE")
	private String empWorkerType;

	@Column(name = "WORKER_CODE")
	private String workerCode;

	@Column(name = "SHIFT_CODE")
	private String shiftCode;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TIME_IN")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private DateTime timeIn;

	@Column(name = "TIME_OUT")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private DateTime timeOut;

	@Column(name = "INS_BY")
	private String insBy;

	@Column(name = "INS_DATE")
	private Date insDate = new Date();

	@Column(name = "COMPUNSANTARY_L")
	private String compunsantaryL;

	@Column(name = "A_TIME_IN")
	private Date aTimeIn;

	@Column(name = "A_TIME_OUT")
	private Date aTimeOut;

	@Column(name = "OVER_TIME")
	private String overTime;

	@Column(name = "STATUS2")
	private String status2;

	@Column(name = "OVER_TIME_STATUS")
	private String overTimeStatus;

	@Column(name = "OVER_TIM2")
	private String overTime2;

	@Column(name = "REG_CODE")
	private String regCode;

	@Column(name = "EMPLOYEE_PAYEE_CODE")
	private String employeePayeCode;

	@Column(name = "OVER_FLOW_HRS")
	private String overFlowHrs;

	@Column(name = "STATUS_TEMP")
	private String statusTemp;

	public AttendenceRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAttendenceDate() {
		return attendenceDate;
	}

	public void setAttendenceDate(Date attendenceDate) {
		this.attendenceDate = attendenceDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmpWorkerType() {
		return empWorkerType;
	}

	public void setEmpWorkerType(String empWorkerType) {
		this.empWorkerType = empWorkerType;
	}

	public String getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(DateTime timeIn) {
		this.timeIn = timeIn;
	}

	public DateTime getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(DateTime timeOut) {
		this.timeOut = timeOut;
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

	public String getCompunsantaryL() {
		return compunsantaryL;
	}

	public void setCompunsantaryL(String compunsantaryL) {
		this.compunsantaryL = compunsantaryL;
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

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getOverTimeStatus() {
		return overTimeStatus;
	}

	public void setOverTimeStatus(String overTimeStatus) {
		this.overTimeStatus = overTimeStatus;
	}

	public String getOverTime2() {
		return overTime2;
	}

	public void setOverTime2(String overTime2) {
		this.overTime2 = overTime2;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getEmployeePayeCode() {
		return employeePayeCode;
	}

	public void setEmployeePayeCode(String employeePayeCode) {
		this.employeePayeCode = employeePayeCode;
	}

	public String getOverFlowHrs() {
		return overFlowHrs;
	}

	public void setOverFlowHrs(String overFlowHrs) {
		this.overFlowHrs = overFlowHrs;
	}

	public String getStatusTemp() {
		return statusTemp;
	}

	public void setStatusTemp(String statusTemp) {
		this.statusTemp = statusTemp;
	}
	
	

}
