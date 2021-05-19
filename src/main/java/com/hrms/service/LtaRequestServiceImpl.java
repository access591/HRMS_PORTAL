package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LtaRequest;
import com.hrms.repository.LtaRequestDao;
@Service
public class LtaRequestServiceImpl implements LtaRequestService {
@Autowired
LtaRequestDao ltaRequestDao;
	@Override
	public void addLtaRequest(LtaRequest ltaRequest) {
		ltaRequest.setLtaCode(ltaRequestDao.getMaxId("LTA"));
		this.ltaRequestDao.saveOrUpdate(ltaRequest);
				
	}
	@Override
	public List<LtaRequest> getAllLTARequest() {
		List<LtaRequest> ltaList= ltaRequestDao.findAll() ;
		return ltaList;
	}

}
