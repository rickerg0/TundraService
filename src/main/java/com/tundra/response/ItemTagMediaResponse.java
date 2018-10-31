package com.tundra.response;

import java.io.Serializable;

/**
 *
 * @author gaudin
 */
public class ItemTagMediaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String mimeType;
    private byte[] content;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

}
