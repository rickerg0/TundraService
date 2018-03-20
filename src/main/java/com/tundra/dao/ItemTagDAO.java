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
	
	@Query("SELECT t FROM ItemTag t JOIN t.itemTagMediaSet m WHERE t.tag = :tag AND t.location IN :locations")
	List<ItemTag> findByTagForLocations(@Param("tag") String tag, @Param("locations") Set<Location> locations);

	@Query("SELECT t FROM ItemTag t JOIN t.itemTagMediaSet m WHERE t.location IN :locations")
	List<ItemTag> findAllForLocations(@Param("locations") Set<Location> locations);
}
