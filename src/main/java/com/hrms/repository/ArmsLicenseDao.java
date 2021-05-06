package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.Employee;

public interface ArmsLicenseDao extends GenericDao<ArmsLicenseDetails> {

	ArmsLicenseDetails findArmsByEmpEmpCode(String id);

}
