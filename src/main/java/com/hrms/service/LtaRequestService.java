package com.hrms.service;

import java.util.List;


import com.hrms.model.LtaRequest;

public interface LtaRequestService {

	void addLtaRequest(LtaRequest ltaRequest);
	List<LtaRequest>getAllLTARequest();
}
