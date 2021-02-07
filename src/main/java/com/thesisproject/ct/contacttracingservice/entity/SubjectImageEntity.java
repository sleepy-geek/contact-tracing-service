package com.thesisproject.ct.contacttracingservice.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thesisproject.ct.contacttracingservice.model.SubjectImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "public", name="tbl_subject_image")
@Data
@NoArgsConstructor
public class SubjectImageEntity {
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
	@Column(name = "image_id")
	private UUID imageId;
	
	@Column(name = "subject_id", nullable = false)
	private UUID subjectId;
	
	@Column(name = "data", nullable = false)
	private byte[] data;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_type", nullable = false)
	private String type;
	
	public SubjectImageEntity(SubjectImage subjectImage) {
		this.imageId = subjectImage.getImageId();
		this.data = subjectImage.getData();
		this.fileName = subjectImage.getFileName();
		this.type = subjectImage.getType();
	}
}
