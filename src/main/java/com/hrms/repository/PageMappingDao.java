package com.hrms.repository;

import java.util.List;

import com.hrms.model.UrlDetail;

public interface PageMappingDao {
	public String PageRequestMapping(String requestMpping, int id);

	List<UrlDetail> getAllPages();

	void addPage(UrlDetail urlDetail);

	UrlDetail checkUrlDetailExists(UrlDetail urlDetail);
}
