package com.tundra.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tundra.jackson.ItemMediaSerializer;

/**
 *
 * @author rickerg0
 */
@JsonSerialize(using = ItemMediaSerializer.class)
@Entity
@Table(name = "itemtagmedia")	
public class ItemTagMedia extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "mimetype")
    private String mimeType;
    @Basic(optional = false)
    @Column(name = "content",columnDefinition="LONGBLOB")
    private byte[] content;

    @JsonBackReference
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ItemTag itemTag;

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

    public ItemTag getItemTag() {
		return itemTag;
	}

	public void setItemTag(ItemTag itemTag) {
		this.itemTag = itemTag;
	}
}
