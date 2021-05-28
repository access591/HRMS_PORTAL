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
@Table(name="EMP_MON_OVERTIME")
public class EmpMonOvertime {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Employee employee;
	
	@Column(name="OTIME_MONTH")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date oTimeMonth;
	
	@Column(name="ACTUAL_OTIME")
	private String actualOTime;
	
	@Column(name="ESI_OTIME")
	private String esiOTime;
	
	@Column(name="NON_ESI_OTIME")
	private String nonEsiOtime;
	
	@Column(name="BASIC")
	private String basic;
	
	@Column(name="OTIME_RATE")
	private String oTimeRate;
	
	@Column(name="ESI_OTIME_AMT")
	private String esiOtimeAmt;
	
	@Column(name="NON_ESI_OTIME_AMT")
	private String nonEsiOtimeAmt;
	
	@Column(name="STATUS")
	private String status;
	
	
	@Column(name="INST_BY")
	private String insBy;
	
	@Column(name="INS_DATE")
	private Date insDate = new Date();
	
	@Column(name="ESI_AMT")
	private String esiAmt;

	public EmpMonOvertime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getoTimeMonth() {
		return oTimeMonth;
	}

	public void setoTimeMonth(Date oTimeMonth) {
		this.oTimeMonth = oTimeMonth;
	}

	public String getActualOTime() {
		return actualOTime;
	}

	public void setActualOTime(String actualOTime) {
		this.actualOTime = actualOTime;
	}

	public String getEsiOTime() {
		return esiOTime;
	}

	public void setEsiOTime(String esiOTime) {
		this.esiOTime = esiOTime;
	}

	public String getNonEsiOtime() {
		return nonEsiOtime;
	}

	public void setNonEsiOtime(String nonEsiOtime) {
		this.nonEsiOtime = nonEsiOtime;
	}

	public String getBasic() {
		return basic;
	}

	public void setBasic(String basic) {
		this.basic = basic;
	}

	public String getoTimeRate() {
		return oTimeRate;
	}

	public void setoTimeRate(String oTimeRate) {
		this.oTimeRate = oTimeRate;
	}

	public String getEsiOtimeAmt() {
		return esiOtimeAmt;
	}

	public void setEsiOtimeAmt(String esiOtimeAmt) {
		this.esiOtimeAmt = esiOtimeAmt;
	}

	public String getNonEsiOtimeAmt() {
		return nonEsiOtimeAmt;
	}

	public void setNonEsiOtimeAmt(String nonEsiOtimeAmt) {
		this.nonEsiOtimeAmt = nonEsiOtimeAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getEsiAmt() {
		return esiAmt;
	}

	public void setEsiAmt(String esiAmt) {
		this.esiAmt = esiAmt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	

}
