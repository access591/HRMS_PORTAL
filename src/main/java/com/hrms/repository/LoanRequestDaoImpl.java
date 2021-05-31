package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LoanApplication;
@Repository
public class LoanRequestDaoImpl extends AbstractGenericDao<LoanApplication> implements LoanRequestDao{

}
