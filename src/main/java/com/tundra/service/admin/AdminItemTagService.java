	package com.tundra.service.admin;

import java.util.List;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.response.ItemTagSummaryResponse;

public interface AdminItemTagService {


	/**
	 * Retrieve a media object by id
	 * 
	 * @param id
	 * @return
	 */
	ItemTagMedia findMediaById(Integer id);
	
	/**
	 * Retrieve an item response summary for the tag provided making sure it is in the user's locations
	 * 
	 * @param tag
	 * @return
	 */
	ItemTagSummaryResponse findSummaryByItemTagForUser(String tag);
	
	
	/**
	 * Retrieve a list of all item response summaries in the user's locations
	 * 
	 * @return
	 */
	List<ItemTagSummaryResponse> findSummaryListForUser();

	/**
	 * Saves an ItemTag
	 * 
	 * @param tag
	 */
	void save (ItemTag tag);
}