package com.hrms.repository;




import java.util.List;



import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
			SQLQuery query = session.createSQLQuery("select PAGE_NAME from URL_DTL where URL_ID=" + id
					+ " and REQ_MAPPING='" + requestMpping + "'").addScalar("PAGE_NAME", new StringType());
			List rows = query.list();
			// urlsp.setPage_name(rows.toString());
			pagname = rows.get(0).toString();

		} catch (Exception e) {
			logger.info("PageMappingDaoImpl.PageRequestMapping" + e.getMessage());

		}

		return pagname;

	}

}
