package com.hrms.repository;

import java.util.List;

import com.hrms.model.Loan;

public interface LoanMaterDao {
	 public void addLoan(Loan loan);
	   List<Loan>getAllLoans();
	   Loan findLoanById(String id);
	   public void updateLoan(Loan L); 
	   public void removeLoan(String id);

}
