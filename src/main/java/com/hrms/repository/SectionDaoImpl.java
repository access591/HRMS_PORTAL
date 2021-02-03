package com.hrms.repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Section;

/**
 * @author Access surendra
 *
 */
@Repository
public class SectionDaoImpl extends AbstractGenericDao<Section> implements SectionDao {

	private Logger logger = LoggerFactory.getLogger(SectionDaoImpl.class.getName());


}
