package com.thesisproject.ct.contacttracingservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thesisproject.ct.contacttracingservice.entity.SubjectTemperatureEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectTemperature {
	private UUID temperatureId;
	private UUID subjectId;
	private Double temperature;
	private LocalDateTime recordDate;
	private String areaCode;
	
	public SubjectTemperature(SubjectTemperatureEntity entity) {
		this.temperatureId = entity.getTemperatureId();
		this.subjectId = entity.getSubjectId();
		this.temperature = entity.getTemperature();
		this.recordDate = entity.getRecordDate();
		this.areaCode = entity.getAreaCode();
	}
}
