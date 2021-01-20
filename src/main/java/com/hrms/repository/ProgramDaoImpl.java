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
import com.hrms.model.Program;


@Repository
public class ProgramDaoImpl implements ProgramDao {

	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(ProgramDaoImpl.class.getName());

	@Override
	public List<Program> getAllPrograms() {
		List<Program> programs = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Program.class);
			programs = (List<Program>) criteria.setFetchMode("M_PROGRAM", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("ProgramDaoImpl.getAllPrograms" + e.getMessage());
		}
		return programs;
	}

	@Override
	public void addProgram(Program program) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(program);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("ProgramDaoImpl.addProgram" + e.getMessage());

		}

	}

	@Override
	public Program checkProgramExists(Program program) {
		Program pmcode = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Program.class);
			pmcode = (Program) criteria.setFetchMode("M_PROGRAM", FetchMode.SELECT)
					.add(Restrictions.eq("programCode", program.getProgramCode())).uniqueResult();

		} catch (Exception e) {
			logger.info("ProgramDaoImpl.checkProgramExists" + e.getMessage());
		}
		return pmcode;
	}

	@Override
	public Program findProgramById(String id) {
		Program programEdit = null;

		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Program.class);
			programEdit = (Program) criteria.setFetchMode("M_PROGRAM", FetchMode.SELECT)
					.add(Restrictions.eq("programCode", id)).uniqueResult();
		} catch (Exception e) {
			logger.info("ProgramDaoImpl.findProgramById" + e.getMessage());
		}
		return programEdit;
	}

	@Override
	public void updateProgram(Program p) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(p);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("ProgramDaoImpl.updateProgram" + e.getMessage());
		}

	}

	@Override
	public void removeProgram(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Program p = (Program) session.load(Program.class, new String(id));
			session.delete(p);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("ProgramDaoImpl.removeProgram" + e.getMessage());
		}
	}

}
