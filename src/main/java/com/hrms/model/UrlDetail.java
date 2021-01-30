package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="URL_DTL")
public class UrlDetail implements Serializable {

	/**
	 * 
	 * Auth Surendra Access
	 * 
	 */
	private static final long serialVersionUID = 842108347597795726L;
	@Id
	@Column(name ="URL_ID")
	private int  Url_Id;
	
	@Column(name ="REQ_MAPPING")
	private String	Req_Mapping;
	
	@Column(name ="PAGE_NAME")
	private String Page_Name;
	
	@Column(name = "ACTIVE_YN")
	private String active;

	@Column(name = "INS_BY")
	private String insertedBy;

	@Column(name = "INS_DATE")
	private Date insertedDate;

	@Column(name = "UPDATE_BY")
	private String updateBy;

	@Column(name = "UPDATE_DATE")
	private Date updatedDate;
	
	
	
	
	public int getUrl_Id() {
		return Url_Id;
	}
	public void setUrl_Id(int url_Id) {
		Url_Id = url_Id;
	}
	public String getReq_Mapping() {
		return Req_Mapping;
	}
	public void setReq_Mapping(String req_Mapping) {
		Req_Mapping = req_Mapping;
	}
	public String getPage_Name() {
		return Page_Name;
	}
	public void setPage_Name(String page_Name) {
		Page_Name = page_Name;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
			
	
}
