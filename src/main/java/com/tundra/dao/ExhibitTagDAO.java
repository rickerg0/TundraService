package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ExhibitTag;
import com.tundra.response.ExhibitTagSummaryResponse;

@Transactional("tundraTransactionManager")
public interface ExhibitTagDAO extends JpaRepository<ExhibitTag, Integer> {
	
	public static final String BASE_SQL = "SELECT NEW com.tundra.response.ExhibitTagSummaryResponse(t) FROM ExhibitTag t "
			+ "LEFT JOIN t.exhibitTagMediaSet m "
			+ "LEFT JOIN t.location l "
			+ "LEFT JOIN l.organization o ";

	List<ExhibitTag> findByTag(String tag);
	
	@Query(BASE_SQL + "WHERE t.tag = :tag")
	List<ExhibitTagSummaryResponse> findSummariesByTag(@Param("tag") String tag);	
	
}
