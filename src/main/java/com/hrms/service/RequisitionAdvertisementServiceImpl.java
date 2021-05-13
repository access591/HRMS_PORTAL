package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ReqAdvertisement;
import com.hrms.repository.RequisitionAdvertisementDao;

@Service
public class RequisitionAdvertisementServiceImpl implements  RequisitionAdvertisementService{

	@Autowired RequisitionAdvertisementDao requisitionAdvertisementDao;
	@Override
	public void addActivity(ReqAdvertisement reqAdvertisement) {
		this.requisitionAdvertisementDao.saveOrUpdate(reqAdvertisement);
		
	}

	@Override
	public List<ReqAdvertisement> getAllReqAdvertisement() {
		
		return this.requisitionAdvertisementDao.findAll();
	}

	@Override
	public ReqAdvertisement findReqAdvertisementById(String id) {
		
		return this.requisitionAdvertisementDao.findById(id);
	}

	@Override
	public void updateReqAdvertisement(ReqAdvertisement a) {
		this.requisitionAdvertisementDao.saveOrUpdate(a);
		
	}

	@Override
	public void removeReqAdvertisement(String id) {
		this.requisitionAdvertisementDao.delete(id);
		
	}

	
	
}
