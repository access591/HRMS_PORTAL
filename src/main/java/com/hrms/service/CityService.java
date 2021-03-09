package com.hrms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hrms.model.City;

public interface CityService {
	public void addCity(City city);
	List<City>getAllCities();
	City findCityById(String id);
	public void updateCity(City c);
	public void removeCity(String id);
	public Page<City> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

}
