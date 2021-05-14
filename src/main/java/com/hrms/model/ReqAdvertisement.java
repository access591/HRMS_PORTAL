package com.hrms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="M_EMPREQ_ADVERTISEMENT")
public class ReqAdvertisement {
	
	@Id
	@Column(name="ADVT_CODE")
	private String advtCode;
	
	@Column(name="ADVT_DATE")
	private String advtDate;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="INS_BY")
	private String insBy;
	
	@Column(name="INS_DATE")
	private Date insDate;
	
	@OneToMany(mappedBy = "reqAdvertisement",cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<ReqAdvertisementDetail> listReqAdvertisementDetail;

	public String getAdvtCode() {
		return advtCode;
	}

	public void setAdvtCode(String advtCode) {
		this.advtCode = advtCode;
	}

	public String getAdvtDate() {
		return advtDate;
	}

	public void setAdvtDate(String advtDate) {
		this.advtDate = advtDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public List<ReqAdvertisementDetail> getListReqAdvertisementDetail() {
		return listReqAdvertisementDetail;
	}

	public void setListReqAdvertisementDetail(List<ReqAdvertisementDetail> listReqAdvertisementDetail) {
		this.listReqAdvertisementDetail = listReqAdvertisementDetail;
	}
	
	

	
	

}
