package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.hrms.model.City;
import com.hrms.repository.CityDao;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityDao cityDao;
	

	@Override
	public void addCity(City city) {

		city.setCityCode(cityDao.getMaxId("CTY"));
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

	@Override
	public Page<City> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,List<City> listCity) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.cityDao.findAll(pageable,listCity);
	}

}
