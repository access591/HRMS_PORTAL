package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Designation;

@Repository
public class DesignationDaoImpl extends AbstractGenericDao<Designation>  implements DesignationDao {

	private Logger logger = LoggerFactory.getLogger(DesignationDaoImpl.class.getName());

	
}
