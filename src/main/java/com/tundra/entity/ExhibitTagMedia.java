package com.tundra.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tundra.jackson.ExhibitMediaSerializer;

/**
 *
 * @author rickerg0
 */
@JsonSerialize(using = ExhibitMediaSerializer.class)
@Entity
@Table(name = "exibittagmedia")	
public class ExhibitTagMedia extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "MimeType")
    private String mimeType;
    @Basic(optional = false)
    @Column(name = "Content",columnDefinition="LONGBLOB")
    private byte[] content;

    @JsonIgnore
    @JoinColumn(name = "Exibit_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ExhibitTag exhibitTag;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ExhibitTag getExhibitTag() {
		return exhibitTag;
	}

	public void setExhibitTag(ExhibitTag exhibitTag) {
		this.exhibitTag = exhibitTag;
	}
}
