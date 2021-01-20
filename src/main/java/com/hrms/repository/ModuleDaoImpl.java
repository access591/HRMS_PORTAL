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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
@Repository
public class ModuleDaoImpl implements ModuleDao {
	private Logger logger = LoggerFactory.getLogger(ModuleDaoImpl.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Module> getAllModules() {

		List<Module> modules = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Module.class);
			criteria.addOrder(Order.asc("seqNo"));
			modules = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("ModuleDaoImpl.getAllModules" + e.getMessage());
		}

		return modules;

	}

	@Override
	public void addModule(Module module) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(module);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.addModule" + e.getMessage());

		}

	}

	@Override
	public Module findModule(Module module) {
		Module mcode = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Module.class);
			mcode = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("moduleCode", module.getModuleCode())).uniqueResult();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.findModule" + e.getMessage());

		}

		return mcode;
	}

	@Override
	public Module findModuleById(String id) {
		Module moduleEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Module.class);
			moduleEdit = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("moduleCode", id)).uniqueResult();
		}

		catch (Exception e) {
			logger.info("ModuleDaoImpl.findModuleById" + e.getMessage());

		}
		return moduleEdit;
	}

	@Override
	public void updateModule(Module m) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(m);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.updateModule" + e.getMessage());
		}

	}

	@Override
	public void removeModule(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Module m = (Module) session.load(Module.class, new String(id));
			session.delete(m);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.removeModule" + e.getMessage());
		}

	}

	@Override
	public List<Module> getActiveModules() {
		List<Module> modulesList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Module.class);
			modulesList = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("ModuleDaoImpl.getActiveModules" + e.getMessage());
		}
		return modulesList;
	}

	@Override
	public List<Module> getAllModulesList(String userCode) {

		List modules = null;
		try {
			Session session = sessionFactory.openSession();
			String sql = "SELECT  DISTINCT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO\r\n"
					+ " FROM 	hrms.m_module m\r\n"
					+ " INNER JOIN 	hrms.m_sub_module sb  on sb.MODULE_CODE=m.MODULE_CODE \r\n"
					+ " INNER JOIN 	hrms.m_program pr on pr.MODULE_CODE=m.MODULE_CODE and pr.SUB_MODULE_CODE=sb.SUB_MODULE_CODE\r\n"
					+ " INNER JOIN  hrms.m_urights u on u.MODULE_CODE=m.MODULE_CODE and u.SUB_MODULE_CODE=sb.SUB_MODULE_CODE\r\n"
					+ " and u.PRG_CODE=pr.PRG_CODE\r\n" + " where m.ACTIVE_YN LIKE '%Y%' " + "and u.USER_CODE ="
					+ userCode;

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Module.class);
			modules = query.list();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.getAllModulesList" + e.getMessage());
		}

		return modules;
	}

	@Override
	public List<SubModule> getAllSubModule(String modulecCode, String ucode) {
		List subMOdules = null;
		try {
			Session session = sessionFactory.openSession();
			String sql = "SELECT DISTINCT  SMC.SUB_MODULE_CODE,SMC.INS_DATE,SMC.INS_BY,SMC.ACTIVE_YN,SMC.SUB_MODULE_NAME,SMC.MODULE_CODE,SMC.SEQ_NO,SMC.UPDATE_BY,SMC.UPDATE_DATE  \r\n"
					+ "FROM  hrms.m_sub_module SMC , hrms.m_urights ur\r\n" + "where SMC.MODULE_CODE='" + modulecCode
					+ "'" + "and  SMC.SUB_MODULE_CODE= ur.SUB_MODULE_CODE\r\n" + "and ur.user_code='" + ucode + "'";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(SubModule.class);
			subMOdules = query.list();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.getAllSubModule" + e.getMessage());
		}

		return subMOdules;
	}

	@Override
	public List<Program> GetAllProgramList(String moduleCode, String smCode, String Ucode) {
		Session session = sessionFactory.openSession();
		List Programs = null;
		try {
			String sql = "SELECT DISTINCT  pmc.PRG_CODE,pmc.PRG_NAME,pmc.MODULE_CODE,pmc.PRG_TYPE,pmc.PRG_HREF_NAME,pmc.ACTIVE_YN,pmc.INS_BY,pmc.INS_DATE,pmc.UPDATE_BY,pmc.UPDATE_DATE,pmc.SUB_MODULE_CODE,pmc.SEQ_NO,pModuleCode,subModuleCode,dmoduleCode,dsubMouduleCode  \r\n"
					+ "FROM  hrms.m_program pmc, hrms.m_urights ur ,hrms.m_module mm\r\n" + "where mm.MODULE_CODE='"
					+ moduleCode + "'" + "and pmc.SUB_MODULE_CODE='" + smCode + "'"
					+ "and pmc.PRG_CODE= ur.PRG_CODE\r\n" + "and ur.USER_CODE='" + Ucode + "'";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Program.class);
			Programs = query.list();
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.GetAllProgramList" + e.getMessage());
		}

		return Programs;
	}

}
