package com.hrms.repository;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Grade;

@Repository
public class GradeMaterDaoImpl extends AbstractGenericDao<Grade> implements GradeMaterDao {

	private Logger logger = LoggerFactory.getLogger(GradeMaterDaoImpl.class.getName());

}
