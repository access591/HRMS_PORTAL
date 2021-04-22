package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Category;


@Repository
public class CategoryDaoImpl extends AbstractGenericDao<Category>  implements CategoryDao {

}
