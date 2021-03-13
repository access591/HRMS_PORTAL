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
		grade.setInsDate(new Date());
		grade.setGradeCode(gradeMaterDao.getMaxId("GRD"));
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
		g.setGardeName(g.getGardeName());
		g.setUpdDate(g.getUpdDate());
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