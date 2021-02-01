package com.hrms.repository;

import java.util.List;

import com.hrms.model.UrlDetail;

public interface PageMappingDao {
	public String PageRequestMapping(String requestMpping, int id);

	List<UrlDetail> getAllPages();

	void addPage(UrlDetail urlDetail);
	
  	UrlDetail findUrlDetailById(String id);
  	
  	 public void updatePage(UrlDetail u); 


	UrlDetail checkUrlDetailExists(UrlDetail urlDetail);
	
	
	public void removePage(String id);
}
