	package com.tundra.service;

import java.util.List;

import com.tundra.entity.Organization;

public interface TundraService {

	List<Organization> findAllOrganizations();

	Organization findOrganization(int id);

	List<Organization> findByName(String name);

	List<Organization> findByNameAndCity(String name,String city);

}