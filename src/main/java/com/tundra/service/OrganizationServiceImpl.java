package com.tundra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDAO organizationDAO;
	
	/* (non-Javadoc)
	 * @see com.tundra.service.OrganizationService#findAllOrganizations()
	 */
	@Override
	public List<Organization> findAllOrganizations() {
		return organizationDAO.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.tundra.service.OrganizationService#findOrganization(int)
	 */
	@Override
	public Organization findOrganization(int id) {
		return organizationDAO.findOne(id);
	}
	
	/* (non-Javadoc)
	 * @see com.tundra.service.OrganizationService#findByName(java.lang.String)
	 */
	@Override
	public List<Organization> findByName(String name) {
		return organizationDAO.findByName(name);
	}
	
	/* (non-Javadoc)
	 * @see com.tundra.service.OrganizationService#findByNameAndCity(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Organization> findByNameAndCity(String name, String city) {
		return organizationDAO.findByNameAndCity(name, city);
	}
}
