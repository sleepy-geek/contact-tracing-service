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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.error.NotFoundException;
import com.thesisproject.ct.contacttracingservice.model.TemperatureRecord;
import com.thesisproject.ct.contacttracingservice.model.UserImage;
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
	
	@PostMapping(path = "/export")
	public ResponseEntity<List<UserProfile>> exportUsers() {
		emailService.sendUserProfilesReport();
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserProfile>> getUserProfiles(@RequestParam(name = "filter", required = false) String filter) {
		return ResponseEntity.ok().body(userService.getUserProfiles(filter));
	}
	
	@GetMapping(path = "/{userProfileId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable(name = "userProfileId", required = true) UUID userProfileId) throws NotFoundException {
		return ResponseEntity.ok().body(userService.getUserProfile(userProfileId));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> postUserProfile(@RequestBody UserProfile userProfile) throws BadRequestException {
		return ResponseEntity.ok().body(userService.postUserProfile(userProfile));
	}
	
	@PutMapping(path = "/{userProfileId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> putUserProfile(@PathVariable(name = "subjectId", required = true) UUID userProfileId,
											   @RequestBody UserProfile userProfile) throws BadRequestException, NotFoundException {
		return ResponseEntity.ok().body(userService.putUserProfile(userProfileId, userProfile));
	}
	
	@PostMapping(path = "/{userProfileId}/temperaturerecords", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TemperatureRecord> postTemperatureRecord(@PathVariable UUID userProfileId,
																	 @RequestBody TemperatureRecord temperatureRecord) throws BadRequestException {
		return ResponseEntity.ok().body(userService.postTemperatureRecord(userProfileId, temperatureRecord));
	}
	
	@PostMapping(path ="/{userProfileId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserImage> postUserImage(MultipartFile imageFile) {
		return ResponseEntity.ok(null);
	}
}
