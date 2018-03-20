	package com.tundra.service;

import java.util.List;

import com.tundra.entity.ItemTagMedia;
import com.tundra.response.ItemTagSummaryResponse;

public interface ItemTagService {


	/**
	 * Retrieve a media object by id
	 * 
	 * @param id
	 * @return
	 */
	ItemTagMedia findMediaById(Integer id);
	
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
	 * @return
	 */
	List<ItemTagSummaryResponse> findSummaryList();

}