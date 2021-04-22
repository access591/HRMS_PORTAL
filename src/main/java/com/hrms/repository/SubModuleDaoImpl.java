package com.hrms.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.SubModule;

@Repository
public class SubModuleDaoImpl extends AbstractGenericDao<SubModule> implements SubModuleDao {
	private Logger logger = LoggerFactory.getLogger(SubModuleDaoImpl.class.getName());
	@Override
	public SubModule checkSubModuleExists(SubModule subModule) {
		SubModule subModuleName = null;
		try {
			
			Criteria criteria = getSession().createCriteria(SubModule.class);
			subModuleName = (SubModule) criteria.setFetchMode("SUB_MODULE_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("subModuleName", subModule.getSubModuleName())).uniqueResult();

		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.checkSubModuleExists" + e.getMessage());
		}

		return subModuleName;
	}
	@Override
	public SubModule checkSubModuleSeqExists(SubModule subModule) {

		
		
		SubModule Subsq = null;
		try {
			
			Criteria criteria = getSession().createCriteria(SubModule.class);
			Subsq = (SubModule) criteria.setFetchMode("SEQ_NO", FetchMode.SELECT)
					.add(Restrictions.eq("seqNoSubModule", subModule.getSeqNoSubModule())).uniqueResult();

		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.checkDepartmentExists" + e.getMessage());
		}

		return Subsq;
	
	}
	


}
