package com.tundra.response;

public class ExhibitTagSummaryResponse {

	private String organizationName;
	private String locationName;
	private String exhibitTagName;
    private Integer exhibitTagId;
    private String exhibitTagTag;
    private String exhibitTagMimeType;
    
    
	public ExhibitTagSummaryResponse(String organizationName, String locationName, String exhibitTagName,
			Integer exhibitTagId, String exhibitTagTag, String exhibitTagMimeType) {
		super();
		this.organizationName = organizationName;
		this.locationName = locationName;
		this.exhibitTagName = exhibitTagName;
		this.exhibitTagId = exhibitTagId;
		this.exhibitTagTag = exhibitTagTag;
		this.exhibitTagMimeType = exhibitTagMimeType;
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
	public String getExhibitTagMimeType() {
		return exhibitTagMimeType;
	}
	public void setExhibitTagMimeType(String exhibitTagMimeType) {
		this.exhibitTagMimeType = exhibitTagMimeType;
	}
    
}
