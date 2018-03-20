package com.tundra.service.admin;

import java.util.List;

import com.tundra.entity.Organization;

public interface AdminOrganizationService {

	/**
	 * Retrieve a list of all organizations 
	 * 
	 * @return
	 */
	List<Organization> findAllOrganizations();

	/**
	 * Retrieve an organization by id
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganization(int id);

}