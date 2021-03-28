package com.thesisproject.ct.contacttracingservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thesisproject.ct.contacttracingservice.model.Admin;
import com.thesisproject.ct.contacttracingservice.util.QRCodeUtility;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("")
@ApiIgnore
public class CoreController {
	
	@GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getFormsQRCode() {
		return ResponseEntity.ok(QRCodeUtility.generateQRCode("https://contact-tracing-service.herokuapp.com/forms", 500, 500));
	}
	@GetMapping(path = "/login")
	public ModelAndView login(ModelMap model) {
		return new ModelAndView("/login", "admin", new Admin());
	}
	
	@GetMapping(path = "/logout")
	public ModelAndView logout(ModelMap model) {
		return new ModelAndView("/login", "admin", new Admin());
	}
}
