package com.tundra.service;

import java.util.List;

import com.tundra.entity.Organization;

public interface OrganizationService {

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

	/**
	 * Retrieve a list of organizations by name
	 * 
	 * @param name
	 * @return
	 */
	List<Organization> findByName(String name);

	/**
	 * Retrieve a list of organizations by name and city
	 * 
	 * @param name
	 * @param city
	 * @return
	 */
	List<Organization> findByNameAndCity(String name,String city);
}