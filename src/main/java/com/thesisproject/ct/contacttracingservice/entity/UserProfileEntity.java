package com.thesisproject.ct.contacttracingservice.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;

import com.thesisproject.ct.contacttracingservice.model.UserProfile;
import com.thesisproject.ct.contacttracingservice.model.UserRegistration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "tbl_user_profile")
public class UserProfileEntity {
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
	@Column(name = "user_profile_id")
	private UUID userProfileId;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "middle_name", nullable = true)
	private String middleName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "id_number", unique = true, nullable = true)
	private String idNumber;
	
	@Column(name = "contact_number", nullable = true)
	private String contactNumber;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "job_position", nullable = true)
	private String position;
	
	@Column(name = "department", nullable = true)
	private String department;
	
	@Column(name = "user_agreement", nullable = true)
	private boolean userAgreementAccepted;
	
	public UserProfileEntity(UserProfile userProfile) {
		this.userProfileId  = userProfile.getUserProfileId();
		this.firstName = StringUtils.capitalize(userProfile.getFirstName());
		this.middleName = StringUtils.capitalize(userProfile.getMiddleName());
		this.lastName = StringUtils.capitalize(userProfile.getLastName());
		this.idNumber = userProfile.getIdNumber();
		this.contactNumber = userProfile.getContactNumber();
		this.email = userProfile.getEmail().toLowerCase();
		this.position = userProfile.getPosition();
		this.department = userProfile.getDepartment();
		this.userAgreementAccepted = userProfile.isUserAgreementAccepted();
	}
	
	public UserProfileEntity(UserRegistration userRegistration) {
		this.userProfileId  = userRegistration.getUserProfileId();
		this.firstName = StringUtils.capitalize(userRegistration.getFirstName());
		this.lastName = StringUtils.capitalize(userRegistration.getLastName());
		this.email = userRegistration.getEmail().toLowerCase();
	}
}
