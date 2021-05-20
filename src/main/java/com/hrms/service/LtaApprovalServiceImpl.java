package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LtaRequest;
import com.hrms.repository.LtaApprovalDao;

@Service
public class LtaApprovalServiceImpl implements LtaApprovalService{
@Autowired
LtaApprovalDao ltaApprovalDao;
	@Override
	public List<LtaRequest> getAllLtaApproval() {
		List<LtaRequest>listLtaApproval=ltaApprovalDao.getAllLtaApproval();
		return listLtaApproval;
	}

}
