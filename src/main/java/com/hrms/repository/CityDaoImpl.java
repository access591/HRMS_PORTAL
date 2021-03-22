package com.hrms.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
	public Page<City> findAll(Pageable pageable,List<City> listCity) {
		 int startOfPage = pageable.getPageNumber() * pageable.getPageSize();
		    if (startOfPage > listCity.size()) {
		        return new PageImpl<>(listCity, pageable, 0);
		    }
		final int end = Math.min((startOfPage + pageable.getPageSize()), listCity.size());
		// pageable = PageRequest.of(0, 2);

		pageable = PageRequest.of(1,5);
		return new PageImpl<>(listCity.subList(startOfPage, end), pageable, listCity.size());
	}

}
