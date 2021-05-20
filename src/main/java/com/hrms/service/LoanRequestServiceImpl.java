package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LoanApplication;
import com.hrms.repository.LoanRequestDao;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {
@Autowired
LoanRequestDao  loanRequestDao;
	@Override
	public void addLoanRequest(LoanApplication loanRequest) {
		loanRequest.setAppNo(loanRequestDao.getMaxId("LAR"));
		
		this.loanRequestDao.saveOrUpdate(loanRequest);
	}

}
