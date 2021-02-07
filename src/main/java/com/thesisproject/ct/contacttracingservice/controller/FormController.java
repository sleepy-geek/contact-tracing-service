package com.thesisproject.ct.contacttracingservice.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.service.FormService;
import com.thesisproject.ct.contacttracingservice.service.SubjectService;

@Controller
@RequestMapping("/forms")
public class FormController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired 
	private FormService formService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		return "homeView";
	}
	
	@PostMapping("/create")
	public String createForm(@RequestParam("email") String email,
							 ModelMap model) {
		String formUrl = formService.sendFormUrlToEmail(email);
		model.addAttribute("formUrl", formUrl);
		
		return "successfullySentView";
	}
	
	@GetMapping("/{formId}")
	public ModelAndView getForm(@PathVariable(name = "formId") UUID formId,
								ModelMap model) {
		formService.validateFormId(formId);
		model.addAttribute("formId", formId);
		return new ModelAndView("subjectFormView", "subject", new Subject());
	}
	
	@PostMapping("/{formId}/submit")
	public String submmitForm(@PathVariable(name = "formId") UUID formId,
							  @RequestParam("image") MultipartFile image, 
							  @Valid @ModelAttribute("subject") Subject subject, 
							  BindingResult result,
							  ModelMap model) {
		subject = subjectService.postSubject(subject);
		formService.submitForm(formId, subject);
		
		model.addAttribute("subjectId", subject.getSubjectId());
		model.addAttribute("firstName", subject.getFirstName());
		model.addAttribute("middleName", subject.getMiddleName());
		model.addAttribute("lastName", subject.getLastName());
		model.addAttribute("idNumber", subject.getIdNumber());
		model.addAttribute("contactNumber", subject.getContactNumber());
		model.addAttribute("email", subject.getEmail());
		model.addAttribute("position", subject.getPosition());
		model.addAttribute("department", subject.getDepartment());
		model.addAttribute("agreedDataPrivacyConsent", subject.isAgreedDataPrivacyConsent());
		model.addAttribute("image", image);
		
		return "subjectDetailView";
	}
}
