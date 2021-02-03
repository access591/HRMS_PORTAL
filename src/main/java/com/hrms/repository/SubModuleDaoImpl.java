package com.hrms.repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.SubModule;

@Repository
public class SubModuleDaoImpl extends AbstractGenericDao<SubModule> implements SubModuleDao {
	private Logger logger = LoggerFactory.getLogger(SubModuleDaoImpl.class.getName());


}
