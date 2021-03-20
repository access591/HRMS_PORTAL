package com.hrms.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.City;
/**
 * @author Access surendra
 *
 */
@Repository
public class CityDaoImpl extends AbstractGenericDao<City> implements CityDao {
	
	@Override
	public Page<City> findAll(Pageable pageable) {
		
		return null;
	}

}
