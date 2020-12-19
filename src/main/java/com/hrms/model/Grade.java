package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name ="M_Grade")
public class Grade implements Serializable
{
	/**
	 * @author Access surendra
	 */
	private static final long serialVersionUID = 633323013185969173L;

	  @Id
		@Column(name = "GRADE_CODE")
		private String Grade_Code;

		@Column(name = "GARDE_NAME")
		private String Garde_Name;
		
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
		
		public String getGrade_Code() {
			return Grade_Code;
		}
		public void setGrade_Code(String grade_Code) {
			Grade_Code = grade_Code;
		}
		public String getGarde_Name() {
			return Garde_Name;
		}
		public void setGarde_Name(String garde_Name) {
			Garde_Name = garde_Name.toUpperCase();
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