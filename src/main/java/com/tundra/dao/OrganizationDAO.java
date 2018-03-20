package com.tundra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.Organization;

@Transactional("tundraTransactionManager")
public interface OrganizationDAO extends JpaRepository<Organization,Integer> {

}
