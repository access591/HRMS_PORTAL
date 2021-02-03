package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Bank;


@Repository
public class BankDaoImpl extends AbstractGenericDao<Bank> implements BankDao {

	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());


	



}
