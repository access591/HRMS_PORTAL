package com.hrms.service;

import java.util.List;

import com.hrms.model.EmployeeTransfer;

public interface EmployeeTransferService {

	List<EmployeeTransfer> getAllEmployeeTransfer();

	boolean addEmployeeTransfer(EmployeeTransfer employeeTrns);

}