package com.hrms.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.Loan;
import com.hrms.repository.LoanMaterDao;

@Service
public class LoanMaterServiceImpl implements LoanMaterService {
	@Autowired
	LoanMaterDao loanMaterDao;
	@Override
	public void addLoan(Loan loan) {
		loan.setInsDate(new Date());
		loan.setLoanCode(loanMaterDao.getMaxId("LNC"));
		  this.loanMaterDao.saveOrUpdate(loan);
	}
	@Override
	public List<Loan> getAllLoans() {
		List<Loan> listLoan = loanMaterDao.findAll();
		return listLoan;
	}

	@Override
	public Loan findLoanById(String id) {
		return loanMaterDao.findById(id);
	}
	@Override
	public void updateLoan(Loan L) {
		L.setLoanName(L.getLoanName());
		L.setUpdDate(L.getUpdDate());
		L.setActive(L.getActive());
		this.loanMaterDao.saveOrUpdate(L);
	}

	@Override
	public void removeLoan(String id) {
		
		this.loanMaterDao.delete(id);
	}
}
