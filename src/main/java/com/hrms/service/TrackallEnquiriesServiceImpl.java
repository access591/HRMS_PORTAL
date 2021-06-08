package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TrackallEnquiries;
import com.hrms.repository.TrackallEnquiriesDao;

@Service
public class TrackallEnquiriesServiceImpl implements TrackallEnquiriesService {
	@Autowired TrackallEnquiriesDao trackallEnquiriesDao ;

	@Override
	public void addTrackallEnquiries(TrackallEnquiries trackallEnquiries) {
	
		this.trackallEnquiriesDao.saveOrUpdate(trackallEnquiries);
		
	}

	@Override
	public TrackallEnquiries findByIdTrackallEnq(long id) {
		
		return this.trackallEnquiriesDao.findById(id);
	}

	@Override
	public List<TrackallEnquiries> getAllTrackallEnquiries() {
		
		return this.trackallEnquiriesDao.findAll();
	}

	@Override
	public void removeTrackallEnquiries(Long id) {
		this.trackallEnquiriesDao.delete(id);	
		
	}

	@Override
	public void updateTrackallEnquiries(TrackallEnquiries trackallEnquiries) {
		this.trackallEnquiriesDao.saveOrUpdate(trackallEnquiries);
		
	}
}
