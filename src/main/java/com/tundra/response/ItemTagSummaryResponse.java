package com.tundra.response;

import java.util.ArrayList;
import java.util.List;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;

public class ItemTagSummaryResponse {

	private String organizationName;
	private String locationName;
	private String itemTagName;
    private Integer itemTagId;
    private String itemTagTag;
    private List<ItemTagMediaSummary> mediaSet = new ArrayList<>();
    
	public ItemTagSummaryResponse(ItemTag tag) {
		super();
		this.organizationName = tag.getLocation().getOrganization().getName();
		this.locationName = tag.getLocation().getName();
		this.itemTagName = tag.getName();
		this.itemTagId = tag.getId();
		this.itemTagTag = tag.getTag();
		if (tag.getItemTagMediaSet() != null) {
			for (ItemTagMedia m: tag.getItemTagMediaSet()) {
				mediaSet.add(new ItemTagMediaSummary(m));
			}
		}
	}
	
	public String getItemTagTag() {
		return itemTagTag;
	}

	public void setItemTagTag(String itemTagTag) {
		this.itemTagTag = itemTagTag;
	}

	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getItemTagName() {
		return itemTagName;
	}
	public void setItemTagName(String itemTagName) {
		this.itemTagName = itemTagName;
	}
	public Integer getItemTagId() {
		return itemTagId;
	}
	public void setItemTagId(Integer itemTagId) {
		this.itemTagId = itemTagId;
	}
	
	public List<ItemTagMediaSummary> getMediaSet() {
		return mediaSet;
	}

	public void setMediaSet(List<ItemTagMediaSummary> mediaSet) {
		this.mediaSet = mediaSet;
	}

	public class ItemTagMediaSummary {
		
		private String itemTagMimeType;
	    private Integer itemTagMediaId;

	    public ItemTagMediaSummary(ItemTagMedia media) {
			itemTagMimeType = media.getMimeType();
			itemTagMediaId = media.getId();
		}

		public String getItemTagMimeType() {
			return itemTagMimeType;
		}

		public void setItemTagMimeType(String itemTagMimeType) {
			this.itemTagMimeType = itemTagMimeType;
		}

		public Integer getItemTagMediaId() {
			return itemTagMediaId;
		}

		public void setItemTagMediaId(Integer itemTagMediaId) {
			this.itemTagMediaId = itemTagMediaId;
		}
	}
}
