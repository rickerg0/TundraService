package com.tundra.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.Organization;

@Service
public class AdminOrganizationServiceImpl implements AdminOrganizationService {

	@Autowired
	private OrganizationDAO organizationDAO;
	
	/* (non-Javadoc)
	 * @see com.tundra.service.OrganizationService#findAllOrganizations()
	 */
	@Override
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).READ_ORGANIZATION)")
	public List<Organization> findAllOrganizations() {
		return organizationDAO.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.tundra.service.OrganizationService#findOrganization(int)
	 */
	@Override
	@PreAuthorize("@adminSecurityManager.hasAuthority(T(com.tundra.security.Authority).READ_ORGANIZATION)")
	public Organization findOrganization(int id) {
		return organizationDAO.findById(id).get();
	}
	
}
