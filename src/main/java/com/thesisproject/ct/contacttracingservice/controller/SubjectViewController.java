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
import org.springframework.web.servlet.ModelAndView;

import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.service.FormService;
import com.thesisproject.ct.contacttracingservice.service.SubjectService;
import com.thesisproject.ct.contacttracingservice.service.SystemService;

@Controller
@RequestMapping("/forms")
public class SubjectViewController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired 
	private FormService formService;
	
	@Autowired 
	private SystemService systemService;
	 
	@GetMapping()
	public String getFormBuilder(ModelMap model) {
		return "createFormView";
	}
	
	@PostMapping("/create")
	public String createForm(@RequestParam("email") String email,
							 ModelMap model) {
		model.addAttribute("formUrl", formService.sendFormUrlToEmail(email));
		return "formCreatedView";
	}
	
	@GetMapping("/{formId}")
	public ModelAndView getForm(@PathVariable(name = "formId") UUID formId,
								ModelMap model) {
		formService.validateFormId(formId);
		Subject subject = subjectService.getFormSubject(formId);
		
		model.addAttribute("formId", formId);
		model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
			
		return new ModelAndView("subjectFormView", "subject", subject);
	}
	
	@PostMapping("/{formId}/save")
	public String saveForm(@PathVariable(name = "formId") UUID formId,
							@Valid @ModelAttribute("subject") Subject subject, 
							BindingResult result,
							ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("formId", formId);
			model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
			model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
			return "subjectFormView";
		}
		
		subject = subjectService.postSubject(subject);
		formService.saveForm(formId, subject);
		
		model.addAttribute("formId", formId);
		model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
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
		
		return "formSavedView";
	}
	
	@PostMapping("/{formId}/submit")
	public String submitForm(@PathVariable(name = "formId") UUID formId,
							  @Valid @ModelAttribute("subject") Subject subject, 
							  BindingResult result,
							  ModelMap model) {
		subject = subjectService.getFormSubject(formId);
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
		
		return "formSubmittedView";
	}
}
