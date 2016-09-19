package com.tundra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.database.Organization;
import com.tundra.interfaces.OrganizationDAO;

@Service
public class TundraServiceImpl implements TundraService {
  
//	@Autowired
//	LocationDAO locationDAO;
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
	
}
