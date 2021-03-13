package com.hrms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
import com.hrms.model.UserRights;

@Repository
public class ModuleDaoImpl extends AbstractGenericDao<Module> implements ModuleDao {
	private Logger logger = LoggerFactory.getLogger(ModuleDaoImpl.class.getName());
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

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
	public List<Module> getAllModulesList(String userCode) {

		List modules = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			
			  String sql =
			  "SELECT  DISTINCT u.MODULE_CODE,m.MODULE_NAME,m.ACTIVE_YN,m.INS_BY,m.INS_DATE,m.UPDATE_BY,m.UPDATE_DATE,m.SEQ_NO  \r\n"
			  + "	FROM 	M_MODULE m ,M_URIGHTS u\r\n" +
			  "	Where m.MODULE_CODE = u.MODULE_CODE\r\n" +
			  " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y'\r\n" + " and u.USER_CODE ='" +
			  userCode + "'" + " ORDER BY m.SEQ_NO";
			  
			  SQLQuery query = session.createSQLQuery(sql); query.addEntity(Module.class);
			  modules = query.list();
			  	
				/*
				 * Criteria criteriaUser=session.createCriteria(UserRights.class).setProjection(
				 * Projections.distinct(Projections.projectionList().
				 * add(Projections.property("module_code"),"module_code")));
				 * criteriaUser.add(Restrictions.eq("user_code", userCode)); List<UserRights>
				 * moduleName=criteriaUser.list();
				 * System.out.println("moduleName==========="+moduleName); List <String>
				 * moduleNameuser =new ArrayList<String>(); for(UserRights right:moduleName) {
				 * moduleNameuser.add(right.getModule_code());
				 * System.out.println("Module............"+right.getModule_code());
				 * 
				 * } System.out.println(" moduleNameuser size"+moduleNameuser.size()); modules =
				 * (List) criteriaUser.setFetchMode("M_MODULE", FetchMode.SELECT)
				 * .add(Restrictions.eq("moduleCode", moduleNameuser)).uniqueResult();
				 */
				  
				 
			 
			
			
			/*
			 * final Session session = this.sessionFactory.getCurrentSession(); Criteria
			 * moduleCriteria = session.createCriteria(Module.class); Criteria userCriteria
			 * = userCriteria.createCriteria("Myuser");
			 * userCriteria.add(Restrictions.gt(userCode); List results =
			 * moduleCriteria.list();
			 */
			
			
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
					+ "	FROM 	M_MODULE m , M_URIGHTS u ,M_SUB_MODULE S "
					+ "	Where m.MODULE_CODE = u.MODULE_CODE\r\n" + " AND u.MODULE_CODE =S.MODULE_CODE\r\n"
					+ " and u.SUB_MODULE_CODE =S.SUB_MODULE_CODE\r\n"
					+ " and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and S.ACTIVE_YN ='Y'\r\n" + " and u.USER_CODE ='"
					+ ucode + "'" + " and S.MODULE_CODE ='" + modulecCode + "'" + " ORDER BY S.SEQ_NO";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(SubModule.class);
			subMOdules = query.list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return subMOdules;
	}

	@Override
	public List<Program> getAllProgramList(String moduleCode, String smCode, String userCode) {
		List programs = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			String sql = "SELECT  DISTINCT DISTINCT  u.PRG_CODE,p.PRG_NAME,p.MODULE_CODE,p.PRG_TYPE,p.PRG_HREF_NAME,p.ACTIVE_YN,p.INS_BY,p.INS_DATE,p.UPDATE_BY,p.UPDATE_DATE,p.SUB_MODULE_CODE,p.SEQ_NO ,dmoduleCode,dsubMouduleCode \r\n"
					+ " FROM 	M_MODULE m , M_URIGHTS u ,M_SUB_MODULE s ,M_PROGRAM p"
					+ "  Where m.MODULE_CODE = u.MODULE_CODE\r\n" + "  AND u.MODULE_CODE =s.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =s.SUB_MODULE_CODE\r\n" + "  and u.MODULE_CODE =p.MODULE_CODE\r\n"
					+ "  and u.SUB_MODULE_CODE =p.SUB_MODULE_CODE\r\n" + "  and u.PRG_CODE =p.PRG_code\r\n"
					+ "  and m.ACTIVE_YN ='Y' AND u.ACTIVE_YN ='Y' and s.ACTIVE_YN ='Y' and p.ACTIVE_YN ='Y'\r\n"
					+ "  and u.USER_CODE ='" + userCode + "'" + "and u.MODULE_CODE ='" + moduleCode + "'"
					+ "and  u.SUB_MODULE_CODE ='" + smCode + "'" + " ORDER BY p.SEQ_NO ";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Program.class);
			programs = query.list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return programs;
	}

}
