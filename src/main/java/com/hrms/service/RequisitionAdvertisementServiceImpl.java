package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ReqAdvertisement;
import com.hrms.repository.RequisitionAdvertisementDao;

@Service
public class RequisitionAdvertisementServiceImpl implements  RequisitionAdvertisementService{

	@Autowired RequisitionAdvertisementDao requisitionAdvertisementDao;
	@Autowired SessionFactory sessionFactory;
	@Override
	public void addActivity(ReqAdvertisement reqAdvertisement) {
		//this.requisitionAdvertisementDao.saveOrUpdate(reqAdvertisement);
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    reqAdvertisement.setAdvtCode(requisitionAdvertisementDao.getMaxId("ADT"));
	    session.save(reqAdvertisement);
	    //session.saveOrUpdate(employeReq);
	    session.getTransaction().commit();
	    session.clear();
	    session.close();
		
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
		//this.requisitionAdvertisementDao.saveOrUpdate(a);
		Session session = sessionFactory.openSession();
		ReqAdvertisement r = session.find(ReqAdvertisement.class, a.getAdvtCode());
		r.getListReqAdvertisementDetail().clear();
		r.getListReqAdvertisementDetail().addAll(a.getListReqAdvertisementDetail());
		session.beginTransaction();
		session.merge(a);
		session.getTransaction().commit();
		
	}

	@Override
	public void removeReqAdvertisement(String id) {
		this.requisitionAdvertisementDao.delete(id);
		
	}

	
	
}
