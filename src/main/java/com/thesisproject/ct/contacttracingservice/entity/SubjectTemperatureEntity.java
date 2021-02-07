package com.thesisproject.ct.contacttracingservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.thesisproject.ct.contacttracingservice.model.SubjectTemperature;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "public", name="tbl_subject_temperature")
@Data
@NoArgsConstructor
public class SubjectTemperatureEntity {
	
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid2")
	@Column(name = "temp_id")
	private UUID temperatureId;
	
	@Column(name = "subject_id", nullable = false)
	private UUID subjectId;
	
	@Column(name = "temperature", nullable = false)
	private Double temperature;
	
	@Column(name = "record_date", nullable = false)
	private LocalDateTime recordDate;
	
	public SubjectTemperatureEntity(SubjectTemperature subjectTemperature) {
		this.temperatureId = subjectTemperature.getTemperatureId();
		this.temperature = subjectTemperature.getTemperature();
		this.recordDate = subjectTemperature.getRecordDate();
	}
}
