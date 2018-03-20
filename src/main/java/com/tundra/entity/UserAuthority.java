package com.tundra.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "users_authority")
public class UserAuthority extends AbstractEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "authority")
	private String authority;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_Id", referencedColumnName = "id")
	private User user;

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
