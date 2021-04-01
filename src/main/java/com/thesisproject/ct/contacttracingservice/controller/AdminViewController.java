package com.thesisproject.ct.contacttracingservice.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thesisproject.ct.contacttracingservice.model.Admin;
import com.thesisproject.ct.contacttracingservice.model.SearchObject;
import com.thesisproject.ct.contacttracingservice.model.UserProfile;
import com.thesisproject.ct.contacttracingservice.service.ApplicationService;
import com.thesisproject.ct.contacttracingservice.service.UserService;
import com.thesisproject.ct.contacttracingservice.util.QRCodeUtility;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("")
@ApiIgnore
public class AdminViewController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private UserService userService;
	
	@Value("form.registration")
	private String registrationFormUrl;
	
	@GetMapping(path = "/")
	public String getHome(SearchObject searchObject,
						  ModelMap model) {
		
		model.addAttribute("qrCodeImage", "data:image/png;base64," + Base64.getEncoder().encodeToString(QRCodeUtility.generateQRCode(registrationFormUrl, 500, 500)));
		return "home";
	}
	
	@GetMapping(path = "/usermanagement")
	public String getUserManagement(SearchObject searchObject,
									UserProfile userProfile,
									ModelMap model) {
		
		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
		model.addAttribute("updateDisabled", true);
		return "user-management";
	}
	
	@PostMapping(path = "/usermanagement/search")
	public String postUserManagementSearch(SearchObject searchObject,
			                               UserProfile userProfile,
			                               ModelMap model,
			                               BindingResult result) {
		List<UserProfile> userProfileList = new ArrayList<>();
		
		Optional.ofNullable(searchObject.getFilter())
				.filter(filter -> !"".equals(filter))
				.map(userService::getUserProfiles)
				.ifPresent(userProfileList::addAll);
		
		
		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
		model.addAttribute("userProfiles", userProfileList);
		model.addAttribute("userProfile", userProfile);
		model.addAttribute("updateDisabled", true);
		
		return "user-management";
	}
	
	@PostMapping(path = "/usermanagement/edit")
	public String postUserManagementEdit(SearchObject searchObject,
			                         	   UserProfile userProfile,
			                         	   ModelMap model,
			                         	   BindingResult result) {
		List<UserProfile> userProfileList = new ArrayList<>();
		
		Optional.ofNullable(searchObject.getFilter())
				.filter(filter -> !"".equals(filter))
				.map(userService::getUserProfiles)
				.ifPresent(userProfileList::addAll);
		
		userProfile = userService.getUserProfile(userProfile.getUserProfileId());
		
		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
		model.addAttribute("userProfiles", userProfileList);
		model.addAttribute("userProfile", userProfile);
		model.addAttribute("updateDisabled", false);
		return "user-management";
	}
	
	@PostMapping(path = "/usermanagement/update")
	public String postUserManagementUpdate(SearchObject searchObject,
			                         	   @Valid UserProfile userProfile,
			                         	   BindingResult result,
			                         	   ModelMap model) {
		
		List<UserProfile> userProfileList = new ArrayList<>();
		
		Optional.ofNullable(searchObject.getFilter())
				.filter(filter -> !"".equals(filter))
				.map(userService::getUserProfiles)
				.ifPresent(userProfileList::addAll);
		
		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
		model.addAttribute("userProfiles", userProfileList);
		model.addAttribute("userProfile", userProfile);
		model.addAttribute("updateDisabled", false);
		
		if(result.hasErrors()) {
			return "user-management";
		}
		
		userProfile = Optional.ofNullable(userProfile)
								.map(up -> userService.putUserProfile(up.getUserProfileId(), up))
								.orElse(new UserProfile());
		return "user-management";
	}
	
	@PostMapping(path = "/usermanagement/delete")
	public String postUserManagementDelete(SearchObject searchObject,
			                         	   UserProfile userProfile,
			                         	   BindingResult result,
			                         	   ModelMap model) {
		
		List<UserProfile> userProfileList = new ArrayList<>();
		
		Optional.ofNullable(searchObject.getFilter())
				.filter(filter -> !"".equals(filter))
				.map(userService::getUserProfiles)
				.ifPresent(userProfileList::addAll);
		
		model.addAttribute("validPositions", applicationService.getSystemVariablesKeyValue("POSITION"));
		model.addAttribute("validDepartments", applicationService.getSystemVariablesKeyValue("DEPARTMENT"));
		model.addAttribute("userProfiles", userProfileList);
		model.addAttribute("userProfile", userProfile);
		model.addAttribute("updateDisabled", false);
		
		if(result.hasErrors()) {
			return "user-management";
		}
		
		userService.deleteUserProfile(userProfile.getUserProfileId());
		return "user-management";
	}
	
	@GetMapping(path = "/login")
	public ModelAndView getLogin(ModelMap model) {
		return new ModelAndView("login", "admin", new Admin());
	}
	
	@GetMapping(path = "/logout")
	public ModelAndView logout(ModelMap model) {
		return new ModelAndView("login", "admin", new Admin());
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
