package com.hrms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="OVERTIME_REGISTER")
public class OvertimeRegister {
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Employee employee;
	
	@Column(name="OVER_TIME_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date overTimeDate;
	
	@Column(name="TIME_IN")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date timeIN;
	
	@Column(name="TIME_OUT")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date timeOut;
	
	@Column(name="INS_BY")
	private String insBy;
	
	@Column(name="INS_DATE")
	private Date insDate;
	
	@Column(name="ESI_YN")
	private String esiYn;
	
	@Column(name="OVERTIME_RATE")
	private String overTimeRate;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="OVERTIME")
	private String overTime;

	
	
	public OvertimeRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getOverTimeDate() {
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
	}

	public Date getTimeIN() {
		return timeIN;
	}

	public void setTimeIN(Date timeIN) {
		this.timeIN = timeIN;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
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

	public String getEsiYn() {
		return esiYn;
	}

	public void setEsiYn(String esiYn) {
		this.esiYn = esiYn;
	}

	public String getOverTimeRate() {
		return overTimeRate;
	}

	public void setOverTimeRate(String overTimeRate) {
		this.overTimeRate = overTimeRate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	
	
	
	

	
	
	
	
	
	
	

}
