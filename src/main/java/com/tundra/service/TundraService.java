	package com.tundra.service;

import java.util.List;

import com.tundra.entity.ExhibitTag;
import com.tundra.entity.ExhibitTagMedia;
import com.tundra.entity.Organization;
import com.tundra.response.ExhibitTagSummaryResponse;

public interface TundraService {

	List<Organization> findAllOrganizations();

	Organization findOrganization(int id);

	List<Organization> findByName(String name);

	List<Organization> findByNameAndCity(String name,String city);

	ExhibitTag findByTag(String tag);

	ExhibitTagMedia findMediaById(Integer id);
	
	List<ExhibitTag> findAllTags();	
	
	ExhibitTagSummaryResponse findSummaryByExhibitTag(String tag);
	
	List<ExhibitTagSummaryResponse> findSummaryList();
}