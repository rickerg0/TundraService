package com.tundra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.ItemTagDAO;
import com.tundra.dao.ItemTagMediaDAO;
import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.User;
import com.tundra.response.ItemTagSummaryResponse;

@Service
public class ItemTagServiceImpl implements ItemTagService {
  
	@Autowired
	ItemTagDAO itemTagDAO;
	
	@Autowired
	ItemTagMediaDAO itemTagMediaDAO;
	
	@Override
	public ItemTag findByTag(String tag) {
		
		ItemTag et = null;
		List<ItemTag> list = itemTagDAO.findByTag(tag);
		
		if( list != null && list.size() ==1){
			et = list.get(0);
		}
		return et;
	}

	@Override
	public List<ItemTag> findAllTags() {
		return itemTagDAO.findAll();
	}

	@Override
	public ItemTagMedia findMediaById(Integer id) {
		// sorry guys... hibernate bought into the optional paradigm
		return itemTagMediaDAO.findById(id).get();
	}

	@Override
	public ItemTagSummaryResponse findSummaryByItemTag(String tag) {
		
		ItemTagSummaryResponse summary = null;
		List<ItemTag> list = itemTagDAO.findByTag(tag);
		
		if( list != null && list.size() == 1){
			summary = new ItemTagSummaryResponse(list.get(0));
		}
		return summary;
	}
	
	@Override
	public List<ItemTagSummaryResponse> findSummaryList() {
		
		List<ItemTagSummaryResponse> list = new ArrayList<>();
		List<ItemTag> etList = itemTagDAO.findAll();
		
		if (etList != null) {
			for (ItemTag et: etList) {
				list.add(new ItemTagSummaryResponse(et));
			}
		}
		return list;
	}

	@Override
	public void save(ItemTag tag, User user) {
		
		if (tag != null) {
			
			tag.setAuditUser(user.getUserName());
			itemTagDAO.save(tag);
		}
		
	}
}
