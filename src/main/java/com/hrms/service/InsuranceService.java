package com.hrms.service;

import com.hrms.model.Insurance;

public interface InsuranceService {
	public void addInsurance(Insurance insurance);
	Insurance findInsuranceById(String id);
	public void updateInsurance(Insurance d);
	public void removeInsurance(String id);

}
