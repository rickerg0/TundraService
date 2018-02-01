package com.tundra.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gaudin
 * 
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "firstname")
	private String firstName;

	@Basic(optional = false)
	@Column(name = "lastname")
	private String lastName;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@Column(name = "user_name")
	private String userName;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_Id", referencedColumnName = "id")
	private Organization organization;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
