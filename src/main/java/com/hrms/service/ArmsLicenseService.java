package com.hrms.service;

import java.util.List;

import com.hrms.model.ArmsLicenseDetails;


public interface ArmsLicenseService {

	void addArmsLicenseDetails(ArmsLicenseDetails armsLicense);

	ArmsLicenseDetails findArmsLicenseById(String id);

	List<ArmsLicenseDetails> getAllArmsLicenses();

	void updateArmsLicenseService(ArmsLicenseDetails armsLicense);
	public ArmsLicenseDetails findArmsByEmpEmpCode(String id);
	void removeArmsLicenseService(String id2);

}
