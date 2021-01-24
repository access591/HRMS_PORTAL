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

			String sql = "SELECT  DISTINCT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO  \r\n"
					+ "	FROM 	M_MODULE m ,M_URIGHTS u\r\n"
					+ "	Where m.MODULE_CODE = u.MODULE_CODE\r\n"
					+ " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y'\r\n"
					+ " and u.USER_CODE =" + userCode + " ORDER BY m.SEQ_NO";

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

			
			String sql = "SELECT DISTINCT u.SUB_MODULE_CODE,S.INS_DATE,S.INS_BY,S.ACTIVE_YN,S.SUB_MODULE_NAME,S.MODULE_CODE,S.SEQ_NO,S.UPDATE_BY,S.UPDATE_DATE   \r\n"
					+ "	FROM 	M_MODULE m , M_URIGHTS u ,M_SUB_MODULE s\r\n"
					+ "	Where m.MODULE_CODE = u.MODULE_CODE\r\n"
					+ " AND U.MODULE_CODE =s.MODULE_CODE\r\n"
					+ " and u.SUB_MODULE_CODE =s.SUB_MODULE_CODE\r\n"
					+ " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and s.ACTIVE_YN ='Y'\r\n"
					+ " and u.USER_CODE =" + ucode + " and s.MODULE_CODE =" + modulecCode
					+ " ORDER BY s.SEQ_NO";

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
		List Programs =null;
		try {
			Session session = sessionFactory.openSession();
			String sql = "SELECT  DISTINCT DISTINCT  U.PRG_CODE,p.PRG_NAME,p.MODULE_CODE,p.PRG_TYPE,p.PRG_HREF_NAME,p.ACTIVE_YN,p.INS_BY,p.INS_DATE,p.UPDATE_BY,p.UPDATE_DATE,p.SUB_MODULE_CODE,p.SEQ_NO ,dmoduleCode,dsubMouduleCode \r\n"
					+ " FROM 	M_MODULE m , M_URIGHTS u ,M_SUB_MODULE s ,M_PROGRAM p\r\n"
					+ "  Where m.MODULE_CODE = u.MODULE_CODE\r\n" + "  AND U.MODULE_CODE =s.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =s.SUB_MODULE_CODE\r\n" + "  and u.MODULE_CODE =p.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =p.SUB_MODULE_CODE\r\n" + "  and u.PRG_CODE =p.PRG_code\r\n"
					+ "  and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and s.ACTIVE_YN ='Y' and p.ACTIVE_YN ='Y'\r\n"
					+ "  and u.USER_CODE ='" + Ucode + "'" + "and u.MODULE_CODE ='" + moduleCode + "'"
					+ "and  u.SUB_MODULE_CODE ='" + smCode + "'" + " ORDER BY P.SEQ_NO ";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Program.class);
			 Programs = query.list();
			
		} catch (Exception e) {
			logger.info("ModuleDaoImpl.GetAllProgramList" + e.getMessage());
		}
		

		return Programs;
	}

}
