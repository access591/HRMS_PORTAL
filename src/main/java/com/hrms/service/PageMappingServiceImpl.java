package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.UrlDetail;
import com.hrms.repository.PageMappingDao;

@Service
public class PageMappingServiceImpl implements PageMappingService {
	@Autowired
	private PageMappingDao pageMappingDao;

	@Override
	public String PageRequestMapping(String requestMpping, int id) {

		return pageMappingDao.PageRequestMapping(requestMpping, id);

	}

	@Override
	public List<UrlDetail> getAllPages() {
		List<UrlDetail> listUrlDetail = pageMappingDao.getAllPages();
		return listUrlDetail;
	}

	@Override
	public void addPage(UrlDetail urlDetail) {
		urlDetail.setInsertedDate(new Date());
		this.pageMappingDao.addPage(urlDetail);

	}

	@Override
	public boolean checkUrlDetailExists(UrlDetail urlDetail) {
		UrlDetail e = pageMappingDao.checkUrlDetailExists(urlDetail);
		if (e != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void removePage(String id) {
		this.pageMappingDao.removePage(id);

	}

	@Override
	public UrlDetail findUrlDetailById(String id) {
		return pageMappingDao.findUrlDetailById(id);
	}

	@Override
	public void updatePage(UrlDetail u) {
		u.setPage_Name(u.getPage_Name());
		u.setReq_Mapping(u.getReq_Mapping());
		u.setActive(u.getActive());
		u.setUpdatedDate(u.getUpdatedDate());
		this.pageMappingDao.updatePage(u);

	}
}
