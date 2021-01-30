package com.hrms.service;

import java.util.List;

import com.hrms.model.UrlDetail;

public interface PageMappingService
{
	public String PageRequestMapping(String requestMpping, int id);

	List<UrlDetail> getAllPages();

	public void addPage(UrlDetail urlDetail);

	boolean checkUrlDetailExists(UrlDetail urlDetail);
	
	public void removePage(String id);
	
}
