package com.hrms.repository;

import java.util.List;

import com.hrms.model.Bank;



public interface BankDao
{
	List<Bank>getAllBanks();
	void addBank(Bank bank);
	Bank findBankById(String id);
	public void updateBank(Bank d);
	public void removeBank(String id);
	
}
