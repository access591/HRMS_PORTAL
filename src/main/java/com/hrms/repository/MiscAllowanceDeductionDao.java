package com.hrms.repository;

import java.util.List;

import com.hrms.model.MiscAllowance;

public interface MiscAllowanceDeductionDao 
{
	public void addMiscAllowanceDeduction(MiscAllowance miscAllowance);
	List<MiscAllowance>getAllMiscAllowanceDeduction();
	MiscAllowance findMiscAllowanceDeductionById(String id);
	public void updateMiscAllowanceDeduction(MiscAllowance M);
	public void removeMiscAllowanceDeduction(String id);

}
