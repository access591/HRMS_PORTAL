package com.hrms.repository;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Department;


@Repository
public class DepartmentDaoImpl extends AbstractGenericDao<Department> implements DepartmentDao {
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());

	@Override
	public int getMAX_DEPARTMENT_CODE() {
		
			String sql=" select MAX(substr(DEPARTMENT_CODE,-4)) as MAX_ID from M_DEPARTMENT";
				return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
				public Integer extractData(ResultSet rs)  {
				int reMaxId=0;
					try {
						if (rs.next())
						{
						reMaxId=rs.getInt("MAX_ID");
						}
						if(reMaxId==0)
						{
							reMaxId=1;
						}
						else
						{
							reMaxId+=1;
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
						return reMaxId;
				}
			});
	}
	
}

