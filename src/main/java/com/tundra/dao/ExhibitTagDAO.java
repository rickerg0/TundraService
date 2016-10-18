package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tundra.entity.ExhibitTag;

public interface ExhibitTagDAO extends JpaRepository<ExhibitTag, Integer> {
	
	List<ExhibitTag> findByTag(String tag);
	
}
