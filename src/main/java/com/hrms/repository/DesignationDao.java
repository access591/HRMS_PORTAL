package com.hrms.repository;

import java.util.List;

import com.hrms.model.Designation;


public interface DesignationDao
{
	List<Designation>getAllDesignations();
	void addDesignation(Designation designation);
	Designation findDesignationById(String id);
}
