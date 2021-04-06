package com.hrms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrms.model.LeaveDetail;

public interface LeaveDetailService {
	
	public void addLeaveDetail(LeaveDetail leaveDetail);

	List<LeaveDetail> getAllLeaveDetails();

	LeaveDetail findLeaveDetailById(String id);

	public void updateLeaveDetail(LeaveDetail c);

	public void removeLeaveDetail(String id);

	public void leaveReportGenratepdf(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<LeaveDetail> dataList);

}
