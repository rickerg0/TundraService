package com.tundra.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author rickerg0
 */
@Entity
@Table(name = "location")
public class Location extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
	
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.EAGER)
//    private Set<ItemTag> itemTagSet;
    
    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_Id", referencedColumnName = "id")
    private Organization organization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

//    public Set<ItemTag> getItemTagSet() {
//		return itemTagSet;
//	}
//
//	public void setItemTagSet(Set<ItemTag> itemTagSet) {
//		this.itemTagSet = itemTagSet;
//	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
