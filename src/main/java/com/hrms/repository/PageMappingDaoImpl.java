package com.hrms.repository;




import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PageMappingDaoImpl implements PageMappingDao {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public String PageRequestMapping(String requestMpping, int id) {
		Session session = sessionFactory.openSession();
		
		  String sql="select page_name from hrms.url_dtl" +
		  " where url_id="+id + "and req_mapping="+requestMpping;
		    SQLQuery query = session.createSQLQuery(sql);
	        return  null;
		
	}

}
