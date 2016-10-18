package com.tundra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.ExhibitTagDAO;
import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.ExhibitTag;
import com.tundra.entity.Organization;

@Service
public class TundraServiceImpl implements TundraService {
  
	@Autowired
	ExhibitTagDAO exhibitTagDAO;
	@Autowired
	private OrganizationDAO organizationDAO;
	
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
	public List<ExhibitTag> findByTag(String tag) {
		return exhibitTagDAO.findByTag(tag);
	}
}
