package com.tundra.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@Column(name = "password")
	private String password;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_Id", referencedColumnName = "id")
	private Organization organization;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserAuthority> userAuthorities;

    @ManyToMany
    @JoinTable(
        name="users_location",
        joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="location_id", referencedColumnName="id"))
    private Set<Location> locations;
    
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

}
