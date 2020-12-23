package com.hrms.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Leave;
import com.hrms.repository.LeaveDao;
@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveDao leaveDao;

	@Override
	public void addLeave(Leave leave) {
		leave.setIns_date(new Date());
		this.leaveDao.addLeave(leave);
	}

	@Override
	public List<Leave> getAllLeaves() {
		List<Leave> listLeave = leaveDao.getAllLeaves();
		return listLeave;
	}

	@Override
	public Leave findLeaveById(String id) {
		return leaveDao.findLeaveById(id);
	}

	@Override
	public void updateLeave(Leave d) {
		d.setLev_code(d.getLev_code());
		d.setUpd_date(d.getUpd_date());
		d.setActive(d.getActive());
		this.leaveDao.updateLeave(d);

	}

	@Override
	public void removeLeave(String id) {
		this.leaveDao.removeLeave(id);

	}

}
