package com.hrms.service;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Category;
import com.hrms.repository.CategoryDao;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired CategoryDao categoryDao;
	
	@Override

	public void addCategory(Category category) {
		category.setInsDate(new Date());
	    category.setCategoryCode(categoryDao.getMaxId("CAT"));
	    this.categoryDao.saveOrUpdate(category);

	
		

	}

	@Override
	public List<Category> getAllCategory() {
		
		return this.categoryDao.findAll();
	}

	@Override
	public Category findCategoryByCatId(String categId) {
		
		return this.categoryDao.findById(categId);
	}

	@Override
	public void updateCategory(Category category) {
		this.categoryDao.saveOrUpdate(category);
		
		
	}

	@Override
	public void removeCategory(String categoryId) {
		try {
			this.categoryDao.delete(categoryId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean chaeckCategoryExistOrNot(Category category) {

		Category category1 = this.categoryDao.chaeckCategoryExistOrNot(category);


		if(category1 != null) {
			return true;
		}
		return false;
	}

}
