package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.model.UrlDetail;

@Repository
public class PageMappingDaoImpl implements PageMappingDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(PageMappingDaoImpl.class.getName());

	@Override
	public String PageRequestMapping(String requestMpping, int id) {
		String pagname = null;
		try {
			Session session = sessionFactory.openSession();
			UrlDetail urlsp = new UrlDetail();
			SQLQuery query = session.createSQLQuery(
					"select PAGE_NAME from URL_DTL where URL_ID=" + id + " and REQ_MAPPING='" + requestMpping + "'")
					.addScalar("PAGE_NAME", new StringType());
			List rows = query.list();
			// urlsp.setPage_name(rows.toString());
			pagname = rows.get(0).toString();

		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.PageRequestMapping" + e.getMessage());

		}

		return pagname;

	}

	@Override
	public List<UrlDetail> getAllPages() {
		List<UrlDetail> listUrlDetail = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UrlDetail.class);
			listUrlDetail = (List<UrlDetail>) criteria.setFetchMode("URL_DTL", FetchMode.SELECT).list();
		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.getAllPages" + e.getMessage());
		}
		return listUrlDetail;
	}

	@Override
	public void addPage(UrlDetail urlDetail) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(urlDetail);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.addPage" + e.getMessage());
		}

	}

	@Override
	public UrlDetail checkUrlDetailExists(UrlDetail urlDetail) {
		UrlDetail urlid = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UrlDetail.class);
			urlid = (UrlDetail) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT)
					.add(Restrictions.eq("Url_Id", urlDetail.getUrl_Id())).uniqueResult();

		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.checkUrlDetailExists" + e.getMessage());
		}

		return urlid;
	}

	@Override
	public void removePage(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			UrlDetail u = (UrlDetail) session.load(UrlDetail.class, new String(id));
			session.delete(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.removePage" + e.getMessage());
		}

	}

	@Override
	public UrlDetail findUrlDetailById(String id) {
		UrlDetail editUrlDetail = null;
		try {

			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UrlDetail.class);
			editUrlDetail = (UrlDetail) criteria.setFetchMode("Url_Id", FetchMode.SELECT)
					.add(Restrictions.eq("Url_Id", id)).uniqueResult();

		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.findUrlDetailById" + e.getMessage());
		}
		return editUrlDetail;
	}

	@Override
	public void updatePage(UrlDetail u) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.updatePage" + e.getMessage());
		}

	}
}
