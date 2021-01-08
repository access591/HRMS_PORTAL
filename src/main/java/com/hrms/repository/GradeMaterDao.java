package com.hrms.repository;

import java.util.List;

import com.hrms.model.Grade;

public interface GradeMaterDao {

	  public void addGrade(Grade grade);
	  List<Grade>getAllGrades();
	  Grade findGradeById(String id);
	   public void updateGrade(Grade d); 
	   public void removeGrade(String id);
	   Grade checkGradeExists(Grade grade);
}
