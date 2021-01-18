package com.hrms.repository;




import java.util.List;



import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.UrlDetail;

@Repository
public class PageMappingDaoImpl implements PageMappingDao {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public String PageRequestMapping(String requestMpping, int id) {
		Session session = sessionFactory.openSession();
		UrlDetail urlsp = new UrlDetail();
		SQLQuery query = session.createSQLQuery("select page_name from hrms.url_dtl where url_id="+id+" and req_mapping='"+requestMpping+"'").addScalar("page_name", new StringType());
		List rows = query.list();
	       //urlsp.setPage_name(rows.toString());
			String pagname=rows.get(0).toString();

	        return  pagname;
		
	}

}