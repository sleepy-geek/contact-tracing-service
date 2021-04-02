package com.thesisproject.ct.contacttracingservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.thesisproject.ct.contacttracingservice.model.UserImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "public", name="tbl_user_image")
@Data
@NoArgsConstructor
public class UserImageEntity {
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
	@Column(name = "image_id")
	private UUID imageId;
	
	@Column(name = "user_profile_id", nullable = false)
	private UUID userProfileId;
	
	@Column(name = "data", nullable = false)
	private byte[] data;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_type", nullable = false)
	private String type;
	
	@CreatedDate
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	@UpdateTimestamp
	private LocalDateTime lastModifiedDate;
	
	@LastModifiedBy
	private String lastModifiedBy;
	
	public UserImageEntity(UserImage userImage) {
		this.imageId = userImage.getImageId();
		this.data = userImage.getData();
		this.fileName = userImage.getFileName();
		this.type = userImage.getType();
	}
}
