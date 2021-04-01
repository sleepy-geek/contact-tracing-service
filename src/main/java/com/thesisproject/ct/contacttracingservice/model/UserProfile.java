package com.thesisproject.ct.contacttracingservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesisproject.ct.contacttracingservice.entity.UserProfileEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserProfile extends UserRegistration{
	
	@Size(min = 2, max = 50, message = "Please enter value with 2 to 50 characters.")
	private String middleName;
	
	@Size(min = 6, max = 6, message = "Please enter a valid ID number.")
	private String idNumber;
	
	@Size(min = 10, max = 10, message = "Please enter a valid contact number.")
	private String contactNumber;
	
	@Size(min = 2, max = 50, message = "Please enter a valid position.")
	private String position;
	
	@Size(min = 2, max = 50, message = "Please enter a valid department.")
	private String department;
	
	@AssertTrue(message = "Please accept user agreement and data privacy agreement.")
	private boolean userAgreementAccepted;
	
	@JsonIgnore
	private String lastTemperatureRecord;
	
	private List<TemperatureRecord> temperatureRecords;
	private UserImage image;
	
	public UserProfile(UserProfileEntity entity) {
		super(entity);
		this.middleName = entity.getMiddleName();
		this.idNumber = entity.getIdNumber();
		this.contactNumber = entity.getContactNumber();
		this.position = entity.getPosition();
		this.department = entity.getDepartment();
		this.userAgreementAccepted = entity.isUserAgreementAccepted();
		this.temperatureRecords = new ArrayList<>();
	}
}
