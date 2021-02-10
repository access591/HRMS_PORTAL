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
	public String getMAX_DEPARTMENT_CODE() {
			String sql="select FN_PRGM_CODE('DEP') dep";
				return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
					String dep;
				public String extractData(ResultSet rs)  {
					try {
						if (rs.next())
						{
							dep=rs.getString("dep");
						}
					
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				
						return dep; 
						
				}
			});
	}
	
}

