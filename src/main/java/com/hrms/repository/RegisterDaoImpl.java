package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Register;

@Repository
public class RegisterDaoImpl extends AbstractGenericDao<Register> implements RegisterDao {

}
