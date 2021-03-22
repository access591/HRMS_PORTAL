package com.hrms.repository;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveGrant;
import com.hrms.model.Module;

@Repository
public class LeaveGrantRegisterDaoImpl extends AbstractGenericDao<LeaveGrant> implements LeaveGrantRegisterDao {
	
	@Override
	public LeaveGrant findYearAndType(String year, String leaveType) {
		Criteria criteria = getSession().createCriteria(Module.class);
		LeaveGrant designationEdit =(LeaveGrant) criteria.setFetchMode("M_EMPLOYEE", FetchMode.SELECT).add(Restrictions.eq("year",year)).add(Restrictions.eq("",leaveType)).uniqueResult(); 

		   return designationEdit;
		}
}
