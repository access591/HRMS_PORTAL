package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Award;
import com.hrms.repository.AwardDao;
@Service
public class AwardServiceImpl  implements AwardService{

	@Autowired
	AwardDao awardDao;
	@Override
	public void addAward(Award award) {
		this.awardDao.saveOrUpdate(award);
		
	}

	@Override
	public List<Award> getAllAwards() {
		
		List<Award> listAward = awardDao.findAll();
		return listAward;
	}

	@Override
	public Award findAwardById(long id) {
	
		return awardDao.findById(id);
	}

	@Override
	public void updateAward(Award c) {
		this.awardDao.saveOrUpdate(c);	
		
	}

	@Override
	public void removeAward(long id) {
		this.awardDao.delete(id);
		
	}

}
