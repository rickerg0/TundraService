	package com.tundra.service;

import java.util.List;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.Organization;
import com.tundra.response.ItemTagSummaryResponse;

public interface TundraService {

	/**
	 * Retrieve a list of all organizations 
	 * 
	 * @return
	 */
	List<Organization> findAllOrganizations();

	/**
	 * Retrieve an organization by id
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganization(int id);

	/**
	 * Retrieve a list of organizations by name
	 * 
	 * @param name
	 * @return
	 */
	List<Organization> findByName(String name);

	/**
	 * Retrieve a list of organizations by name and city
	 * 
	 * @param name
	 * @param city
	 * @return
	 */
	List<Organization> findByNameAndCity(String name,String city);

	/**
	 * Retrieve a tag by... well... tag
	 * 
	 * @param tag
	 * @return
	 */
	ItemTag findByTag(String tag);

	/**
	 * Retrieve a media object by id
	 * 
	 * @param id
	 * @return
	 */
	ItemTagMedia findMediaById(Integer id);
	
	/**
	 * Retrieve all tags
	 * 
	 * @return
	 */
	List<ItemTag> findAllTags();	
	
	/**
	 * Retrieve an item response summary for the tag provided
	 * 
	 * @param tag
	 * @return
	 */
	ItemTagSummaryResponse findSummaryByItemTag(String tag);
	
	
	/**
	 * Retrieve a list of all item response summaries
	 * 
	 * @param tag
	 * @return
	 */
	List<ItemTagSummaryResponse> findSummaryList();
}