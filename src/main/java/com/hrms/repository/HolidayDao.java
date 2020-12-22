package com.hrms.repository;

import java.util.List;

import com.hrms.model.Holiday;

public interface HolidayDao {
	
	public void addHoliday(Holiday holiday);
	   List<Holiday>getAllHolidays();
	   Holiday findHolidayById(String id);
	   public void updateHoliday(Holiday h); 
	   public void removeHoliday(String id);

}
