package com.hrms.service;

import java.util.List;

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
	@Override
	public List<LoanApplication> getAllRequest() {
		List<LoanApplication> loanreq= loanRequestDao.findAll() ;
		return loanreq;
		
	}
	@Override
	public void removeLoanRequest(String id) {
	this.loanRequestDao.delete(id);
		
	}
	@Override
	public LoanApplication findByIdLoanReq(String id) {
	
		return loanRequestDao.findById(id);
	}
	@Override
	public void updateLoanRequest(LoanApplication loanRequest) {
	String ui=loanRequest.getAppNo();
	System.out.println("XXX>>>>>>>>>>>>>>>>>>>>>"+ui);
		this.loanRequestDao.saveOrUpdate(loanRequest);
	}

}
