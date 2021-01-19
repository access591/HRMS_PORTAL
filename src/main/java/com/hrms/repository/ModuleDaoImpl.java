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

		String sql="SELECT  DISTINCT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO\r\n"
				+ " FROM 	hrms.m_module m\r\n"
				+ " INNER JOIN 	hrms.m_sub_module sb  on sb.MODULE_CODE=m.MODULE_CODE \r\n"
				+ " INNER JOIN 	hrms.m_program pr on pr.MODULE_CODE=m.MODULE_CODE and pr.SUB_MODULE_CODE=sb.SUB_MODULE_CODE\r\n"
				+ " INNER JOIN  hrms.m_urights u on u.MODULE_CODE=m.MODULE_CODE and u.SUB_MODULE_CODE=sb.SUB_MODULE_CODE\r\n"
				+ " and u.PRG_CODE=pr.PRG_CODE\r\n"
				+ " where m.ACTIVE_YN LIKE '%Y%' "
				+ "and u.USER_CODE ="+userCode;
		
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Module.class);
		List modules = query.list();
		return modules;
	}

	@Override
	public List<SubModule> getAllSubModule(String modulecCode, String ucode) {
		Session session = sessionFactory.openSession();
		
		String sql = "SELECT DISTINCT  SMC.SUB_MODULE_CODE,SMC.INS_DATE,SMC.INS_BY,SMC.ACTIVE_YN,SMC.SUB_MODULE_NAME,SMC.MODULE_CODE,SMC.SEQ_NO,SMC.UPDATE_BY,SMC.UPDATE_DATE  \r\n"
				+ "FROM  hrms.m_sub_module SMC , hrms.m_urights ur\r\n" 
				+ "where SMC.MODULE_CODE='"+modulecCode + "'"
				+"and  SMC.SUB_MODULE_CODE= ur.SUB_MODULE_CODE\r\n"
				+ "and ur.user_code='"+ucode+ "'";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(SubModule.class);
		List subMOdules = query.list();
		return subMOdules;
	}

	@Override
	public List<Program> GetAllProgramList(String moduleCode, String smCode, String Ucode) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT DISTINCT  pmc.PRG_CODE,pmc.PRG_NAME,pmc.MODULE_CODE,pmc.PRG_TYPE,pmc.PRG_HREF_NAME,pmc.ACTIVE_YN,pmc.INS_BY,pmc.INS_DATE,pmc.UPDATE_BY,pmc.UPDATE_DATE,pmc.SUB_MODULE_CODE,pmc.SEQ_NO,pModuleCode,subModuleCode,dmoduleCode,dsubMouduleCode  \r\n"
				+ "FROM  hrms.m_program pmc, hrms.m_urights ur ,hrms.m_module mm\r\n" + "where mm.MODULE_CODE='"
				+ moduleCode + "'" 
				+ "and pmc.SUB_MODULE_CODE='" 
				+ smCode + "'" 
				+ "and pmc.PRG_CODE= ur.PRG_CODE\r\n"
				+ "and ur.USER_CODE='" + Ucode + "'";

		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Program.class);
		List Programs = query.list();
		return Programs;
	}

}
