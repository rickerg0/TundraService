package com.tundra.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).READ_TAG)")
	public ItemTagMedia findMediaById(Integer id) {
		
		ItemTagMedia media = null;
		List<ItemTagMedia> list = itemTagMediaDAO.findByIdForUserLocations(id, getCurrentUser().getLocations());
		
		if( list != null && list.size() == 1){
			media = list.get(0);
		}

		return media;
	}

	@Override
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).UPDATE_TAG)")
	public void save(ItemTag tag) {
		if (tag != null) {
			itemTagDAO.save(tag);
		}
	}

	@Override
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).READ_TAG)")
	public ItemTagSummaryResponse findSummaryByItemTagForUser(String tag) {

		ItemTagSummaryResponse summary = null;
		List<ItemTag> list = itemTagDAO.findByTagForUserLocations(tag, getCurrentUser().getLocations());
		
		if( list != null && list.size() == 1){
			summary = new ItemTagSummaryResponse(list.get(0));
		}
		return summary;
	}

	@Override
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).READ_TAG)")
	public List<ItemTagSummaryResponse> findSummaryListForUser() {

		List<ItemTagSummaryResponse> list = new ArrayList<>();
		List<ItemTag> etList = itemTagDAO.findAllForUserLocations(getCurrentUser().getLocations());
		if (etList != null) {
			for (ItemTag et: etList) {
				list.add(new ItemTagSummaryResponse(et));
			}
		}
		return list;
	}

	@Override
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).DELETE_TAG)")
	public void delete(ItemTag tag) {
		// need to make sure the user has permission to the tag being deleted
		List<ItemTag> list = itemTagDAO.findByIdForUserLocations(tag.getId(), getCurrentUser().getLocations());
		if (list == null || list.isEmpty()) {
			throw new SecurityException("Invalid permission for delete.");
		}
		itemTagDAO.delete(tag);
		
	}
	
}
