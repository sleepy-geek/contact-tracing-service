package com.thesisproject.ct.contacttracingservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thesisproject.ct.contacttracingservice.model.SmsObject;
import com.thesisproject.ct.contacttracingservice.model.UserProfile;

@Service
public class SmsService {
	
	@Value("${semaphore.url}")
	private String semaphoreUrl;
	
	@Value("${semaphore.apikey}")
	private String semaphoreApiKey;
	
	@Value("${semaphore.sendername}")
	private String senderName;
	
	public void sendRegistrationCompletionSms(UserProfile userProfile) {
		SmsObject sms = new SmsObject();
		sms.setApikey(this.semaphoreApiKey);
		sms.setSendername(this.senderName);
		sms.setNumber(userProfile.getContactNumber());
		sms.setMessage("Thank you for completing your registration with CTS. You may now use your personal QR Code to user the contact tracing facilities.\n\nCTS Team");


		RestTemplate template = new RestTemplate();
		HttpEntity<SmsObject> httpEntity = new HttpEntity<>(sms);
		String response = template.postForObject(semaphoreUrl, httpEntity, String.class);
		System.out.println(response);
	}
	
	public void sendDailyRecordSms() {
		
	}
	
	public void sendDetectionSms() {
		
	}
}
