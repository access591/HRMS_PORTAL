package com.hrms.service;

import java.util.List;

import com.hrms.model.TrackallEnquiries;

public interface TrackallEnquiriesService {

	void addTrackallEnquiries(TrackallEnquiries trackallEnquiries);

	TrackallEnquiries findByIdTrackallEnq(long id);

	List<TrackallEnquiries> getAllTrackallEnquiries();

	void removeTrackallEnquiries(Long id);

	void updateTrackallEnquiries(TrackallEnquiries trackallEnquiries);

}
