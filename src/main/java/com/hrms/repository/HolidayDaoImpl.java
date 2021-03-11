package com.hrms.repository;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Holiday;
@Repository
public class HolidayDaoImpl  extends AbstractGenericDao<Holiday>implements HolidayDao {


}
