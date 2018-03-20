package com.tundra.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.Location;

@Transactional("tundraTransactionManager")
public interface ItemTagMediaDAO extends JpaRepository<ItemTagMedia, Integer> {
	
	@Query("SELECT m FROM ItemTagMedia m LEFT JOIN m.itemTag t WHERE t.tag = :tag")
	List<ItemTagMedia> findByItemTag(@Param("tag") String tag);
	
	@Query("SELECT m FROM ItemTagMedia m LEFT JOIN m.itemTag t WHERE t.tag = :tag AND t.location IN :locations")
	List<ItemTagMedia> findByItemTagForLocations(@Param("tag") String tag, @Param("locations") Set<Location> locations);
	
	@Query("SELECT m FROM ItemTagMedia m LEFT JOIN m.itemTag t WHERE m.id = :id AND t.location IN :locations")
	List<ItemTagMedia> findByIdForLocations(@Param("id") Integer id, @Param("locations") Set<Location> locations);
	
}
