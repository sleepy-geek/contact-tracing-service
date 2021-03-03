package com.thesisproject.ct.contacttracingservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thesisproject.ct.contacttracingservice.entity.SubjectTemperatureEntity;

@Repository
public interface SubjectTemperatureRepository extends JpaRepository<SubjectTemperatureEntity, UUID>{
	public List<SubjectTemperatureEntity> findAllBySubjectId(UUID subjectId);
}
