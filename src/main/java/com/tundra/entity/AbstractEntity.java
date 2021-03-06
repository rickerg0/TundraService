package com.tundra.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public class AbstractEntity {
	
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @Basic(optional = false)
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Basic(optional = false)
    @Column(name = "updated_user")
    private String updatedUser;

    @Basic(optional = false)
    @Column(name = "created_user")
    private String createdUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
	@PrePersist
    public void onPrePersist() {
		
		String auditUser = SecurityContextHolder.getContext().getAuthentication().getName();

		checkAuditInfo(auditUser);
		
		setCreated(new Date());
		setCreatedUser(auditUser);
		setUpdated(new Date());
		setUpdatedUser(auditUser);
    }
      
    @PreUpdate
    public void onPreUpdate() {
    	
		String auditUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
    	checkAuditInfo(auditUser);
    	
		setUpdated(new Date());
		setUpdatedUser(auditUser);
    }

    private void checkAuditInfo(String auditUser) {
		if (StringUtils.isBlank(auditUser)) {
			throw new SecurityException("User must be in scope to save");
		}
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
    	// return false if the other one is null
        if (object == null) {
            return false;
        }
        
    	// return false if the other one is not the same class
        if (!(object.getClass().equals(getClass()))) {
            return false;
        }
        
        AbstractEntity other = (AbstractEntity) object;
        if (this.id == null && other.id == null) {
        	return super.equals(other);
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ id=" + id + " ]";
    }    
}
