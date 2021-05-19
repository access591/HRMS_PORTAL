package com.hrms.service;

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

}
