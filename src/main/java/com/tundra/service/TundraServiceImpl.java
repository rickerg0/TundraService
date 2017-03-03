package com.tundra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.ExhibitTagDAO;
import com.tundra.dao.ExhibitTagMediaDAO;
import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.ExhibitTag;
import com.tundra.entity.ExhibitTagMedia;
import com.tundra.entity.Organization;
import com.tundra.response.ExhibitTagSummaryResponse;

@Service
public class TundraServiceImpl implements TundraService {
  
	@Autowired
	ExhibitTagDAO exhibitTagDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	@Autowired
	ExhibitTagMediaDAO exhibitTagMediaDAO;
	
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
	public ExhibitTag findByTag(String tag) {
		ExhibitTag et = null;
		List<ExhibitTag> list = exhibitTagDAO.findByTag(tag);
		if( list != null && list.size() ==1){
			et = list.get(0);
		}
		return et;
	}

	@Override
	public List<ExhibitTag> findAllTags() {
		return exhibitTagDAO.findAll();
	}

	@Override
	public ExhibitTagMedia findMediaById(Integer id) {
		ExhibitTagMedia media = exhibitTagMediaDAO.findOne(id);
		return media;
	}

	@Override
	public ExhibitTagSummaryResponse findSummaryByExhibitTag(String tag) {
		ExhibitTagSummaryResponse summary = null;
		List<ExhibitTagSummaryResponse> list = exhibitTagDAO.findSummariesByTag(tag);
		if( list != null && list.size() == 1){
			summary = list.get(0);
		}
		return summary;
	}	
	@Override
	public List<ExhibitTagSummaryResponse> findSummaryList() {
		List<ExhibitTagSummaryResponse> list = new ArrayList<>();
		List<ExhibitTag> etList = exhibitTagDAO.findAll();
		if (etList != null) {
			for (ExhibitTag et: etList) {
				list.add(new ExhibitTagSummaryResponse(et));
			}
		}
		return list;
	}
}
