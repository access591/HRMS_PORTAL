package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.AttendanceRegister;

@Repository
public class AttendanceRegisterDaoImpl extends AbstractGenericDao<AttendanceRegister> implements AttendanceRegisterDao {
}
