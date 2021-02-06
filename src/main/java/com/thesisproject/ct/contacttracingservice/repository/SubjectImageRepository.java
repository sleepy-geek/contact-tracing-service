package com.thesisproject.ct.contacttracingservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesisproject.ct.contacttracingservice.entity.SubjectImageEntity;

public interface SubjectImageRepository extends JpaRepository<SubjectImageEntity, UUID> {

}
