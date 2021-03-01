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
import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.service.EmailService;
import com.thesisproject.ct.contacttracingservice.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Subject>> getSubjects() {
		return ResponseEntity.ok().body(subjectService.getSubjects());
	}
	
	@PostMapping(path = "/export")
	public ResponseEntity<List<Subject>> exportSubjects() {
		emailService.sendSubjectRecordsReport();
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(path = "/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> getSubject(@PathVariable(name = "subjectId", required = true) UUID subjectId) throws NotFoundException {
		return ResponseEntity.ok().body(subjectService.getSubject(subjectId));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> postSubject(@RequestBody Subject subject) throws BadRequestException {
		return ResponseEntity.ok().body(subjectService.postSubject(subject));
	}
	
	@PutMapping(path = "/{subjectId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> postSubject(@PathVariable(name = "subjectId", required = true) UUID subjectId,
											   @RequestBody Subject subject) throws BadRequestException, NotFoundException {
		return ResponseEntity.ok().body(subjectService.putSubject(subjectId, subject));
	}
	
	@PostMapping(path = "/{subjectId}/temperature", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> postSubjectTemperature(@RequestBody Subject subject) throws BadRequestException {
		return ResponseEntity.ok().body(subjectService.postSubject(subject));
	}
}
