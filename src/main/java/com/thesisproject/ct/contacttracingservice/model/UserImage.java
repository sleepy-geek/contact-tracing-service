package com.thesisproject.ct.contacttracingservice.model;

import java.util.UUID;

import com.thesisproject.ct.contacttracingservice.entity.UserImageEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserImage {
	private UUID imageId;
	private byte[] data;
	private String fileName;
	private String type;
	
	public UserImage(UserImageEntity entity) {
		this.imageId = entity.getImageId();
		this.data = entity.getData();
		this.fileName = entity.getFileName();
		this.type = entity.getType();
	}
}
