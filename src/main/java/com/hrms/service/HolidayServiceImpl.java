package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Grade;
import com.hrms.model.Holiday;
import com.hrms.repository.HolidayDao;
@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	HolidayDao holidayDao;
	@Override
	public void addHoliday(Holiday holiday) {
		holiday.setIns_date(new Date());
		  this.holidayDao.saveOrUpdate(holiday);
		
	}
	
	@Override
	public List<Holiday> getAllHolidays() {
		List<Holiday> listHoliday = holidayDao.findAll();
		return listHoliday;
	}




	@Override
	public Holiday findHolidayById(String id) {
		
		return holidayDao.findById(id);
	}

	@Override
	public void updateHoliday(Holiday h)
	{	
		h.setDescription(h.getDescription());
		h.setDate_Of_Holiday(h.getDate_Of_Holiday());
		h.setHoliday_Type(h.getHoliday_Type());
		h.setUpd_date(h.getUpd_date());
		h.setActive(h.getActive());
		this.holidayDao.saveOrUpdate(h);
		
		
	}

	@Override
	public void removeHoliday(String id) {
		
		this.holidayDao.delete(id);
	}




}
