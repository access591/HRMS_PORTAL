package com.hrms.service;

import java.util.List;

import com.hrms.model.StaffPostingDuties;

public interface StaffPostingDutiesService {

	void addStaffPostingDuties(StaffPostingDuties staffduties);

	void removestaffDuties(String id);

	List<StaffPostingDuties> getAllStaffPostingDuties();

	StaffPostingDuties StaffPostingDutieById(String id);

	void UpdateStaffPostingDuties(StaffPostingDuties staffduties);

}
