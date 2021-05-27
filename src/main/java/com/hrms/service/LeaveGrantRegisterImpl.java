package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LeaveGrant;
import com.hrms.repository.LeaveGrantRegisterDao;
@Service
public class LeaveGrantRegisterImpl  implements LeaveGrantRegisterService{
@Autowired
LeaveGrantRegisterDao leaveGrantRegisterDao;

@Autowired SessionFactory sessionFactory;

@Override
public void addLeaveGrant(LeaveGrant leaveGrant) {
	leaveGrant.setLeaveGrantCode(leaveGrantRegisterDao.getMaxId("LGR"));
	
	try {
		this.leaveGrantRegisterDao.saveOrUpdate(leaveGrant);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	
}

@Override
public List<LeaveGrant> getAllLeaveGrants() {
	List<LeaveGrant> listLeaveGrant = leaveGrantRegisterDao.findAll();
	return listLeaveGrant;
}

@Override
public LeaveGrant findLeaveGrantById(String id) {
	return leaveGrantRegisterDao.findById(id);
}

@Override
public void updateLeaveGrant(LeaveGrant l) {
	this.leaveGrantRegisterDao.saveOrUpdate(l);	
	
}

@Override
public void removeLeaveGrant(String id) {
	this.leaveGrantRegisterDao.delete(id);
	
}


@Override
public List<LeaveGrant> findLeaveGrantByEmpCode(String empCode) {
	// TODO Auto-generated method stub
	return this.leaveGrantRegisterDao.findLeaveGrantByEmp(empCode);
}

@Override
public List<LeaveGrant> findLeaveGrantByEmployeeName(String employeeCode) {
	
	try {
		
		Session session = sessionFactory.openSession();
		Query<LeaveGrant> query = session.createQuery("from LeaveGrant lg "
				+ "inner join fetch lg.empCode e where e.empCode = :employeeCode", LeaveGrant.class);
		query.setParameter("employeeCode", employeeCode);
		List<LeaveGrant> result = query.getResultList();
		return result;
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
}



	

}
