package com.thesisproject.ct.contacttracingservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thesisproject.ct.contacttracingservice.util.QRCodeUtility;

@Controller
@RequestMapping("")
public class CoreController {
	
	@GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getFormsQRCode() {
		return ResponseEntity.ok(QRCodeUtility.generateQRCode("https://contact-tracing-service.herokuapp.com/forms"));
	}
}
