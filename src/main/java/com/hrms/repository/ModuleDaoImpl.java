package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
@Repository
public class ModuleDaoImpl implements ModuleDao {
	private Logger logger = LoggerFactory.getLogger(ModuleDaoImpl.class.getName());
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	@Override
	public List<Module> getAllModules() {

		List<Module> modules = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Module.class);
			criteria.addOrder(Order.asc("seqNo"));
			modules = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return modules;

	}

	@Override
	public void addModule(Module module) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(module);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public Module findModule(Module module) {
		Module mcode = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Module.class);
			mcode = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("moduleCode", module.getModuleCode())).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return mcode;
	}

	@Override
	public Module findModuleById(String id) {
		Module moduleEdit = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Module.class);
			moduleEdit = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("moduleCode", id)).uniqueResult();
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		return moduleEdit;
	}

	@Override
	public void updateModule(Module m) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(m);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public void removeModule(String id) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Module m = (Module) session.load(Module.class, new String(id));
			session.delete(m);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<Module> getActiveModules() {
		List<Module> modulesList = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Module.class);
			modulesList = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return modulesList;
	}

	@Override
	public List<Module> getAllModulesList(String userCode) {

		List modules = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();

			String sql = "SELECT  DISTINCT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO  \r\n"
					+ "	FROM 	M_MODULE m ,M_URIGHTS u\r\n"
					+ "	Where m.MODULE_CODE = u.MODULE_CODE\r\n"
					+ " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y'\r\n"
					+ " and u.USER_CODE ='"+ userCode +"'" 
					+ " ORDER BY m.SEQ_NO";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Module.class);
			modules = query.list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return modules;
	}

	@Override
	public List<SubModule> getAllSubModule(String modulecCode, String ucode) {
		List subMOdules = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();

			
			String sql = "SELECT DISTINCT u.SUB_MODULE_CODE,S.INS_DATE,S.INS_BY,S.ACTIVE_YN,S.SUB_MODULE_NAME,S.MODULE_CODE,S.SEQ_NO,S.UPDATE_BY,S.UPDATE_DATE   \r\n"
					+ "	FROM 	M_MODULE m , M_URIGHTS u ,M_SUB_MODULE S \r\n"
					+ "	Where m.MODULE_CODE = u.MODULE_CODE\r\n"
					+ " AND u.MODULE_CODE =S.MODULE_CODE\r\n"
					+ " and u.SUB_MODULE_CODE =S.SUB_MODULE_CODE\r\n"
					+ " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and S.ACTIVE_YN ='Y'\r\n"
					+ " and u.USER_CODE ='" + ucode+"'"
					+ " and S.MODULE_CODE ='" + modulecCode+"'"
					+ " ORDER BY S.SEQ_NO";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(SubModule.class);
			subMOdules = query.list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return subMOdules;
	}

	@Override
	public List<Program> GetAllProgramList(String moduleCode, String smCode, String Ucode) {
		List Programs =null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			String sql = "SELECT  DISTINCT DISTINCT  u.PRG_CODE,p.PRG_NAME,p.MODULE_CODE,p.PRG_TYPE,p.PRG_HREF_NAME,p.ACTIVE_YN,p.INS_BY,p.INS_DATE,p.UPDATE_BY,p.UPDATE_DATE,p.SUB_MODULE_CODE,p.SEQ_NO ,dmoduleCode,dsubMouduleCode \r\n"
					+ " FROM 	M_MODULE m , M_URIGHTS u ,M_SUB_MODULE s ,M_PROGRAM p\r\n"
					+ "  Where m.MODULE_CODE = u.MODULE_CODE\r\n" + "  AND u.MODULE_CODE =s.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =s.SUB_MODULE_CODE\r\n" + "  and u.MODULE_CODE =p.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =p.SUB_MODULE_CODE\r\n" + "  and u.PRG_CODE =p.PRG_code\r\n"
					+ "  and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and s.ACTIVE_YN ='Y' and p.ACTIVE_YN ='Y'\r\n"
					+ "  and u.USER_CODE ='" + Ucode + "'" 
					+ "and u.MODULE_CODE ='" + moduleCode + "'"
					+ "and  u.SUB_MODULE_CODE ='" + smCode + "'" 
					+ " ORDER BY p.SEQ_NO ";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Program.class);
			 Programs = query.list();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		

		return Programs;
	}

}
