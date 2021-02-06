package com.thesisproject.ct.contacttracingservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesisproject.ct.contacttracingservice.entity.SubjectTemperatureEntity;

public interface SubjectTemperatureRepository extends JpaRepository<SubjectTemperatureEntity, UUID>{

}
