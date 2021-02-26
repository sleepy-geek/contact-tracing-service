package com.thesisproject.ct.contacttracingservice.model;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thesisproject.ct.contacttracingservice.entity.SubjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Subject {
	private UUID subjectId;
	
	@NotNull(message = "This is a required field")
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters")
	private String firstName;
	
	@NotNull(message = "This is a required field")
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters")
	private String middleName;
	
	@NotNull(message = "This is a required field")
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters")
	private String lastName;
	
	@NotNull(message = "This is a required field")
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters")
	private String idNumber;
	
	@NotNull(message = "This is a required field")
	@Size(min = 10, max = 10, message = "Please enter value with 10 characters")
	private String contactNumber;
	
	@NotNull(message = "This is a required field")
	@Email(message = "Please enter a valid email")
	private String email;
	
	@NotNull(message = "This is a required field")
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters")
	private String position;
	
	@NotNull(message = "This is a required field")
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters")
	private String department;
	
	@NotNull(message = "This is a required field")
	private boolean agreedDataPrivacyConsent;
	
	private List<SubjectTemperature> temperatureRecord;
	private SubjectImage image;
	
	public Subject(SubjectEntity entity) {
		this.subjectId  = entity.getSubjectId();
		this.firstName = entity.getFirstName();
		this.middleName = entity.getMiddleName();
		this.lastName = entity.getLastName();
		this.idNumber = entity.getIdNumber();
		this.contactNumber = entity.getContactNumber();
		this.email = entity.getEmail();
		this.position = entity.getPosition();
		this.department = entity.getDepartment();
		this.agreedDataPrivacyConsent = entity.isAgreedDataPrivacyConsent();
	}
}
