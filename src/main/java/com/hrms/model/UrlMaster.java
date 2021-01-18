package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class UrlMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 842108347597795726L;
	@Id
	@Column(name ="URL_ID")
	private int url_id;
	@Column(name ="SR_NO")
	private int sr_no;
	@Column(name ="SCREEN_TYPE")
	private String screen_type;
	public int getUrl_id() {
		return url_id;
	}
	public void setUrl_id(int url_id) {
		this.url_id = url_id;
	}
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}
	public String getScreen_type() {
		return screen_type;
	}
	public void setScreen_type(String screen_type) {
		this.screen_type = screen_type;
	}
	
}
