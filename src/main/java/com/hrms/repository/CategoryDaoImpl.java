package com.hrms.repository;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Category;




@Repository
public class CategoryDaoImpl extends AbstractGenericDao<Category>  implements CategoryDao {

	@Override
	public Category chaeckCategoryExistOrNot(Category category) {
		Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class.getName());
		Category desName = null;

		try {

			Criteria criteria = getSession().createCriteria(Category.class);
			desName = (Category) criteria.setFetchMode("CATEG_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("categoryName", category.getCategoryName())).uniqueResult();

		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.checkDepartmentExists" + e.getMessage());
		}

		return desName;
	}

	}






