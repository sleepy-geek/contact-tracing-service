package com.thesisproject.ct.contacttracingservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisproject.ct.contacttracingservice.entity.SystemVariableEntity;
import com.thesisproject.ct.contacttracingservice.repository.SystemVariableRepository;

@Service
public class ApplicationService {
	@Autowired
	private SystemVariableRepository systemVariableRepository;
	
	public List<SystemVariableEntity> getSystemVariables() {
		return systemVariableRepository.findAll();
	}
	
	public List<SystemVariableEntity> getSystemVariables(String variableGroup) {
		return systemVariableRepository.findAllByVariableGroupAndEnabled(variableGroup, true);
	}
	
	public Map<String, String> getSystemVariablesKeyValue(String variableGroup) {
		return systemVariableRepository.findAllByVariableGroupAndEnabled(variableGroup, true)
									   .stream()
									   .collect(Collectors.toMap(SystemVariableEntity::getVariableKey, SystemVariableEntity::getVariableValue));
	}
	
	public List<SystemVariableEntity> initializeSystemVariables() {
		Map<String, String> validPositionMap = new HashMap<>();
		validPositionMap.put("INSP", "Inspector");
		validPositionMap.put("MNTN", "Maintenance");
		validPositionMap.put("PMST", "PM Staff");
		validPositionMap.put("PDST", "PD Staff");
		validPositionMap.put("MNGR", "Manager");
		validPositionMap.put("OPTR", "Operator");
		validPositionMap.put("CNTR", "Contractor");
		validPositionMap.put("QCST", "QC Staff");
		validPositionMap.put("PCST", "PC Staff");
		validPositionMap.put("ENST", "Engineering Staff");
		validPositionMap.put("ENGR", "Service Engineer");
		
		Map<String, String> validDepartmentMap = new HashMap<>();
		validDepartmentMap.put("PRMN", "Production Maintenance");
		validDepartmentMap.put("PRDN", "Production");
		validDepartmentMap.put("QTCT", "Quality Control");
		validDepartmentMap.put("QTAS", "Quality Assurance");
		validDepartmentMap.put("PRCT", "Production Control");
		validDepartmentMap.put("VSTR", "Visitor");
		validDepartmentMap.put("OPCT", "Operation Control");
		validDepartmentMap.put("MNCT", "Manufacturing Control");

		Map<String, String> validAreaCodeMap = new HashMap<>();
		validAreaCodeMap.put("0001", "Area 0001");
		validAreaCodeMap.put("0002", "Area 0002");
		validAreaCodeMap.put("0003", "Area 0003");
		validAreaCodeMap.put("0004", "Area 0004");
		
		systemVariableRepository.deleteAll();
		validPositionMap.forEach((key, value) -> {
			SystemVariableEntity sv = new SystemVariableEntity();
			sv.setEnabled(true);
			sv.setVariableGroup("POSITION");
			sv.setVariableKey(key);
			sv.setVariableValue(value);
			systemVariableRepository.save(sv);
		});
		validDepartmentMap.forEach((key, value) -> {
			SystemVariableEntity sv = new SystemVariableEntity();
			sv.setEnabled(true);
			sv.setVariableGroup("DEPARTMENT");
			sv.setVariableKey(key);
			sv.setVariableValue(value);
			systemVariableRepository.save(sv);
		});
		validAreaCodeMap.forEach((key, value) -> {
			SystemVariableEntity sv = new SystemVariableEntity();
			sv.setEnabled(true);
			sv.setVariableGroup("AREA CODE");
			sv.setVariableKey(key);
			sv.setVariableValue(value);
			systemVariableRepository.save(sv);
		});
		
		return systemVariableRepository.findAll();
	}
}
