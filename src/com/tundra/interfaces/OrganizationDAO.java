package com.tundra.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.database.Organization;

@Transactional("tundraTransactionManager")
public interface OrganizationDAO extends JpaRepository<Organization,Integer> {

//	@Query("SELECT o FROM Organization o WHERE o.name = :name")
//	List<Organization> findByName(@Param("name") String name);
	
	List<Organization> findByName(String name);
	List<Organization> findByNameAndCity(String name, String city);
}
