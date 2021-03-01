package com.thesisproject.ct.contacttracingservice.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thesisproject.ct.contacttracingservice.entity.FormEntity;
import com.thesisproject.ct.contacttracingservice.enums.FormStatus;
import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.repository.FormRepository;

@Service
public class FormService {
	
	@Value("${form.basepath}")
	private String baseUrl;
	
	@Autowired
	private FormRepository formRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	public String sendFormUrlToEmail(String email) {
		String formUrl = this.createFormUrl();
		emailService.sendFormUrlEmail(email, formUrl);
		return formUrl;
	}
	
	public String createFormUrl() {
		FormEntity formEntity = new FormEntity();
		formEntity.setCreatedDate(LocalDateTime.now());
		formEntity.setStatus(FormStatus.CREATED);
		
		formRepository.saveAndFlush(formEntity);
		
		return baseUrl + formEntity.getFormId();
	}
	
	public String saveForm(UUID formId, Subject subject) {
		FormEntity formEntity = formRepository.getOne(formId);
		formEntity.setStatus(FormStatus.GENERATED);
		formEntity.setSubjectId(subject.getSubjectId());
		formEntity.setSubmittedDate(LocalDateTime.now());
		
		formRepository.saveAndFlush(formEntity);
		return "OK";
	}
	
	public String submitForm(UUID formId, Subject subject) {
		FormEntity formEntity = formRepository.getOne(formId);
		formEntity.setStatus(FormStatus.SUBMITTED);
		formEntity.setSubjectId(subject.getSubjectId());
		formEntity.setSubmittedDate(LocalDateTime.now());
		
		emailService.sendPersonalQRCodeEmail(subject);
		
		formRepository.saveAndFlush(formEntity);
		return "OK";
	}
	
	public String validateFormId(UUID formId) {
		Optional.ofNullable(formId)
				.flatMap(formRepository::findById)
				.filter(form -> !FormStatus.SUBMITTED.equals(form.getStatus()))
				.orElseThrow(BadRequestException::new);
		return "OK";
	}
}
