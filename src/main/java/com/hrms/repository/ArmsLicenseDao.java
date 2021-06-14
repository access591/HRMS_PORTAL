package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.ArmsLicenseDetails;


public interface ArmsLicenseDao extends GenericDao<ArmsLicenseDetails> {

	ArmsLicenseDetails findArmsByEmpEmpCode(String id);

}
