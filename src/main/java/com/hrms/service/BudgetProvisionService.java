package com.hrms.service;

import java.util.List;

import com.hrms.model.BudgetProvision;

public interface BudgetProvisionService {
	
	public void saveBudgetProvision(BudgetProvision budgetProvision);
	public List<BudgetProvision> getAllBudgetProvision();
	public BudgetProvision findByBudgetProvisionId(Long budgitProvisionId);
	public void updateBudgetProvision(BudgetProvision budgetProvision);
	public void removeBudgetProvision(Long budgetId);

}