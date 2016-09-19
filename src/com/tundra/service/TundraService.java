package com.tundra.service;

import java.util.List;

import com.tundra.database.Organization;

public interface TundraService {

	List<Organization> findAllOrganizations();

	Organization findOrganization(int id);

}