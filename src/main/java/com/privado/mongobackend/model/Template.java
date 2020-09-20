package com.privado.mongobackend.model;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "templates")
public class Template implements Serializable{

	
	public String type;
	
	private String entity;
	private String customerId;
	private String law;
	private List<Fields> fields;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public List<Fields> getFields() {
		return fields;
	}
	public void setFields(List<Fields> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "Template [type=" + type + ", entity=" + entity + ", customerId=" + customerId + ", law=" + law
				+ ", fields=" + fields + "]";
	}
	
	
}
