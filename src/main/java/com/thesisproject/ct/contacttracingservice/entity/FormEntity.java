package com.thesisproject.ct.contacttracingservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thesisproject.ct.contacttracingservice.enums.FormStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema="public", name="tbl_form")
@Data
@NoArgsConstructor
public class FormEntity {
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
	@Column(name = "form_id")
	private UUID formId;
	
	@Column(name = "subject_id", nullable = true)
	private UUID subjectId;
	
	@Column(name = "status", nullable = false)
	private FormStatus status;
	
	@Column(name = "created_dt", nullable = false)
	private LocalDateTime createdDate;
	
	@Column(name = "submitted_dt", nullable = true)
	private LocalDateTime submittedDate;
}
