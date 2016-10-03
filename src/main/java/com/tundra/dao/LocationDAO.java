package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.Location;
import com.tundra.entity.Organization;

@Transactional("tundraTansactionManager")
public interface LocationDAO extends JpaRepository<Location,Integer> {

	@Query("select l from Location l where l.organization = :organization")
	public List<Location> findByOrganization(Organization organization);
}
