package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.model.Section;

/**
 * @author Access surendra
 *
 */
@Repository
public class SectionDaoImpl implements SectionDao {
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(SectionDaoImpl.class.getName());

	/**
	 * 
	 * add section Method
	 */
	@Override
	public void addSection(Section section) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(section);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

	}

	/**
	 * 
	 * List section Method
	 */
	@Override
	public List<Section> getAllSections() {
		List<Section> listSection = null;
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Section.class);
			listSection = (List<Section>) criteria.setFetchMode("M_SECTION", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listSection;
	}

	/**
	 * edit find Unique result section Method
	 */
	@Override
	public Section findSectionById(String id) {
		Section sectionEdit = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Section.class);
			sectionEdit = (Section) criteria.setFetchMode("M_SECTION", FetchMode.SELECT)
					.add(Restrictions.eq("Sect_Code", id)).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sectionEdit;

	}

	/**
	 * 
	 * update section Method
	 */
	@Override
	public void updateSection(Section d) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(d);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * delete section Method
	 */
	@Override
	public void removeSection(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Section p = (Section) session.load(Section.class, new String(id));
			session.delete(p);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * add check already record exit or not section Method
	 */
	@Override
	public Section checkSectionExists(Section section) {
		Section secode = null;

		try {

			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Section.class);
			secode = (Section) criteria.setFetchMode("M_SECTION", FetchMode.SELECT)
					.add(Restrictions.eq("Sect_Code", section.getSect_Code())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return secode;
	}

}
