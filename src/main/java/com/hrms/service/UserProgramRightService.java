package com.hrms.service;
import java.util.List;

import com.hrms.model.UserRights;
public interface UserProgramRightService {
	
	List<UserRights> getAllUserRights();

	public void addUserProgramRight(UserRights userRights);

	boolean checkUserRightsExists(UserRights userRights);

	public void removeUserProgramRight(String id);
}
