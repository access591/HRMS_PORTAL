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
		  this.gradeMaterDao.addGrade(grade);
		 
	}

	@Override
	public List<Grade> getAllGrades() {
		List<Grade> listGrade = gradeMaterDao.getAllGrades();
		return listGrade;
		
	}

	@Override
	public Grade findGradeById(String id) {
		
		return gradeMaterDao.findGradeById(id);
	}

	@Override
	public void updateGrade(Grade g) {
		g.setGarde_Name(g.getGarde_Name());
		g.setUpd_date(g.getUpd_date());
		g.setActive(g.getActive());
		this.gradeMaterDao.updateGrade(g);
		
	}

	@Override
	public void removeGrade(String id) {
		this.gradeMaterDao.removeGrade(id);
		
	}

	@Override
	public boolean checkGradeExists(Grade grade) {
		Grade e = gradeMaterDao.checkGradeExists(grade);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

}