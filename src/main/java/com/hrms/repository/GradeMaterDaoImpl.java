package com.hrms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrms.dao.AbstractGenericDao;

import com.hrms.model.Grade;

@Repository
public class GradeMaterDaoImpl extends AbstractGenericDao<Grade> implements GradeMaterDao {
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(GradeMaterDaoImpl.class.getName());

	@Override
	public int getMAX_GRADE_CODE() {

		String sql=" select MAX(substr(GRADE_CODE,-4)) as MAX_ID from M_GRADE";
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
