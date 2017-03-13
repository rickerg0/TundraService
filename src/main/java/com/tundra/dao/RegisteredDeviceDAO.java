package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.RegisteredDevice;

@Transactional("tundraTransactionManager")
public interface RegisteredDeviceDAO extends JpaRepository<RegisteredDevice,Integer> {

	
	List<RegisteredDevice> findByEmail(String email);
	List<RegisteredDevice> findByDeviceId(String deviceId);
}
