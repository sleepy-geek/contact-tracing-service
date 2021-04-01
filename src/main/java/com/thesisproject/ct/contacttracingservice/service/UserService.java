package com.thesisproject.ct.contacttracingservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.thesisproject.ct.contacttracingservice.entity.FormEntity;
import com.thesisproject.ct.contacttracingservice.entity.TemperatureRecordEntity;
import com.thesisproject.ct.contacttracingservice.entity.UserProfileEntity;
import com.thesisproject.ct.contacttracingservice.error.BadRequestException;
import com.thesisproject.ct.contacttracingservice.error.NotFoundException;
import com.thesisproject.ct.contacttracingservice.model.TemperatureRecord;
import com.thesisproject.ct.contacttracingservice.model.UserProfile;
import com.thesisproject.ct.contacttracingservice.model.UserRegistration;
import com.thesisproject.ct.contacttracingservice.repository.FormRepository;
import com.thesisproject.ct.contacttracingservice.repository.TemperatureRecordRepository;
import com.thesisproject.ct.contacttracingservice.repository.UserProfileRepository;

@Service
public class UserService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private TemperatureRecordRepository temperatureRecordRepository;
	
	@Autowired 
	private FormRepository formRepository;
	
	public List<UserProfile> getUserProfiles(String filter) {
		List<UserProfileEntity> entities = Optional.ofNullable(filter)
													.map(fltr -> fltr + "%")
													.map(StringUtils::capitalize)
													.map(fltr -> userProfileRepository.findAllByIdNumberLikeOrFirstNameLikeOrMiddleNameLikeOrLastNameLike(fltr, fltr, fltr, fltr))
													.orElse(userProfileRepository.findAll());
		return entities.stream()
						.map(UserProfile::new)
						.map(this::populateUserProfileTemperatureRecords)
						.collect(Collectors.toList());
	}
	
	public UserProfile getUserProfile(UUID userProfileId)  {
		return userProfileRepository.findById(userProfileId)
								.map(UserProfile::new)
								.map(this::populateUserProfileTemperatureRecords)
					   			.orElse(new UserProfile());
	}
	
	private UserProfile populateUserProfileTemperatureRecords(UserProfile userProfile) {
		userProfile.getTemperatureRecords().addAll(this.getTemperatureRecords(userProfile.getUserProfileId()));
		Comparator<TemperatureRecord> tempRecordDateReverseComparator = Comparator.comparing(TemperatureRecord::getRecordDate).reversed();
		String lastTemperatureRecord = userProfile.getTemperatureRecords().stream().sorted(tempRecordDateReverseComparator).findFirst().map(TemperatureRecord::getTemperature).map(String::valueOf).orElse("--");
		userProfile.setLastTemperatureRecord(lastTemperatureRecord);
		return userProfile;
	}
	
	public List<TemperatureRecord> getTemperatureRecords(UUID userProfileId) {
		return temperatureRecordRepository.findAllByUserProfileId(userProfileId)
										   .stream()
										   .map(TemperatureRecord::new)
										   .collect(Collectors.toList());
	}
	
	public UserProfile postUserProfile(UserProfile userProfile) throws BadRequestException {
		return Optional.ofNullable(userProfile)
					   .map(UserProfileEntity::new)
					   .map(userProfileRepository::saveAndFlush)
					   .map(UserProfile::new)
					   .orElseThrow(BadRequestException::new);
	}
	
	public UserProfile putUserProfile(UUID userProfileId, UserProfile userProfile) throws NotFoundException {
		if(userProfileRepository.existsById(userProfileId)) {
			userProfile.setUserProfileId(userProfileId);
			return Optional.ofNullable(userProfile)
						   .map(UserProfileEntity::new)
						   .map(userProfileRepository::saveAndFlush)
						   .map(UserProfile::new)
						   .orElseThrow(BadRequestException::new);
		} else {
			throw new NotFoundException();
		}
	}
	
	public UserProfile getFormUser(UUID formId) {
		return formRepository.findById(formId)
					   		 .map(FormEntity::getUserProfileId)
					   		 .map(this::getUserProfile)
					   		 .orElse(new UserProfile());
	}
	
	public TemperatureRecord postTemperatureRecord(UUID userProfileId, TemperatureRecord temperatureRecord) {
		return Optional.ofNullable(temperatureRecord)
					   .map(temp -> {
						   temp.setUserProfileId(userProfileId);
						   return temp;
					   })
					   .map(TemperatureRecordEntity::new)
					   .map(temperatureRecordRepository::saveAndFlush)
					   .map(TemperatureRecord::new)
					   .orElse(new TemperatureRecord());
	}
	
	public UserRegistration postUserRegistration(UserRegistration userRegistration) throws BadRequestException {
		return Optional.ofNullable(userRegistration)
					   .map(UserProfileEntity::new)
					   .map(userProfileRepository::saveAndFlush)
					   .map(UserRegistration::new)
					   .orElseThrow(BadRequestException::new);
	}
}
