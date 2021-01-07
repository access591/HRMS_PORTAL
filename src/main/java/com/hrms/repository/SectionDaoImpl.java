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

import com.hrms.model.Section;


@Repository
public class SectionDaoImpl implements SectionDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addSection(Section section) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(section);
		tx.commit();
		session.close();

	}

	@Override
	public List<Section> getAllSections() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Section.class);
		List<Section> listSection = (List<Section>) criteria.setFetchMode("M_SECTION", FetchMode.SELECT).list();
		return listSection;
	}
	@Override
	public Section findSectionById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Section.class);
		Section sectionEdit = (Section) criteria.setFetchMode("M_SECTION", FetchMode.SELECT)
				.add(Restrictions.eq("Sect_Code", id)).uniqueResult();

		return sectionEdit;

	}

	@Override
	public void updateSection(Section d) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(d);
		tx.commit();
		session.close();

	}

	@Override
	public void removeSection(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Section p = (Section) session.load(Section.class, new String(id));
		System.out.println("value of p " + p);

		session.delete(p);
		tx.commit();
		session.close();

	}

	@Override
	public Section checkSectionExists(Section section) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Section.class);
		Section secode = (Section) criteria.setFetchMode("M_SECTION",FetchMode.SELECT)
				.add(Restrictions.eq("Sect_Code", section.getSect_Code())).uniqueResult();
		return secode;
	}

	
}
