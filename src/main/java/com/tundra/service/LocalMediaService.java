package com.tundra.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;

public class LocalMediaService implements MediaService {

	@Value("${local.media.service.path}") private String path;
	
	@Override
	public byte[] getMedia(Integer itemTagId, Integer mediaId) throws Exception {
		
		FileInputStream fs = null;

		try {
		
			File file = new File(getFQP(itemTagId, mediaId));
			fs = new FileInputStream(file);
			
			byte content[] = new byte[(int)file.length()];
			fs.read(content);
			
			return content;
			
		} finally {
			fs.close();
		}
	}

	@Override
	public void saveMedia(Integer itemTagId, Integer itemTagMediaId, byte[] content) throws Exception {
		
		FileOutputStream fs = null;
		
		try {
			
			fs = new FileOutputStream(getFQP(itemTagId, itemTagMediaId));
			fs.write(content);
			
		} finally {
			fs.close();
		}
	}
	
	private String getFQP(Integer itemTagId, Integer mediaId) {
		return path + "/" + itemTagId + "/" + mediaId;
	}

}
