package com.tundra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.entity.User;

@Transactional("tundraTransactionManager")
public interface UserDAO extends JpaRepository<User,Integer> {

	
	List<User> findByUserNameAndPassword(String userName, String password);

	List<User> findByFirstNameAndLastNameAndEmailAndUserName(String firstName, String lastName, String email, String userName);
	
}
