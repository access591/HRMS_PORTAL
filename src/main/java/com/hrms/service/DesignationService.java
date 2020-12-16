package com.hrms.service;

import java.util.List;

import com.hrms.model.Designation;


public interface DesignationService 
{
	public void addDesignation(Designation designation);
	List<Designation>getAllDesignations();
	Designation findDesignationById(String id);
	

}
