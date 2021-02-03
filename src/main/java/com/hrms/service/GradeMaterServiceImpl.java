package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.Grade;
import com.hrms.repository.GradeMaterDao;

@Service
public class GradeMaterServiceImpl implements GradeMaterService{
	@Autowired
	GradeMaterDao gradeMaterDao;

	@Override
	public void addGrade(Grade grade) {
		grade.setIns_date(new Date());
		this.gradeMaterDao.saveOrUpdate(grade);

	}

	@Override
	public List<Grade> getAllGrades() {
		List<Grade> listGrade = gradeMaterDao.findAll();
		return listGrade;

	}

	@Override
	public Grade findGradeById(String id) {

		return gradeMaterDao.findById(id);
	}

	@Override
	public void updateGrade(Grade g) {
		g.setGarde_Name(g.getGarde_Name());
		g.setUpd_date(g.getUpd_date());
		g.setActive(g.getActive());
		this.gradeMaterDao.saveOrUpdate(g);

	}

	@Override
	public void removeGrade(String id) {
		this.gradeMaterDao.delete(id);

	}

	@Override
	public boolean checkGradeExists(Grade grade) {
		Grade e = gradeMaterDao.existOrNot(grade);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

}