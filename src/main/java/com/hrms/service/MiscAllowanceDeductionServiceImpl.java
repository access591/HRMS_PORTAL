package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Grade;
import com.hrms.model.MiscAllowance;
import com.hrms.repository.MiscAllowanceDeductionDao;

@Service
public class MiscAllowanceDeductionServiceImpl implements MiscAllowanceDeductionService {
	@Autowired
	MiscAllowanceDeductionDao miscAllowanceDeductionDao;

	@Override
	public void addMiscAllowanceDeduction(MiscAllowance miscAllowance) {
		miscAllowance.setIns_date(new Date());
		this.miscAllowanceDeductionDao.saveOrUpdate(miscAllowance);

	}

	@Override
	public List<MiscAllowance> getAllMiscAllowanceDeduction() {

		List<MiscAllowance> listMiscAllowanceDeduction = miscAllowanceDeductionDao.findAll();
		return listMiscAllowanceDeduction;

	}

	@Override
	public MiscAllowance findMiscAllowanceDeductionById(String id) {
		return miscAllowanceDeductionDao.findById(id);

	}

	@Override
	public void updateMiscAllowanceDeduction(MiscAllowance M) {
		M.setDescription(M.getDescription());
		M.setHead(M.getHead());
		M.setType(M.getType());
		M.setAct_Code(M.getAct_Code());
		M.setAccount_Name(M.getAccount_Name());
		M.setSub_Group_Code(M.getSub_Group_Code());
		M.setSub_Group_Name(M.getSub_Group_Name());
		M.setUpd_date(M.getUpd_date());
		M.setActive(M.getActive());
		this.miscAllowanceDeductionDao.saveOrUpdate(M);

	}

	@Override
	public void removeMiscAllowanceDeduction(String id) {
		this.miscAllowanceDeductionDao.delete(id);

	}

}
