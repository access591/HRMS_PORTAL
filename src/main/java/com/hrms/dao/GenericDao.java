package com.hrms.dao;


import java.util.List;

public interface GenericDao<E> {
	List<E> findAll();
	void delete( String id );
	E findById( String id );
	void saveOrUpdate(E entity);

	
	
	

}
