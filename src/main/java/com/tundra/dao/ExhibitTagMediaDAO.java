package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ExhibitTagMedia;
import com.tundra.response.ExhibitTagSummaryResponse;

@Transactional("tundraTransactionManager")
public interface ExhibitTagMediaDAO extends JpaRepository<ExhibitTagMedia, Integer> {
	
	@Query("SELECT m FROM ExhibitTagMedia m LEFT JOIN m.exhibitTag t WHERE t.tag = :tag")
	List<ExhibitTagMedia> findByExhibitTag(@Param("tag") String tag);
	
	@Query("SELECT NEW com.tundra.response.ExhibitTagSummaryResponse(o.name,l.name,t.name,t.id,t.tag,m.mimeType) FROM ExhibitTagMedia m "
			+ "LEFT JOIN m.exhibitTag t "
			+ "LEFT JOIN t.location l "
			+ "LEFT JOIN l.organization o "
			+ "WHERE t.tag = :tag")
	List<ExhibitTagSummaryResponse> findSummaryByExhibitTag(@Param("tag") String tag);
	
}
