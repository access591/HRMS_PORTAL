package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "ARMS_LICENSE_DETAILS")
public class ArmsLicenseDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657442637595445194L;
	@Id
	@Size(max =5)
	@Column(name ="SR_NO")
	int seNo;
	@Size(max =50)
	@Column(name ="NAME")
	private String name;
	@Size(max =50)
	@Column(name = "FATHER_NAME")
	private String fatherName;
	@Size(max =200)
	@Column(name = "ADDRESS_ARMS")
	private String addressArms;
	@Size(max =20)
	@Column(name = "DISTRICT")
	private String  district;
	@Size(max =20)
	@Column(name = "STATE")
	private String state;
	@Size(max =30)
	@Column(name = "ARMS_AREA")
	private String armsArea;

	@Column(name = "DOI")
	private Date doi;

	@Column(name = "DOV")
	private Date dov;
	@Size(max =200)
	@Column(name = "TOA")
	private String toa;
	@Size(max =100)
	@Column(name = "TOP")
	private String top;
	@Size(max =5)
	@Column(name = "NOL")
	private int nol;
	@Size(max =1000)
	@Column(name = "LCD")
	private String lcd;
	@Size(max =1000)
	@Column(name = "DEALER_DETAILS")
	private String dealerDetails;
	public int getSeNo() {
		return seNo;
	}
	public void setSeNo(int seNo) {
		this.seNo = seNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getAddressArms() {
		return addressArms;
	}
	public void setAddressArms(String addressArms) {
		this.addressArms = addressArms;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getArmsArea() {
		return armsArea;
	}
	public void setArmsArea(String armsArea) {
		this.armsArea = armsArea;
	}
	public Date getDoi() {
		return doi;
	}
	public void setDoi(Date doi) {
		this.doi = doi;
	}
	public Date getDov() {
		return dov;
	}
	public void setDov(Date dov) {
		this.dov = dov;
	}
	public String getToa() {
		return toa;
	}
	public void setToa(String toa) {
		this.toa = toa;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public int getNol() {
		return nol;
	}
	public void setNol(int nol) {
		this.nol = nol;
	}
	public String getLcd() {
		return lcd;
	}
	public void setLcd(String lcd) {
		this.lcd = lcd;
	}
	public String getDealerDetails() {
		return dealerDetails;
	}
	public void setDealerDetails(String dealerDetails) {
		this.dealerDetails = dealerDetails;
	}
	
	
}
