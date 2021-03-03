package com.thesisproject.ct.contacttracingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thesisproject.ct.contacttracingservice.entity.SystemVariableEntity;
import com.thesisproject.ct.contacttracingservice.service.SystemService;

@RestController
@RequestMapping("/api/system")
public class SystemController {
	
	@Autowired
	private SystemService systemService;
	
	@GetMapping
	public ResponseEntity<List<SystemVariableEntity>> getSystemVariables() {
		return ResponseEntity.ok(systemService.getSystemVariables());
	}
	
	@GetMapping(path = "/variables")
	public ResponseEntity<List<SystemVariableEntity>> getSystemVariablesByGroup(@RequestParam(name = "variablegroup") String variableGroup) {
		return ResponseEntity.ok(systemService.getSystemVariables(variableGroup));
	}
	
	@PostMapping(path = "/init")
	public ResponseEntity<List<SystemVariableEntity>> initializeSystemVariables() {
		return ResponseEntity.ok(systemService.initializeSystemVariables());
	}
}
