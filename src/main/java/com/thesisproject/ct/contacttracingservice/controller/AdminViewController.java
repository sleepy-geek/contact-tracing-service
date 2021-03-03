package com.thesisproject.ct.contacttracingservice.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thesisproject.ct.contacttracingservice.model.Subject;
import com.thesisproject.ct.contacttracingservice.service.SubjectService;
import com.thesisproject.ct.contacttracingservice.service.SystemService;

import io.swagger.models.properties.UUIDProperty;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/admin")
@ApiIgnore
public class AdminViewController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping(path = "/home")
	public String getAdminHomeView(ModelMap model) {
		return "adminFormView";
	}
	
	@GetMapping(path = "/update-subject-details")
	public ModelAndView getSubjectEditorView(ModelMap model) {
		model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
		return new ModelAndView("subjectEditorFormView", "subject", new Subject());
	}
	
	@PostMapping(path = "/update-subject-details/search")
	public ModelAndView getSubjectEditorSearchView(@RequestParam(name = "subjectId") String subjectId,
			 									   ModelMap model) {
		Subject subject = new Subject();
		try {
			subject = subjectService.getSubject(UUID.fromString(subjectId));
		} catch(Exception e) {
			
		}
		
		
		
		model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
		return new ModelAndView("subjectEditorFormView", "subject", subject);
	}
	
	@PostMapping(path = "/update-subject-details/save")
	public String getSubjectEditorSaveView(@Valid @ModelAttribute(name = "subject") Subject subject,
			 							BindingResult result,
			 							ModelMap model) {
		if(result.hasErrors() ) {
			model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
			model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
			return "subjectEditorFormView";
		}
		
		subject = subjectService.putSubject(subject.getSubjectId(), subject);
		
		model.addAttribute("validPositions", systemService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", systemService.getSystemVariablesKeyValue("DEPARTMENT"));
		return "subjectEditorFormView";
	}
	
	@GetMapping(path = "/update-system-details")
	public String getSystemEditorView(ModelMap model) {
		return "adminFormView";
	}
}
