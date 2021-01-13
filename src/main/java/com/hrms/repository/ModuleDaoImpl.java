package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.Module;
@Repository
public class ModuleDaoImpl implements ModuleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Module> getAllModules() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		criteria.addOrder(Order.asc("seqNo"));
		List<Module> modules = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();
		
		return modules;
	
	}

	@Override
	public void addModule(Module module) {
		 Session session = sessionFactory.openSession(); 
		 Transaction tx = session.beginTransaction();
		 session.persist(module);
		 tx.commit();
		  session.close();
	}

	@Override
	public Module findModule(Module module) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		Module mcode = (Module) criteria.setFetchMode("M_MODULE",FetchMode.SELECT)
				.add(Restrictions.eq("moduleCode", module.getModuleCode())).uniqueResult();
		return mcode;
	}

	@Override
	public Module findModuleById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		Module moduleEdit = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
			.add(Restrictions.eq("moduleCode", id)).uniqueResult();

		return moduleEdit;
	}

	@Override
	public void updateModule(Module m) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(m);
		tx.commit();
		session.close();	
		
	}

	@Override
	public void removeModule(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Module m = (Module) session.load(Module.class, new String(id));
		System.out.println("value of G " + m);

		session.delete(m);
		tx.commit();
		session.close();	
		
	}

	@Override
	public List<Module> getActiveModules() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		List<Module> modulesList = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();
		
		return modulesList;
	}
	@Override
	public List<Module> getAllModulesList(String userCode) {
		Session session = sessionFactory.openSession();
		
		//String sql = "SELECT * FROM M_MODULE  ";
		/*
		 * String
		 * sql="  SELECT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO FROM hrms.m_module m, hrms.m_urights u,hrms.m_sub_module sb ,hrms.m_program pr \r\n"
		 * + "		      where m.MODULE_CODE = u.MODULE_CODE\r\n"
		 * +"             and  m.ACTIVE_YN LIKE '%Y%'\r\n" +
		 * "            and sb.SUB_MODULE_CODE=u.SUB_MODULE_CODE\r\n" +
		 * "             and pr.PRG_CODE=u.PRG_CODE\r\n" +
		 * "			  and u.USER_CODE ="+userCode;
		 */
		
		String sql="  SELECT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO FROM hrms.m_module m, hrms.m_urights u \r\n"
				+ "		      where m.MODULE_CODE = u.MODULE_CODE\r\n"
				+"             and  m.ACTIVE_YN LIKE '%Y%'\r\n"
				+ "			  and u.USER_CODE ="+userCode;
		
		  System.out.println("Surendra Print Sql Query "+userCode);
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Module.class);
		List modules = query.list();
		
		
		return modules;
	}

	

}
