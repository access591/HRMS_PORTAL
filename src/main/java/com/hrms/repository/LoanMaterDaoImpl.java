package com.hrms.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Loan;

@Repository
public class LoanMaterDaoImpl extends AbstractGenericDao<Loan> implements LoanMaterDao {

	private Logger logger = LoggerFactory.getLogger(LoanMaterDaoImpl.class.getName());

}
