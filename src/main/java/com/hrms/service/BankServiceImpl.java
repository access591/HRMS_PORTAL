package com.hrms.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Bank;
import com.hrms.repository.BankDao;
@Service
public class BankServiceImpl implements BankService {
	@Autowired
	BankDao bankDao;

	@Override
	public void addBank(Bank bank) {
		bank.setIns_date(new Date());
		this.bankDao.addBank(bank);
	}

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> listBank = bankDao.getAllBanks();
		return listBank;
	}
	
	@Override
	public Bank findBankById(String id) {
		return bankDao.findBankById(id);
	}

	@Override
	public void updateBank(Bank d) {
		d.setBank_Code(d.getBank_Code());
		d.setUpd_date(d.getUpd_date());
		d.setActive(d.getActive());
		this.bankDao.updateBank(d);

	}

	@Override
	public void removeBank(String id) {
		this.bankDao.removeBank(id);

	}


}
