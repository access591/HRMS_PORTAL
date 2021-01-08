package com.hrms.repository;

import java.util.List;

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

	@Override
	public List<Program> getAllPrograms() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Program.class);
		List<Program> programs = (List<Program>) criteria.setFetchMode("M_PROGRAM", FetchMode.SELECT).list();
		return programs;
	}

	@Override
	public void addProgram(Program program) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(program);
		tx.commit();
		session.close();
	}

	@Override
	public Program checkProgramExists(Program program) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Program.class);
		Program pmcode = (Program) criteria.setFetchMode("M_PROGRAM",FetchMode.SELECT)
				.add(Restrictions.eq("programCode", program.getProgramCode())).uniqueResult();
		return pmcode;
	}

	@Override
	public Program findProgramById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Program.class);
		Program programEdit = (Program) criteria.setFetchMode("M_PROGRAM", FetchMode.SELECT)
			.add(Restrictions.eq("programCode", id)).uniqueResult();

		return programEdit;
	}

	@Override
	public void updateProgram(Program p) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(p);
		tx.commit();
		session.close();	
	}

	@Override
	public void removeProgram(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Program p = (Program) session.load(Program.class, new String(id));
		session.delete(p);
		tx.commit();
		session.close();	
		
	}
	

}
