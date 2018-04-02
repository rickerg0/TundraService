package com.tundra.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ItemTag;
import com.tundra.entity.Location;

@Transactional("tundraTransactionManager")
public interface ItemTagDAO extends JpaRepository<ItemTag, Integer> {
	
	List<ItemTag> findByTag(String tag);
	List<ItemTag> findByLocation(Location location);
	
	@Query("SELECT t FROM ItemTag t LEFT JOIN t.itemTagMediaSet m WHERE t.tag = :tag AND t.location IN :userLocations")
	List<ItemTag> findByTagForUserLocations(@Param("tag") String tag, @Param("userLocations") Set<Location> userLocations);

	@Query("SELECT t FROM ItemTag t LEFT JOIN t.itemTagMediaSet m WHERE t.id = :id AND t.location IN :userLocations")
	List<ItemTag> findByIdForUserLocations(@Param("id") Integer id, @Param("userLocations") Set<Location> userLocations);

	@Query("SELECT t FROM ItemTag t LEFT JOIN t.itemTagMediaSet m WHERE t.location IN :userLocations")
	List<ItemTag> findAllForUserLocations(@Param("userLocations") Set<Location> userLocations);

	@Query("SELECT t FROM ItemTag t LEFT JOIN t.itemTagMediaSet m WHERE t.location = :location AND t.location IN :userLocations")
	List<ItemTag> findAllForLocationAndUserLocations(@Param("location") Location location, 
														@Param("userLocations") Set<Location> userLocations);

}
