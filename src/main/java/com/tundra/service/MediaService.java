package com.tundra.service;

public interface MediaService {

	byte[] getMedia(Integer itemTagId, Integer mediaId) throws Exception;
	void saveMedia(Integer itemTagId, Integer itemTagMediaId, byte[] content) throws Exception;
}
