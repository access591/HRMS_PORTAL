package com.hrms.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Department;
@Repository
public class DepartmentDaoImpl extends AbstractGenericDao<Department> implements DepartmentDao {
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());

	
	
}

