package com.thesisproject.ct.contacttracingservice.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thesisproject.ct.contacttracingservice.model.Subject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "tbl_subject")
public class SubjectEntity {
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
	@Column(name = "subject_id")
	private UUID subjectId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "id_number")
	private String idNumber;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "job_position")
	private String position;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "data_privacy_agreement")
	private boolean agreedDataPrivacyConsent;
	
	public SubjectEntity(Subject subject) {
		this.subjectId  = subject.getSubjectId();
		this.firstName = subject.getFirstName();
		this.middleName = subject.getMiddleName();
		this.lastName = subject.getLastName();
		this.idNumber = subject.getIdNumber();
		this.contactNumber = subject.getContactNumber();
		this.email = subject.getEmail();
		this.position = subject.getPosition();
		this.department = subject.getDepartment();
		this.agreedDataPrivacyConsent = subject.isAgreedDataPrivacyConsent();
	}
}
