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

import com.thesisproject.ct.contacttracingservice.model.UserProfile;
import com.thesisproject.ct.contacttracingservice.service.UserService;
import com.thesisproject.ct.contacttracingservice.service.ApplicationService;

import io.swagger.models.properties.UUIDProperty;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/admin")
@ApiIgnore
public class AdminViewController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/home")
	public String getAdminHomeView(ModelMap model) {
		return "adminFormView";
	}
	
//	@GetMapping(path = "/update-subject-details")
//	public ModelAndView getSubjectEditorView(ModelMap model) {
//		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
//		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
//		return new ModelAndView("subjectEditorFormView", "subject", new UserProfile());
//	}
//	
//	@PostMapping(path = "/update-subject-details/search")
//	public ModelAndView getSubjectEditorSearchView(@RequestParam(name = "subjectId") String subjectId,
//			 									   ModelMap model) {
//		UserProfile userProfile = new UserProfile();
//		try {
//			userProfile = userService.getSubject(UUID.fromString(subjectId));
//		} catch(Exception e) {
//			
//		}
//		
//		
//		
//		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
//		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
//		return new ModelAndView("subjectEditorFormView", "subject", userProfile);
//	}
//	
//	@PostMapping(path = "/update-subject-details/save")
//	public String getSubjectEditorSaveView(@Valid @ModelAttribute(name = "subject") UserProfile userProfile,
//			 							BindingResult result,
//			 							ModelMap model) {
//		if(result.hasErrors() ) {
//			model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
//			model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
//			return "subjectEditorFormView";
//		}
//		
//		userProfile = userService.putSubject(userProfile.getSubjectId(), userProfile);
//		
//		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
//		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
//		return "subjectEditorFormView";
//	}
//	
//	@GetMapping(path = "/update-system-details")
//	public String getSystemEditorView(ModelMap model) {
//		return "adminFormView";
//	}
}
