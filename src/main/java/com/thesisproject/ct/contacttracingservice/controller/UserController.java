package com.thesisproject.ct.contacttracingservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.error.NotFoundException;
import com.thesisproject.ct.contacttracingservice.model.TemperatureRecord;
import com.thesisproject.ct.contacttracingservice.model.UserProfile;
import com.thesisproject.ct.contacttracingservice.service.EmailService;
import com.thesisproject.ct.contacttracingservice.service.UserService;

@RestController
@RequestMapping("/api/userprofiles")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserProfile>> getSubjects() {
		return ResponseEntity.ok().body(userService.getUserProfiles());
	}
	
	@PostMapping(path = "/export")
	public ResponseEntity<List<UserProfile>> exportSubjects() {
		emailService.sendSubjectRecordsReport();
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(path = "/{userProfileId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable(name = "userProfileId", required = true) UUID userProfileId) throws NotFoundException {
		return ResponseEntity.ok().body(userService.getUserProfile(userProfileId));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> postUserProfile(@RequestBody UserProfile userProfile) throws BadRequestException {
		return ResponseEntity.ok().body(userService.postUserProfile(userProfile));
	}
	
	@PutMapping(path = "/{subjectId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> putUserProfile(@PathVariable(name = "subjectId", required = true) UUID subjectId,
											   @RequestBody UserProfile userProfile) throws BadRequestException, NotFoundException {
		return ResponseEntity.ok().body(userService.putUserProfile(subjectId, userProfile));
	}
	
	@PostMapping(path = "/{subjectId}/temperature-record", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TemperatureRecord> postTemperatureRecord(@PathVariable UUID subjectId,
																	 @RequestBody TemperatureRecord temperatureRecord) throws BadRequestException {
		return ResponseEntity.ok().body(userService.postTemperatureRecord(subjectId, temperatureRecord));
	}
}
