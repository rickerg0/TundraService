package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.ItemTag;

@Transactional("tundraTransactionManager")
public interface ItemTagDAO extends JpaRepository<ItemTag, Integer> {
	
	List<ItemTag> findByTag(String tag);
	
}
