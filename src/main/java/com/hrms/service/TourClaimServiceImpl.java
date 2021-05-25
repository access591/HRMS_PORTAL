package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TourClaim;
import com.hrms.repository.TourClaimDao;

@Service
public class TourClaimServiceImpl  implements TourClaimService{
@Autowired
TourClaimDao tourClaimDao;
	@Override
	public void AddTourClaim(TourClaim tourClaim) {
		tourClaim.setTourClaimId(tourClaimDao.getMaxId("TCI"));
		this.tourClaimDao.saveOrUpdate(tourClaim);
		
	}

}
