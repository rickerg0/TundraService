package com.tundra.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.ItemTagDAO;
import com.tundra.dao.ItemTagMediaDAO;
import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.response.ItemTagSummaryResponse;

@Service
public class AdminItemTagServiceImpl extends AbstractAdminService implements AdminItemTagService {
  
	@Autowired
	ItemTagDAO itemTagDAO;
	
	@Autowired
	ItemTagMediaDAO itemTagMediaDAO;
	
	@Override
	public ItemTagMedia findMediaById(Integer id) {
		
		ItemTagMedia media = null;
		List<ItemTagMedia> list = itemTagMediaDAO.findByIdForLocations(id, getCurrentUser().getLocations());
		
		if( list != null && list.size() == 1){
			media = list.get(0);
		}

		return media;
	}

	@Override
	public void save(ItemTag tag) {
		if (tag != null) {
			itemTagDAO.save(tag);
		}
	}

	@Override
	public ItemTagSummaryResponse findSummaryByItemTagForUser(String tag) {

		ItemTagSummaryResponse summary = null;
		List<ItemTag> list = itemTagDAO.findByTagForLocations(tag, getCurrentUser().getLocations());
		
		if( list != null && list.size() == 1){
			summary = new ItemTagSummaryResponse(list.get(0));
		}
		return summary;
	}

	@Override
	public List<ItemTagSummaryResponse> findSummaryListForUser() {

		List<ItemTagSummaryResponse> list = new ArrayList<>();
		List<ItemTag> etList = itemTagDAO.findAllForLocations(getCurrentUser().getLocations());
		if (etList != null) {
			for (ItemTag et: etList) {
				list.add(new ItemTagSummaryResponse(et));
			}
		}
		return list;
	}
	
}
