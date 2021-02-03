package com.hrms.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.MiscAllowance;

@Repository
public class MiscAllowanceDeductionDaoImpl extends AbstractGenericDao<MiscAllowance>
		implements MiscAllowanceDeductionDao {
	private Logger logger = LoggerFactory.getLogger(MiscAllowanceDeductionDaoImpl.class.getName());

}
