package com.hrms.repository;


import com.hrms.dao.GenericDao;
import com.hrms.model.UrlDetail;

public interface PageMappingDao extends GenericDao<UrlDetail> {
	public String PageRequestMapping(String requestMpping, int id);

}
