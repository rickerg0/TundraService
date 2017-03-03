package com.tundra.response;

import java.util.ArrayList;
import java.util.List;

import com.tundra.entity.ExhibitTag;
import com.tundra.entity.ExhibitTagMedia;

public class ExhibitTagSummaryResponse {

	private String organizationName;
	private String locationName;
	private String exhibitTagName;
    private Integer exhibitTagId;
    private String exhibitTagTag;
    private List<ExhibitTagMediaSummary> mediaSet = new ArrayList<>();
    
	public ExhibitTagSummaryResponse(ExhibitTag tag) {
		super();
		this.organizationName = tag.getLocation().getOrganization().getName();
		this.locationName = tag.getLocation().getName();
		this.exhibitTagName = tag.getName();
		this.exhibitTagId = tag.getId();
		this.exhibitTagTag = tag.getTag();
		if (tag.getExhibitTagMediaSet() != null) {
			for (ExhibitTagMedia m: tag.getExhibitTagMediaSet()) {
				mediaSet.add(new ExhibitTagMediaSummary(m));
			}
		}
	}
	
	public String getExhibitTagTag() {
		return exhibitTagTag;
	}

	public void setExhibitTagTag(String exhibitTagTag) {
		this.exhibitTagTag = exhibitTagTag;
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
	public String getExhibitTagName() {
		return exhibitTagName;
	}
	public void setExhibitTagName(String exhibitTagName) {
		this.exhibitTagName = exhibitTagName;
	}
	public Integer getExhibitTagId() {
		return exhibitTagId;
	}
	public void setExhibitTagId(Integer exhibitTagId) {
		this.exhibitTagId = exhibitTagId;
	}
	
	public List<ExhibitTagMediaSummary> getMediaSet() {
		return mediaSet;
	}

	public void setMediaSet(List<ExhibitTagMediaSummary> mediaSet) {
		this.mediaSet = mediaSet;
	}

	public class ExhibitTagMediaSummary {
		
		private String exhibitTagMimeType;
	    private Integer exhibitTagMediaId;

	    public ExhibitTagMediaSummary(ExhibitTagMedia media) {
			exhibitTagMimeType = media.getMimeType();
			exhibitTagMediaId = media.getId();
		}

		public String getExhibitTagMimeType() {
			return exhibitTagMimeType;
		}

		public void setExhibitTagMimeType(String exhibitTagMimeType) {
			this.exhibitTagMimeType = exhibitTagMimeType;
		}

		public Integer getExhibitTagMediaId() {
			return exhibitTagMediaId;
		}

		public void setExhibitTagMediaId(Integer exhibitTagMediaId) {
			this.exhibitTagMediaId = exhibitTagMediaId;
		}
	}
}
