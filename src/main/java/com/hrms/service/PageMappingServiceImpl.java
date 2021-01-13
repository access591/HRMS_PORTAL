package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.repository.PageMappingDao;



@Service
public class PageMappingServiceImpl  implements PageMappingService{
	@Autowired
	private PageMappingDao pageMappingDao;
	@Override
	public String PageRequestMapping(String requestMpping, int id)
	{
	
		
		return pageMappingDao.PageRequestMapping(requestMpping,id);
	
		
	}

}
