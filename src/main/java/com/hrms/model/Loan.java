package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="M_Loan")
public class Loan implements Serializable {

	/**
	 * Auth surendra Access Infotech 
	 */
	private static final long serialVersionUID = 2597790542918327732L;

	
	       @Id
			@Column(name = "LOAN_CODE")
			private String loanCode;
	       
			@Column(name = "LOAN_NAME")
			private String loanName;
			
		    @Column(name = "INS_BY",updatable = false)
			private String insBy;
			
			@Column(name = "INS_DATE",updatable = false)
			private Date insDate =new Date();
			
			@Column(name = "UPD_BY",insertable = false)
			private String updBy;
			
			@Column(name = "UPD_DATE",insertable = false)
			private Date  updDate = new Date();

			
			@Column(name = "ACTIVE_YN")
			private String active;

			
			public String getActive() {
				return active;
			}
			public void setActive(String active) {
				this.active = active;
			}
			public String getLoanCode() {
				return loanCode;
			}
			public void setLoanCode(String loanCode) {
				this.loanCode = loanCode;
			}
			public String getLoanName() {
				return loanName;
			}
			public void setLoanName(String loanName) {
				this.loanName = loanName;
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
			public String getUpdBy() {
				return updBy;
			}
			public void setUpdBy(String updBy) {
				this.updBy = updBy;
			}
			public Date getUpdDate() {
				return updDate;
			}
			public void setUpdDate(Date updDate) {
				this.updDate = updDate;
			}
			
	
	
}
