	package com.tundra.service;

import java.util.List;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.Organization;
import com.tundra.response.ItemTagSummaryResponse;

public interface TundraService {

	List<Organization> findAllOrganizations();

	Organization findOrganization(int id);

	List<Organization> findByName(String name);

	List<Organization> findByNameAndCity(String name,String city);

	ItemTag findByTag(String tag);

	ItemTagMedia findMediaById(Integer id);
	
	List<ItemTag> findAllTags();	
	
	ItemTagSummaryResponse findSummaryByItemTag(String tag);
	
	List<ItemTagSummaryResponse> findSummaryList();
}