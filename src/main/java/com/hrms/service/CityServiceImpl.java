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

		city.setCityCode(cityDao.getMAX_Id("CTY"));
		this.cityDao.saveOrUpdate(city);
	}

	@Override
	public List<City> getAllCities() {
		List<City> listCity = cityDao.findAll();
		return listCity;
	}

	@Override
	public City findCityById(String id) {

		return cityDao.findById(id);
	}

	@Override
	public void updateCity(City c) {
		this.cityDao.saveOrUpdate(c);

	}

	@Override
	public void removeCity(String id) {
		this.cityDao.delete(id);

	}

}