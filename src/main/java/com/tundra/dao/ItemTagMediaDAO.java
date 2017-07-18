package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ItemTagMedia;

@Transactional("tundraTransactionManager")
public interface ItemTagMediaDAO extends JpaRepository<ItemTagMedia, Integer> {
	
	@Query("SELECT m FROM ItemTagMedia m LEFT JOIN m.itemTag t WHERE t.tag = :tag")
	List<ItemTagMedia> findByItemTag(@Param("tag") String tag);
	
}
