package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Country;

@Repository
public class CountryDaoImpl extends AbstractGenericDao<Country> implements CountryDao {

}
