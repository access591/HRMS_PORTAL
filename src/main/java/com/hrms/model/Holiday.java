package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name ="M_Holiday")
public class Holiday implements Serializable
{

	/**
	 * @author Access surendra
	 */
	private static final long serialVersionUID = 3252326016046684834L;
	 @Id
		@Column(name = "HOLIDAY_CODE")
	    private String 	Holiday_Code;
	 
	    @Column(name = "DESCRIPTION")
	    private String  description;
	    
	    @Column(name ="DATE_OF_HOLIDAY")
	    @DateTimeFormat(pattern ="yyyy-mm-dd")
	    @Temporal(TemporalType.DATE)
	    private Date Date_Of_Holiday;
	    
	    @Column(name ="HOLIDAY_TYPE")
	    private String  Holiday_Type;
	    
	    @Column(name ="ACTIVE")
	    private String active;
	    
	    @Column(name = "INS_BY")
		private String Ins_by;
		
		@Column(name = "INS_DATE")
		private Date ins_date = new Date();
		
		@Column(name = "UPD_BY")
		private String upd_by;
		
		@Column(name = "UPD_DATE")
		private Date  upd_date = new Date();

		public String getHoliday_Code() {
			return Holiday_Code;
		}

		public void setHoliday_Code(String holiday_Code) {
			Holiday_Code = holiday_Code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getDate_Of_Holiday() {
			return Date_Of_Holiday;
		}

		public void setDate_Of_Holiday(Date date_Of_Holiday) {
			Date_Of_Holiday = date_Of_Holiday;
		}

		public String getHoliday_Type() {
			return Holiday_Type;
		}

		public void setHoliday_Type(String holiday_Type) {
			Holiday_Type = holiday_Type;
		}

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
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
		
		
	    
	
	
}
