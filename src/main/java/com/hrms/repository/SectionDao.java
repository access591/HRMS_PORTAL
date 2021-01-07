package com.hrms.repository;

import java.util.List;
import com.hrms.model.Section;

public interface SectionDao
{
	List<Section> getAllSections();

	void addSection(Section section);

	Section findSectionById(String id);

	public void updateSection(Section d);

	public void removeSection(String id);

	Section checkSectionExists(Section section);
}
