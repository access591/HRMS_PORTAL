package com.hrms.dao;


import java.util.List;

public interface GenericDao<E> {
	List<E> findAll();

	void delete(String id);

	E findById(String id);

	E findById(long id);

	void saveOrUpdate(E entity);

	E existOrNot(E obj);

	void delete(long id);
	

}
