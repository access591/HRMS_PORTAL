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
			private String Loan_Code;

			@Column(name = "LOAN_NAME")
			private String Loan_Name;
			
			@Column(name = "INS_BY")
			private String Ins_by;
			
			@Column(name = "INS_DATE")
			private Date ins_date = new Date();
			
			@Column(name = "UPD_BY")
			private String upd_by;
			
			@Column(name = "UPD_DATE")
			private Date  upd_date = new Date();
			@Column(name = "ACTIVE_YN")
			private String active;
			public String getLoan_Code() {
				return Loan_Code;
			}
			public void setLoan_Code(String loan_Code) {
				Loan_Code = loan_Code;
			}
			public String getLoan_Name() {
				return Loan_Name;
			}
			public void setLoan_Name(String loan_Name) {
				Loan_Name = loan_Name;
			}
			public String getIns_by() {
				return Ins_by;
			}
			public void setIns_by(String ins_by) {
				Ins_by = ins_by;
			}
			public Date getIns_date() {
				return ins_date;
			}
			public void setIns_date(Date ins_date) {
				this.ins_date = ins_date;
			}
			public String getUpd_by() {
				return upd_by;
			}
			public void setUpd_by(String upd_by) {
				this.upd_by = upd_by;
			}
			public Date getUpd_date() {
				return upd_date;
			}
			public void setUpd_date(Date upd_date) {
				this.upd_date = upd_date;
			}
			public String getActive() {
				return active;
			}
			public void setActive(String active) {
				this.active = active;
			}
			
	
	
}
