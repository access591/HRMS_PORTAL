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
		bank.setBank_Code(bankDao.getMAX_Id("BNK"));
		this.bankDao.saveOrUpdate(bank);
	}

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> listBank = bankDao.findAll();
		return listBank;
	}

	@Override
	public Bank findBankById(String id) {
		return bankDao.findById(id);
	}

	@Override
	public void updateBank(Bank d) {
		d.setBank_Code(d.getBank_Code());
		d.setUpd_date(d.getUpd_date());
		d.setActive(d.getActive());
		this.bankDao.saveOrUpdate(d);

	}

	@Override
	public void removeBank(String id) {
		this.bankDao.delete(id);

	}


}
