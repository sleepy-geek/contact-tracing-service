package com.thesisproject.ct.contacttracingservice.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.thesisproject.ct.contacttracingservice.entity.FormEntity;
import com.thesisproject.ct.contacttracingservice.enums.FormStatus;
import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.repository.FormRepository;

@Service
public class FormService {
	
	@Value("${form.basepath}")
	private String baseUrl;
	
	@Autowired
	private FormRepository formRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public String sendFormUrlToEmail(String email) {
		String formUrl = this.createFormUrl();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("no-reply-contact-tracing@gmail.com");
		message.setTo(email);
		message.setSubject("Contact Tracing Form URL");
		message.setText(formUrl);
		
		emailSender.send(message);
		
		return formUrl;
	}
	
	public String createFormUrl() {
		FormEntity formEntity = new FormEntity();
		formEntity.setCreatedDate(LocalDateTime.now());
		formEntity.setStatus(FormStatus.CREATED);
		
		formRepository.saveAndFlush(formEntity);
		
		return baseUrl + formEntity.getFormId();
	}
	
	public String validateFormId(UUID formId) {
		Optional.ofNullable(formId)
				.flatMap(formRepository::findById)
				.filter(form -> !FormStatus.SUBMITTED.equals(form.getStatus()))
				.orElseThrow(BadRequestException::new);
		return "OK";
	}
}
