	package com.tundra.service;

import java.util.List;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.User;
import com.tundra.response.ItemTagSummaryResponse;

public interface ItemTagService {


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

	/**
	 * Saves an ItemTag
	 * 
	 * @param tag
	 */
	void save (ItemTag tag);
}