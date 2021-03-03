package com.thesisproject.ct.contacttracingservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisproject.ct.contacttracingservice.entity.FormEntity;
import com.thesisproject.ct.contacttracingservice.entity.SubjectEntity;
import com.thesisproject.ct.contacttracingservice.entity.SubjectTemperatureEntity;
import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.error.NotFoundException;
import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.model.SubjectTemperature;
import com.thesisproject.ct.contacttracingservice.repository.FormRepository;
import com.thesisproject.ct.contacttracingservice.repository.SubjectRepository;
import com.thesisproject.ct.contacttracingservice.repository.SubjectTemperatureRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SubjectTemperatureRepository subjectTemperatureRepository;
	
	@Autowired FormRepository formRepository;
	
	public List<Subject> getSubjects() {
		return subjectRepository.findAll()
								.stream()
								.map(Subject::new)
								.map(subject -> {
									subject.getTemperatureRecords().addAll(this.getSubjectTemperatureRecords(subject.getSubjectId()));
									return subject;
								})
								.collect(Collectors.toList());
	}
	
	public Subject getSubject(UUID subjectId)  {
		return subjectRepository.findById(subjectId)
								.map(Subject::new)
								.map(subject -> {
									subject.getTemperatureRecords().addAll(this.getSubjectTemperatureRecords(subjectId));
									return subject;
								})
					   			.orElse(new Subject());
	}
	
	public List<SubjectTemperature> getSubjectTemperatureRecords(UUID subjectId) {
		return subjectTemperatureRepository.findAllBySubjectId(subjectId)
										   .stream()
										   .map(SubjectTemperature::new)
										   .collect(Collectors.toList());
	}
	
	public Subject postSubject(Subject subject) throws BadRequestException {
		return Optional.ofNullable(subject)
					   .map(SubjectEntity::new)
					   .map(subjectRepository::saveAndFlush)
					   .map(Subject::new)
					   .orElseThrow(BadRequestException::new);
	}
	
	public Subject putSubject(UUID subjectId, Subject subject) throws NotFoundException {
		if(subjectRepository.existsById(subjectId)) {
			subject.setSubjectId(subjectId);
			return Optional.ofNullable(subject)
						   .map(SubjectEntity::new)
						   .map(subjectRepository::saveAndFlush)
						   .map(Subject::new)
						   .orElseThrow(BadRequestException::new);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Subject getFormSubject(UUID formId) {
		return formRepository.findById(formId)
					   		 .map(FormEntity::getSubjectId)
					   		 .map(this::getSubject)
					   		 .orElse(new Subject());
	}
	
	public SubjectTemperature postSubjectTemperature(UUID subjectId, SubjectTemperature subjectTemperature) {
		return Optional.ofNullable(subjectTemperature)
					   .map(temp -> {
						   temp.setSubjectId(subjectId);
						   return temp;
					   })
					   .map(SubjectTemperatureEntity::new)
					   .map(subjectTemperatureRepository::saveAndFlush)
					   .map(SubjectTemperature::new)
					   .orElse(new SubjectTemperature());
	}
}
