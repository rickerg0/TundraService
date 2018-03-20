package com.tundra.service.admin;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tundra.entity.User;

public abstract class AbstractAdminService {

	User getCurrentUser() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
