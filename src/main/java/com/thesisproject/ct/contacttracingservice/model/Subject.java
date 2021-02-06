package com.thesisproject.ct.contacttracingservice.model;

import java.util.List;
import java.util.UUID;

import com.thesisproject.ct.contacttracingservice.entity.SubjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Subject {
	private UUID subjectId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String idNumber;
	private String contactNumber;
	private String email;
	private String position;
	private String department;
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
