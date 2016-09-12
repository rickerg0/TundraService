package com.tundra.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.database.Organization;

@Transactional("tundraTansactionManager")
public interface OrganizationDAO extends JpaRepository<Organization,Integer> {

}
