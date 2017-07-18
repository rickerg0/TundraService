package com.tundra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.ItemTagDAO;
import com.tundra.dao.ItemTagMediaDAO;
import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.Organization;
import com.tundra.response.ItemTagSummaryResponse;

@Service
public class TundraServiceImpl implements TundraService {
  
	@Autowired
	ItemTagDAO itemTagDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	@Autowired
	ItemTagMediaDAO itemTagMediaDAO;
	
	/* (non-Javadoc)
	 * @see com.tundra.service.TundraService#findAllOrganizations()
	 */
	@Override
	public List<Organization> findAllOrganizations() {
		return organizationDAO.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.tundra.service.TundraService#findOrganization(int)
	 */
	@Override
	public Organization findOrganization(int id) {
		return organizationDAO.findOne(id);
	}
	
	@Override
	public List<Organization> findByName(String name) {
		return organizationDAO.findByName(name);
	}
	
	@Override
	public List<Organization> findByNameAndCity(String name, String city) {
		return organizationDAO.findByNameAndCity(name, city);
	}
	
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
		ItemTagMedia media = itemTagMediaDAO.findOne(id);
		return media;
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
}
