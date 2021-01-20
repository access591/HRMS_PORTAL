package com.hrms.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.model.SubModule;

@Repository
public class SubModuleDaoImpl implements SubModuleDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(SubModuleDaoImpl.class.getName());

	@Override
	public List<SubModule> getAllSubModules() {
		List<SubModule> listSubModule = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SubModule.class);
			listSubModule = (List<SubModule>) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.getAllSubModules" + e.getMessage());
		}
		return listSubModule;

	}

	@Override
	public void addSubModule(SubModule subModule) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(SubModule.class);

			session.persist(subModule);
			tx.commit();
		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.addSubModule" + e.getMessage());
		}

	}

	@Override
	public SubModule findSubModuleById(String id) {
		SubModule subModuleEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SubModule.class);
			subModuleEdit = (SubModule) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("subModuleCode", id)).uniqueResult();

		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.findSubModuleById" + e.getMessage());
		}

		return subModuleEdit;
	}

	@Override
	public void updateSubModule(SubModule subModule) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(subModule);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.updateSubModule" + e.getMessage());
		}

	}

	@Override
	public void removeSubModule(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			SubModule s = (SubModule) session.load(SubModule.class, new String(id));
			session.delete(s);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.removeSubModule" + e.getMessage());
		}

	}

	@Override
	public SubModule checkSubModuleExists(SubModule subModule) {
		SubModule smcode = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SubModule.class);
			smcode = (SubModule) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT)
					.add(Restrictions.eq("subModuleCode", subModule.getSubModuleCode())).uniqueResult();

		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.checkSubModuleExists" + e.getMessage());
		}
		return smcode;
	}

	@Override
	public List<SubModule> getActiveSubModules() {
		List<SubModule> subModulesList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SubModule.class);
			subModulesList = (List<SubModule>) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("SubModuleDaoImpl.getActiveSubModules" + e.getMessage());
		}
		return subModulesList;
	}

}
