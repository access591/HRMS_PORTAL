package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.repository.TrackallEnquiriesDao;

@Service
public class TrackallEnquiriesServiceImpl implements TrackallEnquiriesService {
	@Autowired TrackallEnquiriesDao trackallEnquiriesDao ;
}
