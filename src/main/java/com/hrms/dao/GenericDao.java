package com.hrms.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericDao<E> {
	List<E> findAll();
	Page<E> findAll(Pageable pageable);
	void delete(String id);

	E findById(String id);

	E findById(long id);

	void saveOrUpdate(E entity);

	E existOrNot(E obj);

	void delete(long id);
	String getMAX_Id(String Cid);

}
