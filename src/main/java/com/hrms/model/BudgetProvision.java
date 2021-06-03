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

@Entity
@Table(name="BUDGET_PROVISION")
public class BudgetProvision {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BUDGET_PROVISION_ID")
	private Long budgetProvisionId;
	
	@Column(name="BUDGET_HEAD")
	private String budgetHead;
	
	@ManyToOne
	@JoinColumn(name="DEPT_CODE")
	private Department department;
	
	@Column(name="EXPENDITURE_PURPOSE")
	private String expenditurePurpose;
	
	@Column(name="FILE_NO")
	private String fileNo;
	
	@Column(name="DATE_OF_SANCTION")
	private Date dateOfSanction;
	
	@Column(name="EXPENDITURE_AMOUNT")
	private String 	expenditureAmount;
	
	@Column(name="BALANCE_AMOUNT")
	private String balanceAmount;
	
	@Column(name="YEARLY_FIX_AMOUNT")
	private String yearlyFixAmount;

	public BudgetProvision() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBudgetProvisionId() {
		return budgetProvisionId;
	}

	public void setBudgetProvisionId(Long budgetProvisionId) {
		this.budgetProvisionId = budgetProvisionId;
	}

	public String getBudgetHead() {
		return budgetHead;
	}

	public void setBudgetHead(String budgetHead) {
		this.budgetHead = budgetHead;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getExpenditurePurpose() {
		return expenditurePurpose;
	}

	public void setExpenditurePurpose(String expenditurePurpose) {
		this.expenditurePurpose = expenditurePurpose;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public Date getDateOfSanction() {
		return dateOfSanction;
	}

	public void setDateOfSanction(Date dateOfSanction) {
		this.dateOfSanction = dateOfSanction;
	}

	public String getExpenditureAmount() {
		return expenditureAmount;
	}

	public void setExpenditureAmount(String expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getYearlyFixAmount() {
		return yearlyFixAmount;
	}

	public void setYearlyFixAmount(String yearlyFixAmount) {
		this.yearlyFixAmount = yearlyFixAmount;
	}
	
	
	
	
	

}
