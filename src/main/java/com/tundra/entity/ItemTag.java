package com.tundra.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author rickerg0
 */
@Entity
@Table(name = "itemtag")

public class ItemTag extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "tag")
    private String tag;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;

    @JsonIgnore
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Location location;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemTag", fetch = FetchType.EAGER)
    private Set<ItemTagMedia> itemTagMediaSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

    public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<ItemTagMedia> getItemTagMediaSet() {
		return itemTagMediaSet;
	}

	public void setItemTagMediaSet(Set<ItemTagMedia> itemTagMediaSet) {
		this.itemTagMediaSet = itemTagMediaSet;
	}
}
