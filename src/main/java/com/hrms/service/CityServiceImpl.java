package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.City;
import com.hrms.repository.CityDao;

@Service
public class CityServiceImpl implements CityService {
@Autowired
CityDao cityDao;
	@Override
	public void addCity(City city) {
		
		
	}

	@Override
	public List<City> getAllCities() {
		
		return null;
	}

	@Override
	public City findCityById(String id) {
		
		return null;
	}

	@Override
	public void updateCity(City c) {
		
		
	}

	@Override
	public void removeCity(String id) {
		
		
	}

}
