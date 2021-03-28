package com.thesisproject.ct.contacttracingservice.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "public", name="tbl_system_variable")
@Data
@NoArgsConstructor
public class SystemVariableEntity {
	
	@Id
	@GeneratedValue(generator = "uuid-generator")
	@GenericGenerator(name = "uuid-generator", strategy = "uuid2")
	@Column(name = "var_id")
	private UUID variableId;
	
	@Column(name = "var_group")
	private String variableGroup;
	
	@Column(name = "var_key")
	private String variableKey;
	
	@Column(name ="var_value")
	private String variableValue;
	
	@Column(name = "var_enabled")
	private boolean enabled;
}
