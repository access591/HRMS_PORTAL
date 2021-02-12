package com.hrms.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.Section;
import com.hrms.repository.SectionDao;
@Service
public class SectionServiceImpl implements SectionService {
	@Autowired
	SectionDao sectionDao;

	@Override
	public void addSection(Section section) {
		section.setIns_date(new Date());
		section.setSect_Code(sectionDao.getMAX_Id("SEC"));
		this.sectionDao.saveOrUpdate(section);
	}

	@Override
	public List<Section> getAllSections() {
		List<Section> listSection = sectionDao.findAll();
		return listSection;
	}

	@Override
	public Section findSectionById(String id) {
		return sectionDao.findById(id);
	}

	@Override
	public void updateSection(Section d) {
		d.setSect_Code(d.getSect_Code());
		d.setUpd_date(d.getUpd_date());
		d.setActive(d.getActive());
		this.sectionDao.saveOrUpdate(d);

	}

	@Override
	public void removeSection(String id) {
		this.sectionDao.delete(id);

	}

	@Override
	public boolean checkSectionExists(Section section) {

		Section e = sectionDao.existOrNot(section);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

}
