package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ExhibitTag;

@Transactional("tundraTransactionManager")
public interface ExhibitTagDAO extends JpaRepository<ExhibitTag, Integer> {
	
	List<ExhibitTag> findByTag(String tag);
	
}
