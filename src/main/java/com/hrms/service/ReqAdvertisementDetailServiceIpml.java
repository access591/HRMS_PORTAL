package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrms.model.ReqAdvertisementDetail;
import com.hrms.repository.ReqAdvertisementDetailDao;

public class ReqAdvertisementDetailServiceIpml implements ReqAdvertisementDetailService{

	@Autowired ReqAdvertisementDetailDao reqAdvertisementDetailDao;
	@Override
	public void addActivity(ReqAdvertisementDetail reqAdvertisementDetail) {
		this.reqAdvertisementDetailDao.saveOrUpdate(reqAdvertisementDetail);
		
	}

	@Override
	public List<ReqAdvertisementDetail> getAllReqAdvertisement() {
		
		return this.reqAdvertisementDetailDao.findAll();
	}

	@Override
	public ReqAdvertisementDetail findReqAdvertisementById(String id) {
		
		return this.reqAdvertisementDetailDao.findById(id);
	}

	@Override
	public void updateReqAdvertisement(ReqAdvertisementDetail a) {
		this.reqAdvertisementDetailDao.saveOrUpdate(a);
		
	}

	@Override
	public void removeReqAdvertisement(Long id) {
		this.reqAdvertisementDetailDao.delete(id);
		
	}

}
