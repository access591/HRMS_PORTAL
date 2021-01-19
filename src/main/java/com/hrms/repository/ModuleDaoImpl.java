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
import com.hrms.model.Program;
import com.hrms.model.SubModule;
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
		Module mcode = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
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

		String sql = "SELECT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO,sb.SUB_MODULE_CODE,sb.SUB_MODULE_NAME,sb.MODULE_CODE,sb.INS_BY,sb.INS_DATE,sb.UPDATE_BY,sb.UPDATE_DATE,sb.ACTIVE_YN,pr.PRG_CODE,pr.PRG_NAME,pr.PRG_TYPE,pr.PRG_HREF_NAME,pr.ACTIVE_YN,pr.INS_BY,pr.INS_DATE,pr.UPDATE_BY,pr.UPDATE_DATE,pr.SUB_MODULE_CODE,pr.MODULE_CODE FROM hrms.m_module m, hrms.m_urights u,hrms.m_sub_module sb ,hrms.m_program pr\r\n"
				+ "where m.MODULE_CODE = u.MODULE_CODE\r\n" + "and  m.ACTIVE_YN LIKE '%Y%'\r\n"
				+ "and sb.SUB_MODULE_CODE=u.SUB_MODULE_CODE\r\n" + "and pr.PRG_CODE=u.PRG_CODE\r\n"
				+ "	and u.USER_CODE =" + userCode;

		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Module.class);

		List modules = query.list();

		return modules;
	}

	@Override
	public List<SubModule> getAllSubModule(String modulecCode) {
		Session session = sessionFactory.openSession();

		String sql = "SELECT  SMC.SUB_MODULE_CODE,SMC.INS_DATE,SMC.INS_BY,SMC.ACTIVE_YN,SMC.SUB_MODULE_NAME,SMC.MODULE_CODE,SMC.SEQ_NO,SMC.UPDATE_BY,SMC.UPDATE_DATE  FROM hrms.m_sub_module SMC "
				+ " where SMC.MODULE_CODE=" + modulecCode;

		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(SubModule.class);

		List subMOdules = query.list();

		return subMOdules;
	}

	@Override
	public List<Program> GetAllProgramList(String moduleCode, String smCode) {
		Session session = sessionFactory.openSession();

		String sql = "SELECT  PRG_CODE,PRG_NAME,MODULE_CODE,PRG_TYPE,PRG_HREF_NAME ,ACTIVE_YN,INS_BY,INS_DATE,UPDATE_BY,UPDATE_DATE,SUB_MODULE_CODE,SEQ_NO,pModuleCode,subModuleCode,dmoduleCode,dsubMouduleCode  \r\n"
				+ "FROM  hrms.m_program pmc " + "where pmc.MODULE_CODE=" + moduleCode + " and pmc.SUB_MODULE_CODE='"
				+ smCode + "'";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Program.class);
		List Programs = query.list();

		return Programs;
	}

}
