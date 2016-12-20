package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ExhibitTagMedia;

@Transactional("tundraTransactionManager")
public interface ExhibitTagMediaDAO extends JpaRepository<ExhibitTagMedia, Integer> {
	
	@Query("SELECT m FROM ExhibitTagMedia m LEFT JOIN m.exhibitTag t WHERE t.tag = :tag")
	List<ExhibitTagMedia> findByExhibitTag(@Param("tag") String tag);
	
}
