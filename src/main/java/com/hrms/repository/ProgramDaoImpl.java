package com.hrms.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Program;


@Repository
public class ProgramDaoImpl extends AbstractGenericDao<Program> implements ProgramDao {


	private Logger logger = LoggerFactory.getLogger(ProgramDaoImpl.class.getName());



}
