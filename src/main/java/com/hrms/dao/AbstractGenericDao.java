package com.hrms.dao;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractGenericDao<E> implements GenericDao<E> {
	private final Class<E> entityClass;

	public AbstractGenericDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<E> findAll() {

		return getSession().createCriteria(this.entityClass).list();
	}

	@Override
	public E findById(String id) {
		return (E) getSession().get(this.entityClass, id);

	}

	@Override
	public void saveOrUpdate(E entity) {

		Transaction tx = getSession().beginTransaction();
		getSession().saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	public void delete(String id) {
		Transaction tx = getSession().beginTransaction();
		getSession().delete(getSession().load(this.entityClass, new String(id)));
		tx.commit();

	}

	@Override
	public E existOrNot(E obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E findById(long id) {
		return (E) getSession().get(this.entityClass, id);

	}

	@Override
	public void delete(long id) {
		Transaction tx = getSession().beginTransaction();
		getSession().delete(getSession().load(this.entityClass, (id)));
		tx.commit();

	}

}
