package com.hrms.repository;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Leave;

@Repository
public class LeaveDaoImpl extends AbstractGenericDao<Leave> implements LeaveDao {

	private Logger logger = LoggerFactory.getLogger(LeaveDaoImpl.class.getName());
}
