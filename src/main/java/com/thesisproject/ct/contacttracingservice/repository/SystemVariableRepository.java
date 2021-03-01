package com.thesisproject.ct.contacttracingservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thesisproject.ct.contacttracingservice.entity.SystemVariableEntity;

public interface SystemVariableRepository extends JpaRepository<SystemVariableEntity, UUID> {
	public List<SystemVariableEntity> findAllByVariableGroupAndEnabled(String variableGroup, boolean enabled);
}
