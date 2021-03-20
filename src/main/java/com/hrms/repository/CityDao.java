package com.hrms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrms.dao.GenericDao;
import com.hrms.model.City;

public interface CityDao extends GenericDao<City>{

	Page<City> findAll(Pageable pageable,List<City> sizeOflist);

}
