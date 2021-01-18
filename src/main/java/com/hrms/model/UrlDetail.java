package com.hrms.model;

import java.io.Serializable;

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
	@Column(name ="REQ_MAPPING")
	private String	req_mapping;
	@Column(name ="PAGE_NAME")
	private String page_name;
	@Column(name ="URL_ID")
	private int  url_id;
	
	public String getReq_mapping() {
		return req_mapping;
	}
	public void setReq_mapping(String req_mapping) {
		this.req_mapping = req_mapping;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public int getUrl_id() {
		return url_id;
	}
	public void setUrl_id(int url_id) {
		this.url_id = url_id;
	}
	
			
	
}
