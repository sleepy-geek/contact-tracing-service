package com.thesisproject.ct.contacttracingservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisproject.ct.contacttracingservice.entity.SubjectEntity;
import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.error.NotFoundException;
import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> getSubjects() {
		return subjectRepository.findAll()
								.stream()
								.map(Subject::new)
								.collect(Collectors.toList());
	}
	
	public Subject getSubject(UUID subjectId) throws NotFoundException {
		return subjectRepository.findById(subjectId)
								.map(Subject::new)
					   			.orElseThrow(NotFoundException::new);
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
}
